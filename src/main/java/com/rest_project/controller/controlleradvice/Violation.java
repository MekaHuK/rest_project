package com.rest_project.controller.controlleradvice;

public class Violation {

    private final String message;

    public Violation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}