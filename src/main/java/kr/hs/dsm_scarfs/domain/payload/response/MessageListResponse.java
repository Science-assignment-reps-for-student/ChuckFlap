package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageListResponse {

    private Integer userId;
    private Integer userNumber;
    private String userName;
    private String message;
    private Long messageTime;
    private boolean isShow;

    @Builder
    public MessageListResponse(Integer userId, Integer userNumber, String userName, String message, Long messageTime, boolean isShow) {
        this.userId = userId;
        this.userNumber = userNumber;
        this.userName = userName;
        this.message = message;
        this.messageTime = messageTime;
        this.isShow = isShow;
    }
}
