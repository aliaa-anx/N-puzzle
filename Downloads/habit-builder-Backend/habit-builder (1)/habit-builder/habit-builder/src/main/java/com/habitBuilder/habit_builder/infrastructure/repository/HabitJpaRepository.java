package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.repository.HabitRepository;
import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;
import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ReminderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HabitJpaRepository implements HabitRepository {

    private final SpringHabitRepository springRepo;
    private final SpringGoalRepository springGoalRepo;
    private final ReminderJpaRepository reminderJpaRepository;

    public HabitJpaRepository(SpringHabitRepository springRepo, SpringGoalRepository springGoalRepo, ReminderJpaRepository reminderJpaRepository) {
        this.springRepo = springRepo;
        this.springGoalRepo = springGoalRepo;
        this.reminderJpaRepository = reminderJpaRepository;
    }

    @Override
    public HabitModel findById(Integer id) {
        return springRepo.findById(id).get().toDomain();
    }

    @Override
    public HabitModel save(HabitModel habit) {
        HabitEntity entity = HabitEntity.fromDomain(habit);
        return springRepo.save(entity).toDomain();
    }

    @Override
    public void incrementStreak(HabitModel habitModel){
        Optional<HabitEntity> habitEntity = springRepo.findById(habitModel.getId());
        HabitModel habit = habitEntity.get().toDomain();
        habit.setStreak(habit.getStreak() + 1);
        springRepo.save(HabitEntity.fromDomain(habit));
    }

//    @Override
//    public List<HabitModel> viewRecommendations(Integer userId){
//        List<GoalEntity> userGoals =  springGoalRepo.getUserGoals(userId);
//        List<HabitEntity> recommendedHabits = new ArrayList<>();
//        for(GoalEntity userGoal : userGoals){
//            Integer categoryId = userGoal.toDomain().getHabitCategory();
//            // to prevent duplication
//            if(!recommendedHabits.containsAll(springRepo.getHabitsByCategory(categoryId)))
//                recommendedHabits.addAll(springRepo.getHabitsByCategory(categoryId));
//        }
//        return HabitEntity.toDomain(recommendedHabits);
//    }

    @Override
    public void delete(Integer id) {
        HabitEntity habit = springRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        ReminderEntity reminder = habit.getReminderEntity();
        if (reminder != null) {
            reminderJpaRepository.deleteReminderChild(id); // Optional if cascade is set correctly
        }

        springRepo.delete(habit);
    }

    @Override
    public List<HabitModel> getUserHabit(Integer userId) {
        List<HabitEntity> habitEntityList = springRepo.getUserHabit(userId);
        return HabitEntity.toDomain(habitEntityList);
    }

    @Override
    public void addDays(Integer id) {
        HabitModel habitModel = this.findById(id);
        habitModel.setActiveDays(habitModel.getActiveDays()+1);
        this.save(habitModel);
    }
    @Override
    public void deleteDays(Integer id) {
        HabitModel habitModel = this.findById(id);
        habitModel.setActiveDays(habitModel.getActiveDays()-1);
        this.save(habitModel);
    }

//    interface SpringHabitRepository extends JpaRepository<HabitEntity, Integer> {}
}