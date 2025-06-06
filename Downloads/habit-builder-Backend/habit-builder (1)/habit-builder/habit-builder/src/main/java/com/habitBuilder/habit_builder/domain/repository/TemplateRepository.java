package com.habitBuilder.habit_builder.domain.repository;

import com.habitBuilder.habit_builder.domain.model.HabitModel;
import com.habitBuilder.habit_builder.domain.model.TemplateModel;

import java.util.List;

public interface TemplateRepository {

    List<TemplateModel> findAll();
    List<TemplateModel> viewRecommendations(Integer userId);
}
