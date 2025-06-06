package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.ReminderModel;
import com.habitBuilder.habit_builder.domain.repository.ReminderRepository;
import com.habitBuilder.habit_builder.infrastructure.model.ReminderEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReminderService {

    @Autowired
    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    /*public String getReminders(){
        String n = String.valueOf(reminderRepository.getReminders());
        return n.toUpperCase();
    }*/

    public ReminderModel getReminder(Integer id){
        Optional<ReminderModel> re = Optional.ofNullable(this.reminderRepository.findById(id));
        return re.orElse(new ReminderModel());
    }

    public void saveReminder(ReminderModel reminderModel){
        /*ReminderModel*/


        reminderRepository.saveReminder(reminderModel);
    }

    public void deleteReminder(Integer id) {
//        ReminderModel reminderModel = reminderRepository.findById(id);
//        if (reminderModel != null) {
//            reminderRepository.delete(reminderModel);
//        }
        reminderRepository.delete(id);
    }

    public void deleteReminderChild(Integer habitId) {

       /*ReminderModel reminderModel = reminderRepository.getHabitReminder(habitId);
       if (reminderModel != null) {
           reminderRepository.delete(reminderModel);
       }*/
        reminderRepository.deleteReminderChild(habitId);
    }

    public ReminderModel getHabitReminder(Integer habitId){
        //Optional<ReminderModel> re = Optional.ofNullable(this.reminderRepository.getHabitReminder(habitId));
        return reminderRepository.getHabitReminder(habitId);
    }

    public List<ReminderModel> getUserReminder(Integer userId){
        //Optional<ReminderModel> re = Optional.ofNullable(this.reminderRepository.getHabitReminder(habitId));
        return reminderRepository.getUserReminder(userId);
    }
}
