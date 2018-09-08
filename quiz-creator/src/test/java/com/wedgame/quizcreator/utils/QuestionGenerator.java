package com.wedgame.quizcreator.utils;

import com.wedgame.quizcreator.model.Question;

public class QuestionGenerator {


    public static Question generateQuestionAnswer1FalseAnswer2True(long id, String questionText, String answer1, String answer2) {
        return Question.builder()
                .id(id)
                .question(questionText)
                .addAnswer(answer1, false)
                .addAnswer(answer2, true)
                .build();
    }
}
