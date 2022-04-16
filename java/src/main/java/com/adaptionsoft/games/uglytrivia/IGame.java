package com.adaptionsoft.games.uglytrivia;

public interface IGame {

    void add(String playerName);

    void roll(int roll);

    boolean wasCorrectlyAnswered();

    boolean wrongAnswer();

}