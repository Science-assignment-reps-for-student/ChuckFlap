package kr.hs.dsm_scarfs.service.message;

import kr.hs.dsm_scarfs.domain.entitys.Message;
import kr.hs.dsm_scarfs.domain.entitys.User;
import kr.hs.dsm_scarfs.domain.payload.request.MessageRequest;
import kr.hs.dsm_scarfs.domain.payload.response.MessageListResponse;
import kr.hs.dsm_scarfs.domain.payload.response.MessageResponse;
import kr.hs.dsm_scarfs.domain.repository.MessageRepository;
import kr.hs.dsm_scarfs.domain.repository.UserRepository;
import kr.hs.dsm_scarfs.exception.MessageNotFoundException;
import kr.hs.dsm_scarfs.exception.PermissionDeniedException;
import kr.hs.dsm_scarfs.exception.UserNotFoundException;
import kr.hs.dsm_scarfs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageListResponse> getMessageList(String token) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        List<MessageListResponse> messageListResponses = new ArrayList<>();

        if (user.getUserType() == 0) { // 요청한 사람이 학생 이라면
            messageRepository.findFirstByUserIdOrderByMessageTimeDesc(user.getId()).ifPresent(message -> messageListResponses.add(
                    MessageListResponse.builder()
                    .userId(user.getId())
                    .userNumber(user.getUserNumber())
                    .userName(user.getUserName())
                    .message(message.getMessage())
                    .messageTime(message.getMessageTime().getTime())
                    .isShow(user.getUserType().equals(message.getMessageType()) || message.isShow())
                    .build()
            ));
        } else { // 요청한 사람이 선생님 이라면
            for (User target : userRepository.findStudent()) {
                Message message = messageRepository.findFirstByUserIdOrderByMessageTimeDesc(target.getId())
                        .orElseThrow(MessageNotFoundException::new);
                messageListResponses.add(
                        MessageListResponse.builder()
                        .userId(target.getId())
                        .userNumber(target.getUserNumber())
                        .userName(target.getUserName())
                        .message(message.getMessage())
                        .messageTime(message.getMessageTime().getTime())
                        .isShow(user.getUserType().equals(message.getMessageType()) || message.isShow())
                        .build()
                );
            }
        }

        return messageListResponses;
    }

    @Override
    public List<MessageResponse> getChats(Integer userId, String token) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        if(user.getUserType() == 0 && !user.getId().equals(userId)) throw new PermissionDeniedException();
        List<MessageResponse> messageResponses = new ArrayList<>();

        for (Message message : messageRepository.findByUserIdOrderByMessageTimeAsc(userId)) {
            messageResponses.add(
                    MessageResponse.builder()
                    .messageId(message.getId())
                    .message(message.getMessage())
                    .messageType(message.getMessageType())
                    .messageTime(message.getMessageTime().getTime())
                    .build()
            );
            if (!user.getUserType().equals(message.getMessageType())) {
                message.setShow(true);
                messageRepository.save(message);
            }
        }

        return messageResponses;
    }

    @Override
    public void readMessage(String token, Integer messageId) {
        User user = userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);
        Message message = messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);
        if(user.getUserType().equals(message.getMessageType())) return;

        message.setShow(true);
        messageRepository.save(message);
    }

    @Override
    public MessageResponse chat(Integer userId, MessageRequest messageRequest) {
        User user = userRepository.findById(JwtUtil.parseToken(messageRequest.getToken()))
                .orElseThrow(UserNotFoundException::new);
        if(user.getUserType() == 0 && !user.getId().equals(userId)) {
            return MessageResponse.builder()
                    .errorMessage("Permission Denied")
                    .build();
        }

        Message message = messageRepository.save(
                Message.builder()
                .userId(userId)
                .message(messageRequest.getMessage())
                .messageType(user.getUserType())
                .messageTime(new Date())
                .isShow(false)
                .build()
        );

        return MessageResponse.builder()
                .messageId(message.getId())
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .messageTime(message.getMessageTime().getTime())
                .build();
    }
}
