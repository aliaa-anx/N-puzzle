package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.GoalModel;
import com.habitBuilder.habit_builder.domain.repository.GoalRepository;
import com.habitBuilder.habit_builder.infrastructure.model.GoalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalJpaRepository implements GoalRepository {
    private final SpringGoalRepository springRepo;

    public GoalJpaRepository(SpringGoalRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public GoalModel findById(Integer id) {
        return springRepo.findById(id).get().toDomain();
    }

    @Override
    public GoalModel save(GoalModel goalModel) {
        GoalEntity entity = GoalEntity.fromDomain(goalModel);
        return springRepo.save(entity).toDomain();
    }

    @Override
    public GoalModel getGoal(Integer goalId){
        return springRepo.getGoal(goalId).toDomain();
    }

    @Override
    public List<GoalModel> getUserGoals(Integer userId){
        List<GoalEntity> goalsList =  springRepo.getUserGoals(userId);
        return GoalEntity.toDomain(goalsList);
    }

    @Override
    public void deleteGoal(Integer goalId){
        springRepo.deleteById(goalId);
    }
}
