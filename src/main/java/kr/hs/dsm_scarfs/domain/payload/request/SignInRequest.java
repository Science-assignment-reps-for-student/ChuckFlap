package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {
    private String userEmail;
    private String userPw;

    @Builder
    public SignInRequest(String userEmail, String userPw) {
        this.userEmail = userEmail;
        this.userPw = userPw;
    }
}
