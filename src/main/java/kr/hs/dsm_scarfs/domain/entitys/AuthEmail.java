package kr.hs.dsm_scarfs.domain.entitys;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "auth_emails")
public class AuthEmail {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String authEmail;

    @Column(nullable = false, length = 36)
    private String emailCode;

    @Column
    private String authState;

    @Builder
    public AuthEmail(String authEmail , String emailCode, String authState) {
        this.authEmail = authEmail;
        this.emailCode = emailCode;
        this.authState = authState;
    }
}
