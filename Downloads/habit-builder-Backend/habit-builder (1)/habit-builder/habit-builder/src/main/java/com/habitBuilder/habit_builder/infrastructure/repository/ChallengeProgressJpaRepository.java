package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;
import com.habitBuilder.habit_builder.domain.repository.ChallengeProgressRepository;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeParticipantsEntity;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeProgressEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ChallengeProgressJpaRepository implements ChallengeProgressRepository {
    private final SpringChallengeProgressRepository springRepo;
    public ChallengeProgressJpaRepository(SpringChallengeProgressRepository springRepo) {
        this.springRepo = springRepo;
    }
    /** @Override
    public ChallengeProgress findByUserIdAndChallengeId(Integer UserId, Integer ChallengeId){
    ChallengeProgressEntity entity = springRepo.findByUserIdAndChallengeId(UserId, ChallengeId);

    if (entity == null) {
    return null; // or throw an exception if necessary
    }

    return entity.toDomain();

    }**/

    @Override
    public ChallengeProgress save(ChallengeProgress progress) {
        // Convert domain model to entity
        ChallengeProgressEntity entity = ChallengeProgressEntity.fromDomain(progress);

        // Save entity to the database
        ChallengeProgressEntity savedEntity = springRepo.save(entity);

        // Convert saved entity back to domain model and return it
        return savedEntity.toDomain();
    }

}
