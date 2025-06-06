package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringChallengeParticipantsRepository extends JpaRepository<ChallengeParticipantsEntity, Integer> {

    ChallengeParticipantsEntity findByUserIdAndChallengeId(Integer userId, Integer challengeId);
    void deleteByUserIdAndChallengeId(Integer userId, Integer challengeId);


}
