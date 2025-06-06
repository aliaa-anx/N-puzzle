package com.habitBuilder.habit_builder.infrastructure.repository;

import com.habitBuilder.habit_builder.infrastructure.model.HabitEntity;
import com.habitBuilder.habit_builder.infrastructure.model.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringTemplateRepository extends JpaRepository<TemplateEntity, Integer> {
    @Query(value = "SELECT * FROM templates WHERE category_id = ?1 ",nativeQuery = true)
    List<TemplateEntity> getTemplatesByCategory(Integer categoryId);
}
