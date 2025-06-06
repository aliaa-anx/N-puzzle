package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.model.TemplateModel;
import com.habitBuilder.habit_builder.domain.repository.TemplateRepository;
import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;
import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ReminderEntity;
import com.habitBuilder.habit_builder.infrastructure.model.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TemplateJpaRepository implements TemplateRepository {
    private final SpringTemplateRepository srpingRepo;
    private final SpringGoalRepository springGoalRepo;

    public TemplateJpaRepository(SpringTemplateRepository srpingRepo, SpringGoalRepository goalRepo){
        this.srpingRepo = srpingRepo;
        this.springGoalRepo = goalRepo;
    }

    @Override
    public List<TemplateModel> findAll() {
        List<TemplateEntity> templateEntities = srpingRepo.findAll();
        return TemplateEntity.toDomain(templateEntities);
    }

    @Override
    public List<TemplateModel> viewRecommendations(Integer userId){
        List<GoalEntity> userGoals =  springGoalRepo.getUserGoals(userId);
        List<TemplateEntity> recommendedHabits = new ArrayList<>();
        for(GoalEntity userGoal : userGoals){
            Integer categoryId = userGoal.toDomain().getHabitCategory();
            // to prevent duplication
            if(!recommendedHabits.containsAll(srpingRepo.getTemplatesByCategory(categoryId)))
                recommendedHabits.addAll(srpingRepo.getTemplatesByCategory(categoryId));
        }
        return TemplateEntity.toDomain(recommendedHabits);
    }
}
