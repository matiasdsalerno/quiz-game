package com.wedgame.quizcreator.model;

public class WrongQuestionException extends RuntimeException {

    public WrongQuestionException(Round round, Long questionId) {
        super("Wrong question. " + round.toString() + "\nAttempted question was: " + questionId);
    }
}
