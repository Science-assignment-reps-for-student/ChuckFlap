package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.EvaluationSelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationSelfRepository extends JpaRepository<EvaluationSelf, Integer> {
    Optional<EvaluationSelf> findByUserIdAndHomeworkId(Integer uuid, Integer homeworkId);
    void deleteByUserIdAndHomeworkId(Integer userId, Integer homeworkId);
}
