package com.wedgame.quizcreator.model.exception;

import com.wedgame.quizcreator.model.PlayerAnswer;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(Long questionId, Long answerId) {
        super(String.format("The answer %d was not found in question %d", answerId, questionId));
    }
}
