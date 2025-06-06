package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringGoalRepository extends JpaRepository<GoalEntity, Integer> {

    @Query(value = "SELECT * FROM goals WHERE id = ?1 ",nativeQuery = true)
    GoalEntity getGoal(Integer goalId);

    @Query(value = "SELECT * FROM goals WHERE user_id = ?1 ",nativeQuery = true)
    List<GoalEntity> getUserGoals(Integer userId);
}
