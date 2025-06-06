package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;
import com.habitBuilder.habit_builder.domain.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public ChallengeModel createChallenge(ChallengeModel model) {
        return challengeRepository.save(model);
    }

    public ChallengeModel getChallenge(Integer id) {
        return challengeRepository.findById(id);
    }

    public List<ChallengeModel> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public void deleteChallenge(Integer id) {
        challengeRepository.deleteById(id);
    }
}
