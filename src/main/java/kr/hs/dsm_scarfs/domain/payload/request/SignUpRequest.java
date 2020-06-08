package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String userEmail;
    private String userPw;
    private String userName;
    private Integer userNumber;
    private String authCode;

    @Builder
    public SignUpRequest(String userEmail, String userPw, String userName, Integer userNumber, String authCode) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.userNumber = userNumber;
        this.authCode = authCode;
    }
}
