package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;

public interface ChallengeProgressRepository {
    /** ChallengeProgress findByUserIdAndChallengeId(Integer userId, Integer challengeId);**/
    ChallengeProgress save(ChallengeProgress progress);
}
