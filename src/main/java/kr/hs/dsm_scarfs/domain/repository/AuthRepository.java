package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String userEmail);

}
