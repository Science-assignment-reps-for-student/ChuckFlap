package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeResponse {

    private String noticeTitle;
    private String notice;

    @Builder
    public NoticeResponse(String noticeTitle, String notice) {
        this.noticeTitle = noticeTitle;
        this.notice = notice;
    }
}
