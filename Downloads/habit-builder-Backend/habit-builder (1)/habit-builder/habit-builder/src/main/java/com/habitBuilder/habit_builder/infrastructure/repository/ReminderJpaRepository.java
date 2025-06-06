package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ReminderModel;
import com.habitBuilder.habit_builder.domain.repository.ReminderRepository;
import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ReminderEntity;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ReminderJpaRepository implements ReminderRepository {

    public final SpringReminderRepository springRepo;
    private final SpringHabitRepository springHabitRepo;

    ReminderJpaRepository (SpringReminderRepository springRepo, SpringHabitRepository springHabitRepo){
        this.springRepo = springRepo;
        this.springHabitRepo = springHabitRepo;
    }


    @Override
    public ReminderModel findById(Integer id) {
        return springRepo.findById(id).get().toDomain();
    }

    @Override
    public ReminderModel getHabitReminder(Integer habitId) {
        //ReminderEntity re = ;
        return springRepo.getHabitReminder(habitId).toDomain();
    }

    @Override
    public List<ReminderModel> getUserReminder(Integer userId) {
        List<ReminderEntity> reminderEntityList = springRepo.getUserReminder(userId);
        List<ReminderEntity> existReminder = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now(); // Get the current time

        for (ReminderEntity reminder : reminderEntityList) {
            if (!"none".equals(reminder.getDuration())) {
                LocalDateTime lastReminderTime = reminder.getLastReminder(); // Assuming this method exists

                if (lastReminderTime != null) { // Check that lastReminderTime is not null
                    // Calculate the difference in minutes
                    long minutesDifference = Duration.between(lastReminderTime, currentTime).toMinutes();

                    // Convert duration from string to long
                    long duration = Long.parseLong(reminder.getDuration());

                    // Check if minutesDifference is greater than the duration
                    if (minutesDifference > duration) {
                        reminder.setLastReminder(currentTime);
                        existReminder.add(reminder);
                        //reminder.setLastReminder(currentTime); // Update lastReminder to now
                        //this.saveReminder(reminder); // Save the updated reminder
                    }
                }
            }
        }

        return ReminderEntity.toDomain(existReminder);
    }

    @Override
    public ReminderModel saveReminder(ReminderModel reminderModel) {

        ReminderEntity reminderEntity = ReminderEntity.fromDomain(reminderModel);
        /*Integer idValue = reminderEntity.getId();
        reminderEntity.setId(idValue);*/
        return springRepo.save(reminderEntity).toDomain();
    }

    /*@Override
    public ReminderModel updateReminder(ReminderModel reminderModel, Integer id) {

        reminderModel.setId(id);
        ReminderEntity reminderEntity = ReminderEntity.fromDomain(reminderModel);
        /*Integer idValue = reminderEntity.getId();
        reminderEntity.setId(idValue);
        return springRepo.save(reminderEntity).toDomain();
    }*/

    @Override
    public void delete(Integer id) {
        ReminderEntity reminder = springRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        // Clear the bidirectional link (IMPORTANT)
        HabitEntity habit = reminder.getHabitEntity();
        if (habit != null) {
            habit.setReminderEntity(null);
        }

        springRepo.delete(reminder);
    }

    @Override
    public void deleteReminderChild(Integer habitId) {
        ReminderModel reminderModel = getHabitReminder(habitId);
        if (reminderModel != null) {
            Integer reId = reminderModel.getId();
            this.delete(reId);
        }
        //List<ReminderEntity> reminderEntityList = ReminderEntity.fromDomain(reminderModel);

    }

    /*@Override
    public void delete(ReminderModel reminderModel) {
        ReminderEntity reminder = springRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        /*if (reminder.getHabitEntity() != null) {
            reminder.getHabitEntity().setReminderEntity(null);
        }


        springRepo.deleteById(id);
    }*/


}
