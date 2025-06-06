package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;
import com.habitBuilder.habit_builder.domain.repository.ChallengeRepository;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChallengeJpaRepository implements ChallengeRepository {

    private final SpringChallengeRepository springRepo;

    public ChallengeJpaRepository(SpringChallengeRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public ChallengeModel findById(Integer id) {
        return springRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"))
                .toDomain();
    }

    @Override
    public ChallengeModel save(ChallengeModel model) {
        ChallengeEntity entity = ChallengeEntity.fromDomain(model);
        return springRepo.save(entity).toDomain();
    }

    @Override
    public List<ChallengeModel> findAll() {
        return springRepo.findAll()
                .stream()
                .map(ChallengeEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        springRepo.deleteById(id);
    }
}
