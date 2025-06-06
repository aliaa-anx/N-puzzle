package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.GoalModel;
import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;

import java.util.List;

public interface GoalRepository {
    GoalModel findById(Integer id);
    GoalModel save(GoalModel habit);
    GoalModel getGoal(Integer goalId);
    List<GoalModel> getUserGoals(Integer userId);
    void deleteGoal(Integer id);
}
