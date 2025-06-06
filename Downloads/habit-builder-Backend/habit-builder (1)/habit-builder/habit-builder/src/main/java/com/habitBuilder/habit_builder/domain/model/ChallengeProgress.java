package com.habitBuilder.habit_builder.domain.model;

public class ChallengeProgress {


    private String title;
    private String userName;
    private String description;
    private int durationDays;
    private int progress;
    private boolean isCompleted;

    public ChallengeProgress(String title,String userName, String description, int durationDays, int progress, boolean isCompleted) {
        this.title = title;
        this.userName=userName;

        this.description = description;
        this.durationDays = durationDays;
        this.progress = progress;
        this.isCompleted = isCompleted;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters (optional, if you want to make it mutable)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

