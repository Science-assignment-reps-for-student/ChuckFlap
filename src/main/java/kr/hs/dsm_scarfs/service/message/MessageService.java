package kr.hs.dsm_scarfs.service.message;

import kr.hs.dsm_scarfs.domain.payload.request.MessageRequest;
import kr.hs.dsm_scarfs.domain.payload.response.MessageListResponse;
import kr.hs.dsm_scarfs.domain.payload.response.MessageResponse;

import java.util.List;

public interface MessageService {
    List<MessageListResponse> getMessageList(String token);
    List<MessageResponse> getChats(Integer userId, String token);
    void readMessage(String token, Integer messageId);
    MessageResponse chat(Integer userId, MessageRequest messageRequest);
}
