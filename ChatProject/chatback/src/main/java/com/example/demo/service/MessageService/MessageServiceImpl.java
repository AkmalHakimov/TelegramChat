package com.example.demo.service.MessageService;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepo;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    @Override
    public HttpEntity<?> getMessages(String key, String selectedUserId) {
        try {
            updateMessage(key,selectedUserId);
            return ResponseEntity.ok(messageRepo.getMessages(UUID.fromString(key), UUID.fromString(selectedUserId)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong!");
        }
    }

    @Override
    public void updateMessage(String key, String selectedUserId) {
        Message message = messageRepo.getUpdateMessage(UUID.fromString(key), UUID.fromString(selectedUserId));
        if(message!=null){
        messageRepo.save(Message.builder()
                        .toId(message.getToId())
                        .id(message.getId())
                        .text(message.getText())
                        .fromId(message.getFromId())
                        .active(false)
                        .createdAt(message.getCreatedAt())
                .build());
        }
    }

    @Override
    public HttpEntity<?> postMessage(String key, Message message) {
       try {
           messageRepo.save(Message.builder()
                   .fromId(userRepo.findById(UUID.fromString(key)).orElseThrow())
                   .text(message.getText())
                   .toId(message.getToId())
                   .build());
           String x = "/topics/" + message.getToId();
           messagingTemplate.convertAndSend(x, message);
           return ResponseEntity.ok("");
       }catch (Exception e){
           e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong!");
       }
    }
}
