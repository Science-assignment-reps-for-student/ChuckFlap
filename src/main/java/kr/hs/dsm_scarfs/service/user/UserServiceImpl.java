package kr.hs.dsm_scarfs.service.user;

import kr.hs.dsm_scarfs.domain.entitys.*;
import kr.hs.dsm_scarfs.domain.payload.request.SignUpRequest;
import kr.hs.dsm_scarfs.domain.payload.response.UserResponse;
import kr.hs.dsm_scarfs.domain.repository.*;
import kr.hs.dsm_scarfs.exception.*;
import kr.hs.dsm_scarfs.util.EmailUtil;
import kr.hs.dsm_scarfs.util.JwtUtil;
import kr.hs.dsm_scarfs.util.PasswordEncoder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthEmailRepository authEmailRepository;
    @Autowired
    private AuthCodeRepository authCodeRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FileSingleRepository fileSingleRepository;
    @Autowired
    private FileMultiRepository fileMultiRepository;

    @SneakyThrows
    @Override
    public UserResponse getUser(String token) {
        Integer userId = JwtUtil.parseToken(token);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        int homeworkLeft = 0, homeworkDone = 0, homeworkOverTime = 0;

        for (Homework homework : homeworkRepository.findAll()) {
            if(user.getUserType() != 0) break;
            char userClass = user.getUserNumber().toString().charAt(1);
            Date date = (Date) Homework.class.getDeclaredMethod("getHomework_"+userClass+"_deadline").invoke(homework);

            if(homework.getHomeworkType() == 1) { // 팀 과제
                if (getCurrentMember(userId, homework.getId()).isPresent()) {
                    Member member = getCurrentMember(userId, homework.getId()).get();
                    if (fileMultiRepository.existsByHomeworkIdAndTeamId(homework.getId(), member.getTeamId())) {
                        homeworkDone++;
                        continue;
                    }
                }
                if(date.after(new java.util.Date(System.currentTimeMillis()))) {
                    homeworkLeft++;
                } else {
                    homeworkOverTime++;
                }
            } else { // 개인, 실험 과제
                if(fileSingleRepository.existsByHomeworkIdAndUserId(homework.getId(), userId)) {
                    homeworkDone++;
                } else {
                    if(date.after(new java.util.Date(System.currentTimeMillis()))) {
                        homeworkLeft++;
                    } else {
                        homeworkOverTime++;
                    }
                }
            }
        }

        return UserResponse.builder()
                .userId(user.getId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .userNumber(user.getUserNumber())
                .userType(user.getUserType())
                .homeworkLeft(homeworkLeft)
                .homeworkDone(homeworkDone)
                .homeworkOverTime(homeworkOverTime)
                .build();
    }

    @Override
    public UserResponse getUser(String token, Integer userId) {
        userRepository.findById(JwtUtil.parseToken(token)).filter(user -> user.getUserType().equals(1))
                .orElseThrow(PermissionDeniedException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return UserResponse.builder()
                .userId(user.getId())
                .userNumber(user.getUserNumber())
                .userName(user.getUserName())
                .build();
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        userRepository.findByUserEmail(signUpRequest.getUserEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        userRepository.findByUserNumber(signUpRequest.getUserNumber()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        authEmailRepository.findByAuthEmail(signUpRequest.getUserEmail())
                .filter(s -> s.getAuthState().equals("Authorized")).orElseThrow(InvalidAuthEmailException::new);

        authCodeRepository.findByUserNumberAndAuthCode(signUpRequest.getUserNumber(), signUpRequest.getAuthCode())
                .orElseThrow(InvalidAuthCodeException::new);

        String userNumber = signUpRequest.getUserNumber().toString();
        if (userNumber.length() != 4) throw new InvalidUserNumberException();
        int userClass = Integer.parseInt(userNumber.substring(0, 2));
        if (userClass < 11 || userClass > 14) throw new InvalidUserNumberException();

        userRepository.save(
                User.builder()
                .userEmail(signUpRequest.getUserEmail())
                .userPw(PasswordEncoder.encode(signUpRequest.getUserPw()))
                .userName(signUpRequest.getUserName())
                .userNumber(signUpRequest.getUserNumber())
                .userType(0)
                .build()
        );
    }

    @Override
    public void authEmail(String email) {
        String code = randomCode();

        userRepository.findByUserEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        authEmailRepository.findByAuthEmail(email).ifPresent(authEmail -> {
            authEmailRepository.delete(authEmail);
        });

        authEmailRepository.save(
                AuthEmail.builder()
                        .authEmail(email)
                        .emailCode(code)
                        .authState("UnAuthorized")
                        .build()
        );

        EmailUtil.sendMail(email, code);

        new Thread(() -> {
            try {
                Thread.sleep(300000);
                AuthEmail emailAuth = authEmailRepository.findByAuthEmail(email).orElseThrow(RuntimeException::new);
                authEmailRepository.delete(emailAuth);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void validEmail(String email, String code) {
        AuthEmail auth = authEmailRepository.findByAuthEmailAndEmailCode(email, code)
                .orElseThrow(UserNotFoundException::new);

        auth.setAuthState("Authorized");
        authEmailRepository.save(auth);
    }

    private String randomCode() {
        StringBuilder code = new StringBuilder();
        String[] codeList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int random;
        for (int i = 0; i < 6; i++) {
            random = (int) (Math.random() * codeList.length);
            code.append(codeList[random]);
        }
        return code.toString();
    }

    public Optional<Member> getCurrentMember(Integer uuid, Integer homeworkId) {
        List<Team> teams = teamRepository.findByHomeworkId(homeworkId).orElseGet(ArrayList::new);
        List<Integer> teamIds = new ArrayList<>();
        for (Team t : teams) teamIds.add(t.getId());

        return memberRepository.findByUserIdAndTeamIdIn(uuid, teamIds);
    }

}
