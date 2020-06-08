package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<List<Member>> findByTeamId(Integer teamId);
    Optional<Member> findByUserIdAndTeamIdIn(Integer userId, List<Integer> teamId);
    Optional<Member> findByTeamIdAndUserId(Integer teamId, Integer userId);
}
