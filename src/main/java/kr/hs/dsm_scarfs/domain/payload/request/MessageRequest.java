package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {

    private String token;
    private String message;
    private Long messageTime;

    @Builder
    public MessageRequest(String token, String message, Long messageTime) {
        this.token = token;
        this.message = message;
        this.messageTime = messageTime;
    }
}
