package com.example.demo.controller;

import com.example.demo.config.Db;
import com.example.demo.entity.User;
import com.example.demo.payload.response.ResUser;
import com.example.demo.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers(@RequestHeader String key) throws SQLException {
        return userService.getUsers(key);
//        List<ResUser> users = new ArrayList<>();
//        PreparedStatement preparedStatement = Db.getPreparedStatement("select u.id,\n" +
//                "       u.first_name,\n" +
//                "       u.last_name,\n" +
//                "       u.phone,\n" +
//                "       (array_agg(m.text order by m.created_at desc))[1]       as last_text,\n" +
//                "       (array_agg(m.created_at order by m.created_at desc))[1] as last_time,\n" +
//                "        (\n" +
//                "        select coalesce(count(m1.id),0) from messages m1 where m1.to_id = cast(? as uuid) and m1.active and m1.from_id = u.id\n" +
//                "        ) as count\n" +
//                "from users u\n" +
//                "         left join messages m on u.id = m.from_id\n" +
//                "                                     and m.to_id = cast(? as uuid) or\n" +
//                "                                 m.from_id = cast(? as uuid) and m.to_id = u.id\n" +
//                "where u.id <> cast(? as uuid)\n" +
//                "group by u.id order by count desc, last_time desc nulls last");
//        preparedStatement.setString(1,key);
//        preparedStatement.setString(2,key);
//        preparedStatement.setString(3,key);
//        preparedStatement.setString(4,key);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()){
//            users.add(ResUser.builder()
//                    .id(UUID.fromString(resultSet.getString("id")))
//                    .phone(resultSet.getString("phone"))
//                    .firstName(resultSet.getString("first_name"))
//                    .lastName(resultSet.getString("last_name"))
//                    .count(resultSet.getInt("count"))
//                    .lastText(resultSet.getString("last_text"))
//                    .createdAt(resultSet.getTimestamp("last_time"))
//                    .build());
//        }
//        return ResponseEntity.ok(users);
    }
}
