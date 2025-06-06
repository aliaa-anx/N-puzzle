package com.habitBuilder.habit_builder.presentation.controller;

import com.habitBuilder.habit_builder.application.service.*;
import com.habitBuilder.habit_builder.domain.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit_builder")
public class Controller {

    private final UserService userService;
    private final HabitService habitService;
    private final GoalService goalService;
    private final ChallengeService challengeService;
    private final ChallengeParticipantsService challengeParticipantsService;
    private final ReminderService reminderService;
    private final TemplateService templateService;
    // Injecting the services beans'
    public Controller(UserService userService, HabitService habitservice, GoalService goalService, ChallengeService challengeService,ChallengeParticipantsService challengeParticipantsService, ReminderService reminderService, TemplateService templateService) {
        this.userService = userService;
        this.habitService = habitservice;
        this.goalService = goalService;
        this.challengeService=challengeService;
        this.challengeParticipantsService=challengeParticipantsService;
        this.reminderService = reminderService;
        this.templateService = templateService;
    }

    @PostMapping("/save-habit")
    public void saveHabit(@RequestBody HabitModel habit) {
        habitService.saveHabit(habit);
    }

    @PostMapping("/update-habit")
    public void updateHabit(@RequestBody HabitModel habit) {
        habitService.saveHabit(habit);
    }

    @GetMapping("/get-user-habit")
    public List<HabitModel> getUserHabit(@RequestParam Integer userId){
        return habitService.getUserHabit(userId);
    }

    @DeleteMapping("/delete-habit/{id}")
    public void deleteHabit(@PathVariable Integer id) {
        habitService.deleteHabit(id);
        reminderService.deleteReminderChild(id);
        //return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserModel user) {
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseModel login(@RequestBody UserModel user) {
        return userService.login(user);
    }

    @PostMapping("/insert-goal")
    public void saveGoal(@RequestBody GoalModel goal) {
        goalService.saveGoal(goal);
    }

    @GetMapping("/get-goal")
    public GoalModel getGoal(@RequestParam Integer goalId){
        return goalService.getGoal(goalId);
    }

    @GetMapping("/get-user-goals")
    public List<GoalModel> getUserGoals(@RequestParam Integer userId){
        return goalService.getUserGoals(userId);
    }

    // when I want to increment the streak of this habit
    @PostMapping("/mark-as-completed")
    public void markAsCompleted(@RequestBody HabitModel habitModel){
        habitService.markAsCompleted(habitModel);
    }

    @GetMapping("/view-recommendations")
    public List<TemplateModel> viewRecommendations(Integer userId){
        return templateService.viewRecommendations(userId);
    }

    @DeleteMapping("/delete-goal/{goalId}")
    public void deleteGoal(@PathVariable Integer goalId){
        goalService.deleteGoal(goalId);
    }


    @GetMapping("/get-reminder")
    public ReminderModel getReminder(@RequestParam Integer id){
        return reminderService.getReminder(id);
    }

    @GetMapping("/get-habit-reminder")
    public ReminderModel getHabitReminder(@RequestParam Integer habitId){
        return reminderService.getHabitReminder(habitId);
    }

    @GetMapping("/get-user-reminder")
    public List<ReminderModel> getUserReminder(@RequestParam Integer userId){
        return reminderService.getUserReminder(userId);
    }

    @PostMapping("/save-reminder")
    public void saveReminder(@RequestBody ReminderModel reminderModel){
        reminderService.saveReminder(reminderModel);
    }

    @PostMapping("/update-reminder")
    public void updateReminder(@RequestBody ReminderModel reminderModel){
        reminderService.saveReminder(reminderModel);
    }

    @DeleteMapping("/delete-reminder/{id}")
    public void deleteReminder(@PathVariable Integer id) {
        reminderService.deleteReminder(id);
        //return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-templates")
    public List<TemplateModel> getTemplates(){
        return templateService.findAll();
    }

    @PostMapping("/add-days/{id}")
    public void addDays(@PathVariable Integer id){
        habitService.addDays(id);
    }

    @PostMapping("/delete-days/{id}")
    public void deleteDays(@PathVariable Integer id) {
        habitService.deleteDays(id);
    }

    // --- CHALLENGE ENDPOINTS ---

    @PostMapping("/create-challenge")
    public ChallengeModel createChallenge(@RequestBody ChallengeModel challenge) {
        return challengeService.createChallenge(challenge);
    }

    @GetMapping("/get-challenge")
    public ChallengeModel getChallenge(@RequestParam Integer id) {
        return challengeService.getChallenge(id);
    }

    @GetMapping("/get-all-challenges")
    public List<ChallengeModel> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @DeleteMapping("/delete-challenge")
    public void deleteChallenge(@RequestParam Integer id) {
        challengeService.deleteChallenge(id);
    }

    @PostMapping("/join-challenge")
    public ChallengeParticipants joinChallenge(@RequestBody ChallengeParticipants challengeParticipants) {
        return challengeParticipantsService.joinChallenge(challengeParticipants);
    }

    @DeleteMapping("/leave-challenge")
    public void leaveChallenge(@RequestBody ChallengeParticipants challengeParticipants) {
        // Call the service to leave the challenge
        challengeParticipantsService.leaveChallenge(challengeParticipants.getUserId(), challengeParticipants.getChallengeId());
    }

    @PutMapping("/increment-progress")

    public ChallengeParticipants incrementProgress(@RequestBody ChallengeParticipants request){
       return challengeParticipantsService.incrementProgress( request.getUserId(), request.getChallengeId());
    }

    @PostMapping("/share-progress")
    public ChallengeProgress shareProgress(@RequestBody ChallengeParticipants request) {
        return challengeParticipantsService.shareProgress(request.getUserId(), request.getChallengeId());

    }


}


