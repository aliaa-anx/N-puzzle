package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name="challengeParticipants",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "challengeId"}))
public class ChallengeParticipantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer challengeId;

     @Column(nullable=false)
    private Integer progress;
     @Column(nullable = false)
    private boolean isCompleted;






   /** public ChallengeParticipants toDomain() {
        return new ChallengeParticipants(userId, challengeId); // Assuming ChallengeParticipants constructor takes these parameters
    }**/
   public ChallengeParticipants toDomain() {
       return new ChallengeParticipants(
               this.id,
               this.challengeId,
               this.userId,
               this.progress,
               this.isCompleted
       );
   }

    // Convert list of entities to list of domain models
   /** public static List<ChallengeParticipants> toDomain(List<ChallengeParticipantsEntity> entities) {
        List<ChallengeParticipants> list = new ArrayList<>();
        for (ChallengeParticipantsEntity entity : entities) {
            list.add(entity.toDomain());
        }
        return list;
    }**/

    public static ChallengeParticipantsEntity fromDomain(ChallengeParticipants domain) {
        ChallengeParticipantsEntity entity = new ChallengeParticipantsEntity();
        entity.setId(domain.getId());
        entity.setChallengeId(domain.getChallengeId());
        entity.setUserId(domain.getUserId());
        entity.setProgress(domain.getProgress() != null ? domain.getProgress() : 0);
        entity.setCompleted(domain.isCompleted());
        return entity;
    }
}
