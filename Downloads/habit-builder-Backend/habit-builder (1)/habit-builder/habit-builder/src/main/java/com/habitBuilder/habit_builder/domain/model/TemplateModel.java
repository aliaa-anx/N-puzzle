package com.habitBuilder.habit_builder.domain.model;

public class TemplateModel {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;

    public TemplateModel(Integer id, String name, Integer categoryId, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
