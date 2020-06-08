package kr.hs.dsm_scarfs.service.user;

import kr.hs.dsm_scarfs.domain.payload.request.SignUpRequest;
import kr.hs.dsm_scarfs.domain.payload.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse getUser(String token);
    UserResponse getUser(String token, Integer userId);
    void signUp(SignUpRequest signUpRequest);
    void authEmail(String email);
    void validEmail(String email, String code);
}
