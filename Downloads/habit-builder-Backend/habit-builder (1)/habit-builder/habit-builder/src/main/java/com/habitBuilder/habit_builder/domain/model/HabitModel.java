package com.habitBuilder.habit_builder.domain.model;

public class HabitModel {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer categoryId;
    private String title;
    private Integer streak;
    private Long activeDays;

    public HabitModel(Integer id,String name, Integer userId, Integer categoryId, String title, Integer streak, Long activeDays) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.streak = (streak != null) ? streak : 0;
        this.activeDays = activeDays;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setStreak(Integer streak) {
        this.streak = streak;
    }
    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public String getTitle() {
        return title;
    }
    public Integer getStreak() {
        return streak;
    }
    public void markAsCompleted(){
        this.streak++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Long activeDays) {
        this.activeDays = activeDays;
    }
}
