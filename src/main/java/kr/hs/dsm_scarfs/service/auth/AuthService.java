package kr.hs.dsm_scarfs.service.auth;

import kr.hs.dsm_scarfs.domain.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse login(String userEmail, String userPw);
    TokenResponse refreshToken(String token);
}
