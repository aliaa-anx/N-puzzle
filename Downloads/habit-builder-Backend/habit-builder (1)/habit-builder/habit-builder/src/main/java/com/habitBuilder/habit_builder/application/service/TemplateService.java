package com.habitBuilder.habit_builder.application.service;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.model.TemplateModel;
import com.habitBuilder.habit_builder.domain.repository.TemplateRepository;

import java.util.List;

public class TemplateService {

    private final TemplateRepository templateRepository;
    public TemplateService(TemplateRepository templateRepository){
        this.templateRepository= templateRepository;
    }
    public List<TemplateModel> findAll(){
        return templateRepository.findAll();
    }
    public List<TemplateModel> viewRecommendations(Integer userId){
        return templateRepository.viewRecommendations(userId);
    }
}
