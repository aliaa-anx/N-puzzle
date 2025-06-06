package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeParticipantsEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringChallengeProgressRepository extends JpaRepository<ChallengeProgressEntity, Integer> {
    //ChallengeProgressEntity findByUserIdAndChallengeId(Integer userId, Integer challengeId);
    ChallengeProgress save(ChallengeProgress challengeProgress);
}
