package com.example.demo.repository;

import com.example.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepo extends JpaRepository<Message, UUID> {

    @Query(value = "select * from messages where to_id_id = :toId and from_id_id = :fromId",nativeQuery = true)
    Message getUpdateMessage(UUID toId,UUID fromId);

    @Query(value = "select * from messages where to_id_id = :toId and from_id = :fromId or from_id_id = :toId and to_id = :fromId order by created_at",nativeQuery = true)
    List<Message> getMessages(UUID toId,UUID fromId);

}
