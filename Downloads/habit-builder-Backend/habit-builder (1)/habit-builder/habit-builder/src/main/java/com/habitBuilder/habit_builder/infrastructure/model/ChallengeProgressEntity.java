package com.habitBuilder.habit_builder.infrastructure.model;
import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "challenge_progress")
public class ChallengeProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int durationDays;
    @Column(nullable = false)
    private int progress;
    @Column(nullable = false)
    private boolean isCompleted;



    public ChallengeProgress toDomain() {
        return new ChallengeProgress(
                this.title,
                this.userName,
                this.description,
                this.durationDays,
                this.progress,
                this.isCompleted
        );
    }
    public static ChallengeProgressEntity fromDomain(ChallengeProgress domain) {
        ChallengeProgressEntity entity = new ChallengeProgressEntity();
        entity.setTitle(domain.getTitle());
        entity.setUserName(domain.getUserName());
        entity.setDescription(domain.getDescription());
        entity.setDurationDays(domain.getDurationDays());
        entity.setProgress(domain.getProgress());
        entity.setCompleted(domain.isCompleted());
        return entity;
    }


}
