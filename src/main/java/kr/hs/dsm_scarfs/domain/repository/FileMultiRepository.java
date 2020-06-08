package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.FileMulti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileMultiRepository extends JpaRepository<FileMulti, Integer> {
    boolean existsByHomeworkIdAndTeamId(Integer homeworkId, Integer teamId);
}
