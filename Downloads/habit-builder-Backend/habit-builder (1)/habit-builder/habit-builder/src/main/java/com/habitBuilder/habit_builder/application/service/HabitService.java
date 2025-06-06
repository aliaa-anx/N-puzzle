package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.repository.HabitRepository;

import java.util.List;

public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void saveHabit(HabitModel habit) {
        habitRepository.save(habit);
    }

    // when I want to increment the streak of this habit
    public void markAsCompleted(HabitModel habitModel){
//        habitModel.markAsCompleted();
        habitRepository.incrementStreak(habitModel);
    }
//    public List<HabitModel> viewRecommendations(Integer userId){
//        return habitRepository.viewRecommendations(userId);
//    }
    public void deleteHabit(Integer id) {
//        ReminderModel reminderModel = reminderRepository.findById(id);
//        if (reminderModel != null) {
//            reminderRepository.delete(reminderModel);
//        }
        habitRepository.delete(id);
    }

    public List<HabitModel> getUserHabit(Integer userId){
        //Optional<ReminderModel> re = Optional.ofNullable(this.reminderRepository.getHabitReminder(habitId));
        return habitRepository.getUserHabit(userId);
    }

    public void addDays(Integer id){
        habitRepository.addDays(id);
    }
    public void deleteDays(Integer id){
        habitRepository.deleteDays(id);
}
}
