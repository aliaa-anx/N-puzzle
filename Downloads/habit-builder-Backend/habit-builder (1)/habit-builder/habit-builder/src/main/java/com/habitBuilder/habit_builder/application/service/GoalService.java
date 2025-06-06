package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.GoalModel;
import com.habitBuilder.habit_builder.domain.repository.GoalRepository;

import java.util.List;

public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void saveGoal(GoalModel goalModel) {
        goalRepository.save(goalModel);
    }

    public GoalModel getGoal(Integer goalId){
        return goalRepository.getGoal(goalId);
    }

    public List<GoalModel> getUserGoals(Integer userId){
        return goalRepository.getUserGoals(userId);
    }

    public void deleteGoal( Integer goalId){
        goalRepository.deleteGoal(goalId);
    }
}
