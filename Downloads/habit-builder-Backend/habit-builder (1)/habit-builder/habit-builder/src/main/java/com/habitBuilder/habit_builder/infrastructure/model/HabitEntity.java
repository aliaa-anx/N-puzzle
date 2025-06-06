package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.model.ReminderModel;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habits")
public class HabitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer userId;
    private Integer categoryId;
    private String title;
    private Integer streak;
    private Long avtiveDays;

    // this won't show up in the database don't worry it's just a foreign key :)
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToOne(mappedBy = "habitEntity", orphanRemoval = true)
    private ReminderEntity reminderEntity;

    public HabitEntity(HabitModel habit) {
        this.id = habit.getId();
        this.name =  habit.getName();
        this.userId = habit.getUserId();
        this.categoryId = habit.getCategoryId();
        this.title = habit.getTitle();
        this.streak = habit.getStreak();
        this.avtiveDays = habit.getActiveDays();
    }
    public HabitEntity(){}

    public HabitModel toDomain() {
        return new HabitModel(id, name, userId,categoryId, title, streak, avtiveDays);
    }

    public static HabitEntity fromDomain(HabitModel habit) {
        return new HabitEntity(habit);
    }


    public void setReminderEntity(ReminderEntity reminderEntity) {
        this.reminderEntity = reminderEntity;
    }

    public static List<HabitModel> toDomain(List<HabitEntity> entityList) {
        List<HabitModel> domainList = new ArrayList<>();
        for (HabitEntity entity : entityList) {
            domainList.add(entity.toDomain()); // use the single converter
        }
        return domainList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStreak() {
        return streak;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ReminderEntity getReminderEntity() {
        return reminderEntity;
    }

    public Long getAvtiveDays() {
        return avtiveDays;
    }

    public void setAvtiveDays(Long avtiveDays) {
        this.avtiveDays = avtiveDays;
    }
}
