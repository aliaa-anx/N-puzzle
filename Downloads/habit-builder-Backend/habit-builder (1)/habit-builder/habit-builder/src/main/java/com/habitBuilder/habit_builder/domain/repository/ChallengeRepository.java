package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;

import java.util.List;

public interface ChallengeRepository {
    ChallengeModel save(ChallengeModel challenge);
    ChallengeModel findById(Integer id);
    List<ChallengeModel> findAll();
    void deleteById(Integer id);
}
