package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.ResponseModel;
import com.habitBuilder.habit_builder.domain.model.UserModel;
import com.habitBuilder.habit_builder.domain.repository.UserRepository;
import com.habitBuilder.habit_builder.infrastructure.model.UserEntity;
import org.apache.catalina.User;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findUser(Integer id) {
        UserModel user = userRepository.findById(id);
        return user;
    }
    public void saveUser(UserModel model) {
        UserModel user = userRepository.save(model);
    }

    public ResponseModel login(UserModel user){
        UserModel response = userRepository.login(user);
        if(response.getName() != null)
            return new ResponseModel(true, "Login Success");
        else
            return new ResponseModel(false, "Login Failed");
    }
}
