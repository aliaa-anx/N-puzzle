package com.habitBuilder.habit_builder.domain.model;

public class UserModel {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String number;
    public UserModel(Integer id, String name, String password, String email, String number){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }
    public UserModel(){}
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }
}
