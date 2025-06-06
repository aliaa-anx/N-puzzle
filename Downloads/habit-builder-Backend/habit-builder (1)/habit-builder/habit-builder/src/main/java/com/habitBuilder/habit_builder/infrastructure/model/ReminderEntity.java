package com.habitBuilder.habit_builder.infrastructure.model;
import com.habitBuilder.habit_builder.domain.model.ReminderModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "reminders", uniqueConstraints = { @UniqueConstraint(columnNames = {"habit_id"})})
public class ReminderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String duration;
    private LocalDateTime lastReminder;
    //@Column(nullable = false)
    private Integer habitId;
    private Integer userId;


    @OneToOne
    @JoinColumn(name = "habitId", referencedColumnName = "id", insertable = false, updatable = false)
    private HabitEntity habitEntity;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userEntity;

    public ReminderEntity(ReminderModel reminderModel) {
        this.id = reminderModel.getId();
        this.duration = reminderModel.getDuration();
        this.lastReminder = reminderModel.getLastReminder();
        this.habitId = reminderModel.getHabitId();
        this.userId = reminderModel.getUserId();
    }

    /*public ReminderEntity(ReminderModel reminderModel, Integer id) {
        this.id = id;
        this.duration = reminderModel.getDuration();
        this.lastReminder = reminderModel.getLastReminder();
        this.habitId = reminderModel.getHabitId();
    }*/

    public ReminderEntity() {
    }

    public ReminderModel toDomain() {
        return new ReminderModel(id, duration, lastReminder, habitId, userId);
    }
    public static List<ReminderModel> toDomain(List<ReminderEntity> entityList) {
        List<ReminderModel> domainList = new ArrayList<>();
        for (ReminderEntity entity : entityList) {
            domainList.add(entity.toDomain()); // use the single converter
        }
        return domainList;
    }

    public static ReminderEntity fromDomain(ReminderModel reminderModel) {
        return new ReminderEntity(reminderModel);
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

    public Integer getHabitId() {
        return habitId;
    }


    public HabitEntity getHabitEntity() {
        return habitEntity;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
