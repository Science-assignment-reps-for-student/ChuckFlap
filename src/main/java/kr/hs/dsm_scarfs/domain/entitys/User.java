package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String userEmail;

    @Column
    private String userPw;

    @Column
    private Integer userNumber;

    @Column
    private String userName;

    @Column
    private Integer userType;

    @Builder
    public User(String userEmail, String userPw, Integer userNumber, String userName, Integer userType) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userNumber = userNumber;
        this.userName = userName;
        this.userType = userType;
    }
}
