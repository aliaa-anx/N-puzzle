package com.habitBuilder.habit_builder.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderModel {


    private Integer id;
    private String duration;
    private LocalDateTime lastReminder;
    private Integer habitId;
    private Integer userId;

    public ReminderModel( Integer id, String duration, LocalDateTime lastReminder, Integer habitId, Integer userId) {
        this.id = id;
        this.duration = duration;
        this.lastReminder = lastReminder;
        this.habitId = habitId;
        this.userId = userId;
    }

    public ReminderModel() {
    }

    public Integer getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public LocalDateTime getLastReminder() {
        return lastReminder;
    }

    public int getHabitId() {
        return habitId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLastReminder(LocalDateTime lastReminder) {
        this.lastReminder = lastReminder;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
