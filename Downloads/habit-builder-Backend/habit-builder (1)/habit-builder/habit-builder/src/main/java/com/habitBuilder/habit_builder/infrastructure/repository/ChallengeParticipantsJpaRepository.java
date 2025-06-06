package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import com.habitBuilder.habit_builder.domain.repository.ChallengeParticipantsRepository;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeParticipantsEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ChallengeParticipantsJpaRepository implements ChallengeParticipantsRepository {
    private final SpringChallengeParticipantsRepository springRepo;

    public ChallengeParticipantsJpaRepository(SpringChallengeParticipantsRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
   /** public ChallengeParticipants save(ChallengeParticipants challengeParticipants) {
        ChallengeParticipantsEntity entity = new ChallengeParticipantsEntity();
        entity.setChallengeId(challengeParticipants.getChallengeId());
        entity.setUserId(challengeParticipants.getUserId());

        ChallengeParticipantsEntity savedEntity = springRepo.save(entity);
        return savedEntity.toDomain();
    }**/
    public ChallengeParticipants save(ChallengeParticipants challengeParticipants) {
       /** ChallengeParticipantsEntity entity = new ChallengeParticipantsEntity();
        entity.setChallengeId(challengeParticipants.getChallengeId());
        entity.setUserId(challengeParticipants.getUserId());

        // When joining, progress should always start at 0
        entity.setProgress(0);
        entity.setCompleted(false);  // Not completed at join

        ChallengeParticipantsEntity savedEntity = springRepo.save(entity);
        return savedEntity.toDomain();**/
        ChallengeParticipantsEntity entity = ChallengeParticipantsEntity.fromDomain(challengeParticipants);

        // If the domain model has an ID, it means it's an update, not a new insert
        if (challengeParticipants.getId() != null) {
            entity.setId(challengeParticipants.getId());
        }

        ChallengeParticipantsEntity savedEntity = springRepo.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public ChallengeParticipants findByUserIdAndChallengeId(Integer userId, Integer challengeId) {
        // Query the Spring Data JPA repository for the entity
        ChallengeParticipantsEntity entity = springRepo.findByUserIdAndChallengeId(userId, challengeId);

        if (entity == null) {
            return null; // or throw an exception if necessary
        }

        return entity.toDomain(); // Convert the entity to the domain object
    }

    @Override
    public void deleteByUserIdAndChallengeId(Integer userId, Integer challengeId) {

        springRepo.deleteByUserIdAndChallengeId(userId, challengeId);
    }

}
