package com.habitBuilder.habit_builder.infrastructure.model;

import com.habitBuilder.habit_builder.domain.model.GoalModel;
import com.habitBuilder.habit_builder.domain.model.TemplateModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="templates")
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    public TemplateEntity(TemplateModel templateModel) {
        this.id = templateModel.getId();
        this.name = templateModel.getName();
        this.categoryId = templateModel.getCategoryId();
        this.description = templateModel.getDescription();
    }

    public TemplateEntity() {
    }

    public TemplateModel toDomain() {
        return new TemplateModel(id, name, categoryId, description);
    }

    public static TemplateEntity fromDomain(TemplateModel templateModel) {
        return new TemplateEntity(templateModel);
    }

    public static List<TemplateModel> toDomain(List<TemplateEntity> entityList) {
        List<TemplateModel> domainList = new ArrayList<>();
        for (TemplateEntity entity : entityList) {
            domainList.add(entity.toDomain()); // use the single converter
        }
        return domainList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
