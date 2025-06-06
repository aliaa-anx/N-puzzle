package com.habitBuilder.habit_builder.domain.model;

public class ResponseModel {
    private boolean success;  // Corrected the typo here
    private String message;

    public ResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter methods for JSON serialization
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
