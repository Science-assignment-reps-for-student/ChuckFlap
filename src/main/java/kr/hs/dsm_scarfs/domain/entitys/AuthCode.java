package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "auth_codes")
public class AuthCode {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userNumber;

    @Column
    private String authCode;

    @Builder
    public AuthCode(Integer userNumber, String authCode) {
        this.userNumber = userNumber;
        this.authCode = authCode;
    }
}
