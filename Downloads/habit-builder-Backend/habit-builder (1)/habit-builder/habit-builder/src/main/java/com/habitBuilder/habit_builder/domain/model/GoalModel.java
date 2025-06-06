package com.habitBuilder.habit_builder.domain.model;

public class GoalModel {
    private Integer id;
    private Integer userId;
    private Integer habitCategoryId;
    private String title;

    public GoalModel(Integer id, Integer userId, Integer habitCategoryId, String title) {
        this.id = id;
        this.userId = userId;
        this.habitCategoryId = habitCategoryId;
        this.title = title;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setHabitCategoty(Integer habitCategory) {
        this.habitCategoryId = habitCategory;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public Integer getHabitCategory() {
        return habitCategoryId;
    }
    public String getTitle() {
        return title;
    }
}
