package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
}
