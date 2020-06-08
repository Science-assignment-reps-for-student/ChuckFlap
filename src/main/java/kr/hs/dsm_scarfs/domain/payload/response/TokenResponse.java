package kr.hs.dsm_scarfs.domain.payload.response;

import kr.hs.dsm_scarfs.util.JwtUtil;
import lombok.Getter;

@Getter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public TokenResponse(Object data) {
        this.accessToken = JwtUtil.getAccessToken(data);
        this.refreshToken = JwtUtil.getRefreshToken(data);
    }
}
