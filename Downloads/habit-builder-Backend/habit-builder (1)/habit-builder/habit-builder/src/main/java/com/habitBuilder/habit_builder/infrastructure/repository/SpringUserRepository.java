package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM users WHERE name = ?1 AND password = ?2",nativeQuery = true)
    UserEntity login(String name, String password);
}
