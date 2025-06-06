package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.ReminderModel;

import java.util.List;

public interface ReminderRepository {
    ReminderModel findById(Integer id);
    ReminderModel saveReminder(ReminderModel reminderModel);
    //void delete(ReminderModel);
    ReminderModel getHabitReminder(Integer habitId);
    List<ReminderModel> getUserReminder(Integer userId);

    /*ReminderModel updateReminder(ReminderModel reminderModel, Integer id);*/
    void delete(Integer id);
    void deleteReminderChild(Integer habitId);
}
