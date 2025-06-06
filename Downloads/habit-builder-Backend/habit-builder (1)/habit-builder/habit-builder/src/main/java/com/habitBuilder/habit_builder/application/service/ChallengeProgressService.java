package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;
import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;
import com.habitBuilder.habit_builder.domain.model.UserModel;
import com.habitBuilder.habit_builder.domain.repository.ChallengeParticipantsRepository;
import com.habitBuilder.habit_builder.domain.repository.ChallengeProgressRepository;
import com.habitBuilder.habit_builder.domain.repository.ChallengeRepository;
import com.habitBuilder.habit_builder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChallengeProgressService {
    private final ChallengeProgressRepository progressRepo;
    private final ChallengeParticipantsRepository repository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public ChallengeProgressService(ChallengeProgressRepository progressRepo, ChallengeParticipantsRepository repository,
                                    ChallengeRepository challengeRepository, UserRepository userRepository) {
        this.progressRepo = progressRepo;
        this.repository = repository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;

    }

    public ChallengeProgress shareProgress(Integer userId, Integer challengeId) {
        ChallengeParticipants participant = repository.findByUserIdAndChallengeId(userId, challengeId);
        ChallengeModel challenge = challengeRepository.findById(challengeId);
        UserModel user = userRepository.findById(userId);

        if (participant == null || challenge == null || user == null) {
            throw new RuntimeException("User, Challenge, or Participation not found");
        }

        ChallengeProgress progress = new ChallengeProgress(
                challenge.getTitle(),
                user.getName(),
                challenge.getDescription(),
                challenge.getDurationDays(),
                participant.getProgress(),
                participant.isCompleted()
        );

        progressRepo.save(progress); // Persist to database

        return progress;
    }


}