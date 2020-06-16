package kr.hs.dsm_scarfs.domain.repository;

import kr.hs.dsm_scarfs.domain.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer uuid);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserNumber(Integer userNumber);
    List<User> findByUserNameIsContainingAndUserTypeOrderByUserNumber(String userName, Integer userType);

    @Query(value = "select u.* from users as u inner join messages as m on m.user_id=u.id group by u.id order by m.message_time desc", nativeQuery = true)
    List<User> findStudent();
}
