package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.HabitModel;

import java.util.List;

public interface HabitRepository {
    HabitModel findById(Integer id);
    HabitModel save(HabitModel habit);
    void incrementStreak(HabitModel habit);
//    List<HabitModel> viewRecommendations(Integer userId);
    void delete(Integer id);
    public List<HabitModel> getUserHabit(Integer userId);
    void addDays(Integer id);
    void deleteDays(Integer id);
}
