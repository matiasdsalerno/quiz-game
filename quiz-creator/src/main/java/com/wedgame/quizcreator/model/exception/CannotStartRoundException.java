package com.wedgame.quizcreator.model.exception;

public class CannotStartRoundException extends RuntimeException {

    public CannotStartRoundException(boolean currentRoundIsCompleted, boolean areThereQuestionsLeft) {
        super("Cannot start new Round [currentRoundIsCompleted: " + currentRoundIsCompleted +
                " areThereQuestionsLeft: " + areThereQuestionsLeft + "]");

    }

    public CannotStartRoundException(String message) {
        super(message);
    }
}
