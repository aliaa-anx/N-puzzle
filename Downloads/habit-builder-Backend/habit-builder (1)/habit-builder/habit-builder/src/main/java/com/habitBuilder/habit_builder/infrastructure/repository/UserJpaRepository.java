package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.model.UserModel;
import com.habitBuilder.habit_builder.domain.repository.HabitRepository;
import com.habitBuilder.habit_builder.domain.repository.UserRepository;
import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import com.habitBuilder.habit_builder.infrastructure.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserJpaRepository implements UserRepository {

    private final SpringUserRepository springRepo;

    public UserJpaRepository(SpringUserRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public UserModel findById(Integer id) {
        return springRepo.findById(id).get().toDomain();
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = UserEntity.fromDomain(user);
        return springRepo.save(entity).toDomain();
    }

    @Override
    public UserModel login(UserModel user){
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity response = springRepo.login(user.getName(), user.getPassword());
        if(response != null)
            return response.toDomain();
        else
            return new UserModel();
    }
}