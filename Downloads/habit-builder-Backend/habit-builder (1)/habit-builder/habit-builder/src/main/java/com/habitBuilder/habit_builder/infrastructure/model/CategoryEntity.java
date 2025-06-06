package com.habitBuilder.habit_builder.infrastructure.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;

    //     this won't show up in the database don't worry it's just a foreign key :)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<HabitEntity> habits;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<GoalEntity> goals;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<TemplateEntity> templateEntities;
}
