package kr.hs.dsm_scarfs.service.evaluation;

import kr.hs.dsm_scarfs.domain.entitys.*;
import kr.hs.dsm_scarfs.domain.payload.request.EvaluationMutualRequest;
import kr.hs.dsm_scarfs.domain.payload.request.EvaluationSelfRequest;
import kr.hs.dsm_scarfs.domain.repository.*;
import kr.hs.dsm_scarfs.exception.*;
import kr.hs.dsm_scarfs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private EvaluationSelfRepository evaluationSelfRepository;
    @Autowired
    private EvaluationMutualRepository evaluationMutualRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void evaluationSelf(String token, EvaluationSelfRequest evaluationSelfRequest) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        Integer homeworkId = evaluationSelfRequest.getHomeworkId();
        Integer scientificAccuracy = evaluationSelfRequest.getScientificAccuracy();
        Integer communication = evaluationSelfRequest.getCommunication();
        Integer attitude = evaluationSelfRequest.getAttitude();

        homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);

        evaluationSelfRepository.findByUserIdAndHomeworkId(user.getId(), homeworkId).ifPresent(evaluationSelf -> {
            throw new AlreadyEvaluationException();
        });

        evaluationSelfRepository.save(
                EvaluationSelf.builder()
                .homeworkId(homeworkId)
                .userId(user.getId())
                .scientificAccuracy(scientificAccuracy)
                .communication(communication)
                .attitude(attitude)
                .build()
        );
    }

    @Override
    public void evaluationMutual(String token, EvaluationMutualRequest evaluationMutualRequest) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findById(evaluationMutualRequest.getTargetUuid()).orElseThrow(TargetNotFoundException::new);
        Homework homework = homeworkRepository.findById(evaluationMutualRequest.getHomeworkId()).orElseThrow(HomeworkNotFoundException::new);
        Integer cooperation = evaluationMutualRequest.getCooperation();
        Integer communication = evaluationMutualRequest.getCommunication();

        evaluationMutualRepository.findByUserIdAndTargetIdAndHomeworkId(user.getId(), target.getId(), homework.getId())
                .ifPresent(evaluationMutual -> {
                    throw new AlreadyEvaluationException();
                });

        if (user.getId().equals(target.getId())) {
            List<Team> teams = teamRepository.findByHomeworkId(evaluationMutualRequest.getHomeworkId()).orElseGet(ArrayList::new);
            List<Integer> teamIds = new ArrayList<>();
            for (Team t : teams) teamIds.add(t.getId());

            Team targetTeam = teamRepository.findById(
                    memberRepository.findByUserIdAndTeamIdIn(user.getId(), teamIds).orElseThrow(MemberNotFoundException::new).getTeamId()
            ).orElseThrow(TeamNotFoundException::new);

            target = userRepository.findById(targetTeam.getLeaderId()).orElseThrow(UserNotFoundException::new);
        }

        evaluationMutualRepository.save(
                EvaluationMutual.builder()
                .userId(user.getId())
                .targetId(target.getId())
                .homeworkId(homework.getId())
                .cooperation(cooperation)
                .communication(communication)
                .build()
        );
    }
}
