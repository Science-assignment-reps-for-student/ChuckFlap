package kr.hs.dsm_scarfs.service.notice;

import kr.hs.dsm_scarfs.domain.entitys.Notice;
import kr.hs.dsm_scarfs.domain.payload.response.NoticeResponse;
import kr.hs.dsm_scarfs.domain.repository.NoticeRepository;
import kr.hs.dsm_scarfs.exception.NoticeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public NoticeResponse getNotice() {

        String noticeTitle, notice;
        List<Notice> noticeList = noticeRepository.findAll();
        if(noticeList.size() == 0) {
            throw new NoticeNotFoundException();
        } else {
            notice = noticeList.get(0).getNotice();
            noticeTitle = noticeList.get(0).getNoticeTitle();
        }

        return NoticeResponse.builder()
                .noticeTitle(noticeTitle)
                .notice(notice)
                .build();
    }
}
