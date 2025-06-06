package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.GoalModel;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goals")
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer habitCategoryId;
    private String title;

    // this won't show up in the database don't worry it's just a foreign key :)
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "habitCategoryId", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    public GoalEntity(GoalModel goalModel) {
        this.id = goalModel.getId();
        this.userId = goalModel.getUserId();
        this.habitCategoryId = goalModel.getHabitCategory();
        this.title = goalModel.getTitle();
    }
    public GoalEntity(){}

    public GoalModel toDomain() {
        return new GoalModel(id, userId, habitCategoryId, title);
    }

    public static GoalEntity fromDomain(GoalModel goalModel) {
        return new GoalEntity(goalModel);
    }
    public static List<GoalModel> toDomain(List<GoalEntity> entityList) {
        List<GoalModel> domainList = new ArrayList<>();
        for (GoalEntity entity : entityList) {
            domainList.add(entity.toDomain()); // use the single converter
        }
        return domainList;
    }
}
