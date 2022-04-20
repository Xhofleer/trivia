package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Questions {
    Map<QuestionCategory, List<String>> questionMap = Map.of(
            QuestionCategory.POP, new ArrayList<>(),
            QuestionCategory.SCIENCE, new ArrayList<>(),
            QuestionCategory.SPORT, new ArrayList<>(),
            QuestionCategory.ROCK, new ArrayList<>()
    );

    public Questions() {
        for (int i = 0; i < 50; i++) {
            questionMap.get(QuestionCategory.POP).add("Pop Question " + i);
            questionMap.get(QuestionCategory.SCIENCE).add(("Science Question " + i));
            questionMap.get(QuestionCategory.SPORT).add(("Sports Question " + i));
            questionMap.get(QuestionCategory.ROCK).add("Rock Question " + i);
        }
    }

    public String askQuestion(QuestionCategory questionCategory) {
        return questionMap.get(questionCategory).remove(0);
    }
}
