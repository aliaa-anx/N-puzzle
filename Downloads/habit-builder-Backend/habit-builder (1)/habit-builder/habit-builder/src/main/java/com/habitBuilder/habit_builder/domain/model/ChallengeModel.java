package com.habitBuilder.habit_builder.domain.model;

public class ChallengeModel {

    private Integer id;
    private String title;
    private String description;
    private Integer durationDays;

    public ChallengeModel(Integer id,String title,String description,Integer durationDays){
        this.id=id;
        this.title=title;
        this.description=description;
        this.durationDays=durationDays;
    }
    public ChallengeModel(){}

    public void setId(Integer id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationDays() {
        return durationDays;
    }
}
