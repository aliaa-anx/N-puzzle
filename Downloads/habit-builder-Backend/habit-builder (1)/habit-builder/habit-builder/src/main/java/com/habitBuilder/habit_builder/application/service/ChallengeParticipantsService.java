package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.ChallengeModel;
import com.habitBuilder.habit_builder.domain.model.ChallengeParticipants;
import com.habitBuilder.habit_builder.domain.model.ChallengeProgress;
import com.habitBuilder.habit_builder.domain.model.UserModel;
import com.habitBuilder.habit_builder.domain.repository.*;
import com.habitBuilder.habit_builder.domain.repository.ChallengeRepository;
import com.habitBuilder.habit_builder.infrastructure.model.ChallengeParticipantsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChallengeParticipantsService {

    private final ChallengeParticipantsRepository repository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final ChallengeProgressRepository progressRepo;

    public ChallengeParticipantsService(ChallengeProgressRepository progressRepo,ChallengeParticipantsRepository repository,
                                        ChallengeRepository challengeRepository,UserRepository userRepository) {
        this.progressRepo = progressRepo;
        this.repository = repository;
        this.challengeRepository = challengeRepository;
        this.userRepository=userRepository;
    }

    /**  public ChallengeParticipantsService(ChallengeParticipantsRepository repository) {
     this.repository = repository;
     }**/
    public ChallengeParticipants joinChallenge(ChallengeParticipants Participant) {
        //ChallengeParticipants participant = new ChallengeParticipants(challengeId, userId);
        return repository.save(Participant);
    }

    public void leaveChallenge(Integer userId, Integer challengeId) {
        // Find the participant by userId and challengeId
        ChallengeParticipants participant = repository.findByUserIdAndChallengeId(userId, challengeId);

        // Check if the participant exists
        if (participant == null) {
            throw new IllegalStateException("User is not part of this challenge.");
        }

        // Delete the participant from the challenge
        repository.deleteByUserIdAndChallengeId(userId, challengeId);
    }
    public ChallengeParticipants incrementProgress(Integer userId, Integer ChallengeId){
        ChallengeParticipants participant= repository.findByUserIdAndChallengeId(userId, ChallengeId);
        ChallengeModel challenge=challengeRepository.findById(ChallengeId);
        Integer ChallangeDuration=challenge.getDurationDays();

        if (participant == null) {
            throw new RuntimeException("User not joined to challenge.");
        }else{
            System.out.println("Found participant ID: " + participant.getId());
        }


        if(participant.isCompleted()){
            return participant;
        }

        Integer newProgress= participant.getProgress()+1;
        participant.setProgress(newProgress);

        if(newProgress>=ChallangeDuration){
            participant.setCompleted(true);
        }
        return repository.save(participant);
    }


    /**public ChallengeProgress shareProgress(Integer userId, Integer challengeId) {
     ChallengeParticipants participant = repository.findByUserIdAndChallengeId(userId, challengeId);
     ChallengeModel challenge = challengeRepository.findById(challengeId);
     UserModel user=userRepository.findById(userId);

     if (participant == null || challenge == null) {
     throw new RuntimeException("Challenge or participation not found");
     }

     return new ChallengeProgress(
     challenge.getTitle(),
     user.getName(),
     challenge.getDescription(),
     challenge.getDurationDays(),
     participant.getProgress(),
     participant.isCompleted()
     );
     }**/


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
