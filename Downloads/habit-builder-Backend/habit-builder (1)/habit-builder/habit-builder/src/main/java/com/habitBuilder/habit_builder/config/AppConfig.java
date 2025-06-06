package com.habitBuilder.habit_builder.config;

import com.habitBuilder.habit_builder.application.service.*;
import com.habitBuilder.habit_builder.domain.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    for dependency injection to pass the implementation of the interface
    @Bean
    public HabitService habitService(HabitRepository habitRepository) {
        return new HabitService(habitRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);

    }

    @Bean
    public GoalService goalService(GoalRepository goalRepository) {
        return new GoalService(goalRepository);
    }

    @Bean
    public ChallengeService challengeService(ChallengeRepository challengeRepository){
        return new ChallengeService(challengeRepository);
    }

    @Bean
    public ReminderService reminderService(ReminderRepository reminderRepository){
        return new ReminderService(reminderRepository);
    }

    @Bean
    public TemplateService templateService(TemplateRepository templateRepository) {
        return new TemplateService(templateRepository);
    }

  /**  @Bean
    public ChallengeParticipantsService challengeParticipantsService(ChallengeParticipantsRepository challengeParticipantsRepository){
        return new ChallengeParticipantsService(challengeParticipantsRepository);
    }**/
  @Bean
  public ChallengeParticipantsService challengeParticipantsService(
          ChallengeProgressRepository challengeProgressRepository,
          ChallengeParticipantsRepository challengeParticipantsRepository,
          ChallengeRepository challengeRepository,UserRepository userRepository) {
      return new ChallengeParticipantsService(
              challengeProgressRepository,
              challengeParticipantsRepository,
              challengeRepository,
              userRepository
      );
  }


}