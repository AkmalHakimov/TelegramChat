package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    @Query(value = "select id from users where phone = :phone and password = :password",nativeQuery = true)
    Optional checkIfUserCanAccess(String phone, String password);

    @Query(value = "select u.id, u.first_name, u.last_name,password ,\n" +
            "       u.phone, (array_agg(m.text order by m.created_at desc))[1] as last_text,\n" +
            "       (array_agg(m.created_at order by m.created_at desc))[1] as\n" +
            "           last_time, (select coalesce(count(m1.id),0) from messages m1 where m1.to_id_id =\n" +
            ":toId and m1.active and m1.from_id_id = u.id ) as count from users u left join messages m on u.id = m.from_id_id\n" +
            "and m.to_id_id = :toId or m.from_id_id = :toId and m.to_id_id = u.id where u.id <> :toId group by u.id order by count desc, last_time desc nulls last",nativeQuery = true)
    List<UserProjection> getUsers(UUID toId);
}
