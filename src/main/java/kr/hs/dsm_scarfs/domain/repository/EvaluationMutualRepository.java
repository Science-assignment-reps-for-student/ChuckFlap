package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.EvaluationMutual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationMutualRepository extends JpaRepository<EvaluationMutual, Integer> {
    Optional<EvaluationMutual> findByUserIdAndTargetIdAndHomeworkId(Integer uuid, Integer targetId, Integer homeworkId);
    void deleteAllByUserIdAndHomeworkId(Integer userId, Integer homeworkId);
}
