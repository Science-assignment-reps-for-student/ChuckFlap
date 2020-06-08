package kr.hs.dsm_scarfs.service.auth;

import kr.hs.dsm_scarfs.domain.entitys.User;
import kr.hs.dsm_scarfs.domain.payload.response.TokenResponse;
import kr.hs.dsm_scarfs.domain.repository.AuthRepository;
import kr.hs.dsm_scarfs.exception.UserNotFoundException;
import kr.hs.dsm_scarfs.util.JwtUtil;
import kr.hs.dsm_scarfs.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public TokenResponse login(String userEmail, String userPw) {

        User user = authRepository.findByUserEmail(userEmail).filter(data -> PasswordEncoder.checkPassword(data.getUserPw(), userPw))
                .orElseThrow(UserNotFoundException::new);

        return new TokenResponse(user.getId());
    }

    @Override
    public TokenResponse refreshToken(String token) {
        Integer uuid = JwtUtil.parseRefreshToken(token);
        return new TokenResponse(uuid);
    }

}
