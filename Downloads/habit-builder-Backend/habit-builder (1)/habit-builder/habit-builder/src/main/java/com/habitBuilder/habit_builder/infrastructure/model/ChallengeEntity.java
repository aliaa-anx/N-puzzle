package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;
import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="challenges")
@Entity
public class ChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Integer durationDays;

    public ChallengeModel toDomain() {
        return new ChallengeModel(id, title, description, durationDays);
    }



    public static ChallengeEntity fromDomain(ChallengeModel model) {
        return ChallengeEntity.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .durationDays(model.getDurationDays())
                .build();
    }
}
