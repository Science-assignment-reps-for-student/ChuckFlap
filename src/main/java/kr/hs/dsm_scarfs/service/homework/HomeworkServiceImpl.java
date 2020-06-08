package kr.hs.dsm_scarfs.service.homework;

import kr.hs.dsm_scarfs.domain.entitys.Homework;
import kr.hs.dsm_scarfs.domain.entitys.Member;
import kr.hs.dsm_scarfs.domain.entitys.User;
import kr.hs.dsm_scarfs.domain.payload.response.HomeworkResponse;
import kr.hs.dsm_scarfs.domain.repository.*;
import kr.hs.dsm_scarfs.exception.MemberNotFoundException;
import kr.hs.dsm_scarfs.exception.UserNotFoundException;
import kr.hs.dsm_scarfs.service.user.UserServiceImpl;
import kr.hs.dsm_scarfs.util.JwtUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private FileSingleRepository fileSingleRepository;
    @Autowired
    private FileMultiRepository fileMultiRepository;

    @Autowired
    private UserServiceImpl userService;

    @SneakyThrows
    @Override
    public List<HomeworkResponse> getHomeworks(String token) {
        Integer uuid = JwtUtil.parseToken(token);
        User user = userRepository.findById(uuid).orElseThrow(UserNotFoundException::new);
        String classRoom = user.getUserNumber().toString().split("")[1];
        List<HomeworkResponse> homeworkResults = new ArrayList<>();
        List<Homework> homeworkList = homeworkRepository.findAllByOrderByCreatedAtDesc();
        boolean submissionStatus;
        boolean hasTeam;

        for (Homework homework : homeworkList) {
            if(user.getUserType() == 1) {
                homeworkResults.add(
                        HomeworkResponse.builder()
                                .id(homework.getId())
                                .homeworkTitle(homework.getHomeworkTitle())
                                .homeworkType(homework.getHomeworkType())
                                .homework_deadline(null)
                                .submissionStatus(false)
                                .created_at(homework.getCreatedAt())
                                .hasTeam(false)
                                .build()
                );
                continue;
            }
            if (homework.getHomeworkType() == 1) { // 팀과제
                if(userService.getCurrentMember(uuid, homework.getId()).isPresent()) {
                    Member member = userService.getCurrentMember(uuid, homework.getId()).orElseThrow(MemberNotFoundException::new);
                    submissionStatus = fileMultiRepository.existsByHomeworkIdAndTeamId(homework.getId(), member.getTeamId());
                    hasTeam = true;
                } else {
                    submissionStatus = false;
                    hasTeam = false;
                }
            } else { // 개인과제, 실험과제
                submissionStatus = fileSingleRepository.existsByHomeworkIdAndUserId(homework.getId(), uuid);
                hasTeam = false;
            }
            homeworkResults.add(
                HomeworkResponse.builder()
                    .id(homework.getId())
                    .homeworkTitle(homework.getHomeworkTitle())
                    .homeworkType(homework.getHomeworkType())
                    .homework_deadline((Date) Homework.class.getMethod("getHomework_"+classRoom+"_deadline").invoke(homework))
                    .submissionStatus(submissionStatus)
                    .created_at(homework.getCreatedAt())
                    .hasTeam(hasTeam)
                    .build()
            );
        }
        return homeworkResults;
    }

}
