package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.FileSingle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileSingleRepository extends JpaRepository<FileSingle, Integer> {
    boolean existsByHomeworkIdAndUserId(Integer homeworkId, Integer userId);
}
