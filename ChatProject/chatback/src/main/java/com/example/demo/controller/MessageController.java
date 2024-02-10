package com.example.demo.controller;


import com.example.demo.config.Db;
import com.example.demo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/test")
    public void test(){
        messagingTemplate.convertAndSend("/topics/chat", "asadbek");
    }

    @PostMapping()
    public HttpEntity<?> postMessage(@RequestHeader String key, @RequestBody Message message) throws SQLException {
        try{
            PreparedStatement preparedStatement = Db.getPreparedStatement("insert into messages (id,from_id,to_id,text) values (cast(? as uuid),cast(? as uuid),cast(? as uuid),?)");
            preparedStatement.setString(1,UUID.randomUUID().toString());
            preparedStatement.setString(2,key);
            preparedStatement.setString(3,message.getToId().toString());
            preparedStatement.setString(4,message.getText());
            preparedStatement.executeUpdate();
            String x = "/topics/" + message.getToId();
            messagingTemplate.convertAndSend(x, message);
            return ResponseEntity.ok("");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong!");
        }


    }

    @GetMapping("/{selectedUserId}")
    public HttpEntity<?> getMessages(@RequestHeader String key, @PathVariable String selectedUserId) throws SQLException {
        try {

            updateMessage(key,selectedUserId);
            List<Message> messages = new ArrayList<>();
            PreparedStatement preparedStatement = Db.getPreparedStatement("select *\n" +
                    "from messages\n" +
                    "where to_id = cast(? as uuid) and from_id = cast(? as uuid)\n" +
                    "   or from_id = cast(? as uuid) and to_id = cast(? as uuid)\n" +
                    "order by created_at");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,selectedUserId);
            preparedStatement.setString(3,key);
            preparedStatement.setString(4,selectedUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                messages.add(Message.builder()
                        .id(UUID.fromString(resultSet.getString("id")))
                        .toId(UUID.fromString(resultSet.getString("to_id")))
                        .fromId(UUID.fromString(resultSet.getString("from_id")))
                        .text(resultSet.getString("text"))
                        .active(resultSet.getBoolean("active"))
                        .createdAt(resultSet.getTimestamp("created_at"))
                        .build());
            }
            return ResponseEntity.ok(messages);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong!");
        }
    }

    private void updateMessage(String key, String selectedUserId) throws SQLException {
        PreparedStatement preparedStatement = Db.getPreparedStatement("update messages set active = false where to_id = cast(? as uuid) and from_id = cast(? as uuid)");
        preparedStatement.setString(1,key);
        preparedStatement.setString(2,selectedUserId);
        preparedStatement.executeUpdate();
    }

}
