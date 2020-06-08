package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.request.SignUpRequest;
import kr.hs.dsm_scarfs.domain.payload.response.UserResponse;
import kr.hs.dsm_scarfs.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public UserResponse getUser(@RequestHeader("Authorization") @NotNull String token) {
        return userService.getUser(token);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@RequestHeader("Authorization") @NotNull String token, @PathVariable Integer userId) {
        return userService.getUser(token, userId);
    }

    @PostMapping
    public void signUp(@RequestBody @NotNull SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
    }

    @PostMapping("/authemail")
    public void authEmail(@RequestParam @NotNull String email){
        userService.authEmail(email);
    }

    @PostMapping("/validemail")
    public void validEmail(@RequestParam @NotNull String email, String code) {
        userService.validEmail(email, code);
    }

}
