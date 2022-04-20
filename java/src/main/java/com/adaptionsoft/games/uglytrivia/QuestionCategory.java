package com.adaptionsoft.games.uglytrivia;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORT("Sports"),
    ROCK("Rock");

    private final String label;

    QuestionCategory(String envUrl) {
        this.label = envUrl;
    }

    public String getLabel() {
        return label;
    }
}
