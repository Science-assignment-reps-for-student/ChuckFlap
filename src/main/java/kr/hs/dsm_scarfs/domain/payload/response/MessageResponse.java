package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageResponse {

    private Integer messageId;
    private String message;
    private Integer messageType;
    private Long messageTime;
    private String errorMessage;

    @Builder
    public MessageResponse(Integer messageId, String message, Integer messageType, Long messageTime, String errorMessage) {
        this.messageId = messageId;
        this.message = message;
        this.messageType = messageType;
        this.messageTime = messageTime;
        this.errorMessage = errorMessage;
    }
}
