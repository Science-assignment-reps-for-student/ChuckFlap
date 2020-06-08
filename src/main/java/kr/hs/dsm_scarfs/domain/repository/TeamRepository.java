package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findById(Integer teamId);
    Optional<Team> findByIdAndLeaderId(Integer teamId, Integer uuid);
    Optional<List<Team>> findByHomeworkId(Integer homeworkId);
    Optional<Team> findByTeamNameAndHomeworkId(String teamName, Integer homeworkId);
}
