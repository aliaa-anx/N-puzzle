package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.UserModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String number;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HabitEntity> items;

    public UserEntity(UserModel user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.number = user.getNumber();
    }
    public UserEntity(){}

    public UserModel toDomain() {
        return new UserModel(id, name, password, email, number);
    }

    public static UserEntity fromDomain(UserModel user) {
        return new UserEntity(user);
    }
}
