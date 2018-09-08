package com.wedgame.quizcreator.model.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long questionId) {
        super(String.format("Question with Id: %d not found", questionId));
    }
}
