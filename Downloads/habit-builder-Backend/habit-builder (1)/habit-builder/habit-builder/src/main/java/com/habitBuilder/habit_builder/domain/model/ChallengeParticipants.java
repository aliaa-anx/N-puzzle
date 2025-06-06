package com.habitBuilder.habit_builder.domain.model;

public class ChallengeParticipants {

    private Integer id;
    private Integer challengeId;
    private Integer userId;
    private Integer progress;  // New field to track progress
    private boolean isCompleted;
    public ChallengeParticipants(Integer challengeId,Integer UserId){

        this.userId=UserId;
        this.challengeId=challengeId;

    }
    public ChallengeParticipants(Integer id,Integer challengeId,Integer UserId,Integer progress,boolean isCompleted){
        this.id=id;
        this.userId=UserId;
        this.challengeId=challengeId;
        this.progress=progress;
        this.isCompleted=isCompleted;

    }



    public ChallengeParticipants(){}

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }


    public Integer getChallengeId() {
        return challengeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
