package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.response.NoticeResponse;
import kr.hs.dsm_scarfs.service.notice.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @GetMapping
    public NoticeResponse getNotice() {
        return noticeService.getNotice();
    }

}
