package com.wedgame.quizcreator.model.exception;

import com.wedgame.quizcreator.model.RoundState;

public class CannotAnswerException extends RuntimeException {


    public CannotAnswerException(Long playerId, Long questionId) {
        super("Player " + " has already answered question " + questionId);

    }

    public CannotAnswerException(RoundState state) {
        super("Cannot answer question. Round state: " + state.toString());
    }
}
