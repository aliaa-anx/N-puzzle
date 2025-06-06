package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;

public interface ChallengeParticipantsRepository {
    ChallengeParticipants save(ChallengeParticipants challengeParticipants);
    //find the participant by userId and challengeId
    ChallengeParticipants findByUserIdAndChallengeId(Integer userId, Integer challengeId);
    void deleteByUserIdAndChallengeId(Integer userId, Integer challengeId);
}
