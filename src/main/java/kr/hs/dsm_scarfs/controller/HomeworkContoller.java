package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.response.HomeworkResponse;
import kr.hs.dsm_scarfs.service.homework.HomeworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkContoller {

    @Autowired
    private HomeworkServiceImpl homeworkService;

    @GetMapping
    public List<HomeworkResponse> getHomeworks(@RequestHeader("Authorization") String token) {
        return homeworkService.getHomeworks(token);
    }
}
