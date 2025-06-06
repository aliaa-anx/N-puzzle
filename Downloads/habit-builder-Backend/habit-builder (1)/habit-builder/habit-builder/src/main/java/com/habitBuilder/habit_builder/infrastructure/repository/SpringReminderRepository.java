package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ReminderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringReminderRepository extends JpaRepository<ReminderEntity, Integer> {
    @Query(value = "SELECT * FROM reminders WHERE habit_id = ?1 ",nativeQuery = true)
    ReminderEntity getHabitReminder(Integer habitId);

    @Query(value = "SELECT * FROM reminders WHERE user_id = ?1 ",nativeQuery = true)
    List<ReminderEntity> getUserReminder(Integer userId);

    /*@Modifying
    @Transactional
    @Query(value = "DELETE FROM reminders WHERE habit_id = ?1 ", nativeQuery = true)
    void deleteReminderChild(Integer habitId);*/
}
