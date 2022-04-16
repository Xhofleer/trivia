package com.adaptionsoft.games.uglytrivia;

import java.util.*;

class Questions {

//    Map<QuestionCategory, String> questionMap = Map.of(
//
//    );
    List<String> popQuestions = new ArrayList<>();
    List<String> scienceQuestions = new ArrayList<>();
    List<String> sportsQuestions = new ArrayList<>();
    List<String> rockQuestions = new ArrayList<>();

    public Questions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add("Rock Question " + i);
        }
    }
}

public class Game implements IGame {
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    Questions questions = new Questions();
    List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        players.add(playerName);
        places[players.size()] = 0;
        purses[players.size()] = 0;
        inPenaltyBox[players.size()] = false;
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop")) System.out.println(popQuestions.remove(0));
        if (currentCategory().equals("Science")) System.out.println(scienceQuestions.remove(0));
        if (currentCategory().equals("Sports")) System.out.println(sportsQuestions.remove(0));
        if (currentCategory().equals("Rock")) System.out.println(rockQuestions.remove(0));
    }

    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        else if (places[currentPlayer] == 4) return "Pop";
        else if (places[currentPlayer] == 8) return "Pop";
        else if (places[currentPlayer] == 1) return "Science";
        else if (places[currentPlayer] == 5) return "Science";
        else if (places[currentPlayer] == 9) return "Science";
        else if (places[currentPlayer] == 2) return "Sports";
        else if (places[currentPlayer] == 6) return "Sports";
        else if (places[currentPlayer] == 10) return "Sports";
        else return "Rock";
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
