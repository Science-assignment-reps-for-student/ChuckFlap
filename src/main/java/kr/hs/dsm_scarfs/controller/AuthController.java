package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.request.SignInRequest;
import kr.hs.dsm_scarfs.domain.payload.response.TokenResponse;
import kr.hs.dsm_scarfs.service.auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @NotNull SignInRequest userSignIn) {
        return authService.login(userSignIn.getUserEmail(), userSignIn.getUserPw());
    }

    @PutMapping
    public TokenResponse refreshToken(@RequestHeader("Authorization") @NotNull String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
