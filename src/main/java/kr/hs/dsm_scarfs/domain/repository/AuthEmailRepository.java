package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.AuthEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthEmailRepository extends JpaRepository<AuthEmail, Integer> {
    Optional<AuthEmail> findByAuthEmail(String mail);
    Optional<AuthEmail> findByAuthEmailAndEmailCode(String email, String emailCode);
}
