package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.UserModel;

public interface UserRepository {
    UserModel save(UserModel user);
    UserModel findById(Integer id);
    UserModel login(UserModel user);
}
