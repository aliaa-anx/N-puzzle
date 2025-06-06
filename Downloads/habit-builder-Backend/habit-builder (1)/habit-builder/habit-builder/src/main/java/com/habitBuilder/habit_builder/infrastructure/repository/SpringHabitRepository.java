package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringHabitRepository extends JpaRepository<HabitEntity, Integer> {
    // should be replaced by habit templates when they are done
//    @Query(value = "SELECT * FROM habits WHERE category_id = ?1 ",nativeQuery = true)
//    List<HabitEntity> getHabitsByCategory(Integer categoryId);
    @Query(value = "SELECT * FROM habits WHERE user_id = ?1 ",nativeQuery = true)
    List<HabitEntity> getUserHabit(Integer userId);
}
