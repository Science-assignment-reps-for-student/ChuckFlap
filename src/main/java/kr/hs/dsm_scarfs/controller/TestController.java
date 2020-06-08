package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.util.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/encode")
    public String encode(@RequestParam String text) {
        return PasswordEncoder.encode(text);
    }
}
