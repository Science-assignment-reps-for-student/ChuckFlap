package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.request.MessageRequest;
import kr.hs.dsm_scarfs.domain.payload.response.MessageListResponse;
import kr.hs.dsm_scarfs.domain.payload.response.MessageResponse;
import kr.hs.dsm_scarfs.service.message.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocketController {

    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/message")
    public List<MessageListResponse> getMessageList(@RequestHeader("Authorization") String token) {
        return messageService.getMessageList(token);
    }

    @GetMapping("/message/{userId}")
    public List<MessageResponse> getChats(@PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        return messageService.getChats(userId, token);
    }

    @PostMapping("/message/{messageId}")
    public void readMessage(@RequestHeader("Authorization") String token, @PathVariable Integer messageId) {
        messageService.readMessage(token, messageId);
    }

    @MessageMapping("/send/{userId}")
    @SendTo({"/receive/{userId}", "/receive/admin"})
    public MessageResponse chat(@DestinationVariable Integer userId, MessageRequest messageRequest) {
        return messageService.chat(userId, messageRequest);
    }
}
