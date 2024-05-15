package com.example.demo.service.MessageService;

import com.example.demo.entity.Message;
import org.springframework.http.HttpEntity;

public interface MessageService {
    HttpEntity<?> postMessage(String key, Message message);

    void updateMessage(String key, String selectedUserId);

    HttpEntity<?> getMessages(String key, String selectedUserId);
}
