package kr.hs.dsm_scarfs.service.team;

import kr.hs.dsm_scarfs.domain.entitys.FileMulti;
import kr.hs.dsm_scarfs.domain.entitys.Member;
import kr.hs.dsm_scarfs.domain.entitys.Team;
import kr.hs.dsm_scarfs.domain.entitys.User;
import kr.hs.dsm_scarfs.domain.payload.request.TeamInfoRequest;
import kr.hs.dsm_scarfs.domain.payload.response.MemberResponse;
import kr.hs.dsm_scarfs.domain.payload.response.TeamResponse;
import kr.hs.dsm_scarfs.domain.repository.*;
import kr.hs.dsm_scarfs.service.user.UserServiceImpl;
import kr.hs.dsm_scarfs.util.JwtUtil;
import kr.hs.dsm_scarfs.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private FileMultiRepository fileMultiRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public TeamResponse getTeam(String token, Integer homeworkId) {
        Integer uuid = JwtUtil.parseToken(token);

        Member member = userService.getCurrentMember(uuid, homeworkId).orElseThrow(TeamNotFoundException::new);
        Team team = teamRepository.findById(member.getTeamId()).orElseThrow(TeamNotFoundException::new);
        User leader = userRepository.findById(team.getLeaderId()).orElseThrow(UserNotFoundException::new);
        List<Member> members = memberRepository.findByTeamId(team.getId()).orElseThrow(TeamNotFoundException::new);
        List<MemberResponse> memberResponses = new ArrayList<>();

        for(Member m : members) {
            User user = userRepository.findById(m.getUserId()).get();
            if(user.getId().equals(leader.getId())) continue;

            memberResponses.add(MemberResponse.builder()
            .userId(user.getId())
            .userNumber(user.getUserNumber())
            .userName(user.getUserName())
            .build());
        }

        return TeamResponse.builder()
                .teamId(team.getId())
                .leaderId(leader.getId())
                .leaderNumber(leader.getUserNumber())
                .leaderName(leader.getUserName())
                .teamName(team.getTeamName())
                .members(memberResponses)
                .build();
    }

    @Override
    public void addTeam(String token, TeamInfoRequest teamInfo) {
        Integer uuid = JwtUtil.parseToken(token);
        String teamName = teamInfo.getTeamName();
        Integer homeworkId = teamInfo.getHomeworkId();

        homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);

        userService.getCurrentMember(uuid, homeworkId).ifPresent(member -> {
            throw new UserAlreadyIncludeException();
        });

        teamRepository.findByTeamNameAndHomeworkId(teamName, homeworkId).ifPresent(team -> {
            throw new TeamAlreadyExistsException();
        });

        Team team = teamRepository.save(Team.builder()
            .leaderId(uuid)
            .homeworkId(homeworkId)
            .teamName(teamName)
            .build()
        );

        memberRepository.save(Member.builder()
            .teamId(team.getId())
            .userId(uuid)
            .build()
        );

    }

    @Override
    public void deleteTeam(String token, Integer teamId) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
        checkLeader(user.getId(), team.getId());

        for (FileMulti fileMulti : fileMultiRepository.findByTeamId(teamId)) {
            File file = new File(fileMulti.getSource());
            file.deleteOnExit();
        }

        memberRepository.deleteAll(memberRepository.findByTeamId(team.getId()).orElseGet(ArrayList::new));
        teamRepository.delete(team);
    }

    @Override
    public void addMember(String token, Integer teamId, Integer targetId) {
        User leader = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findById(targetId).orElseThrow(TargetNotFoundException::new);
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
        checkLeader(leader.getId(), team.getId());

        userService.getCurrentMember(target.getId(), team.getHomeworkId()).ifPresent(member -> {
            throw new UserAlreadyIncludeException();
        });

        memberRepository.save(
                Member.builder()
                .teamId(team.getId())
                .userId(target.getId())
                .build()
        );

    }

    @Override
    public void deleteMember(String token, Integer teamId, Integer targetId) {
        User leader = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findById(targetId).orElseThrow(TargetNotFoundException::new);
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
        checkLeader(leader.getId(), team.getId());

        memberRepository.delete(memberRepository.findByTeamIdAndUserId(team.getId(), target.getId())
                .orElseThrow(MemberNotFoundException::new)
        );
    }

    private void checkLeader(Integer uuid, Integer teamId) {
        teamRepository.findByIdAndLeaderId(teamId, uuid).orElseThrow(UserNotLeaderException::new);
    }
}
