package com.wedgame.quizcreator.model;

import com.wedgame.quizcreator.model.exception.CannotAnswerException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class Round {

    private Question question;
    private RoundState state;
    private Map<Long, Boolean> playerAnswerMap;

    public Round(Question question) {
        this.question = question;
        this.state = RoundState.NOT_STARTED;
        this.playerAnswerMap = new HashMap<>();
    }

    public void answer(PlayerAnswer playerAnswer) {
        runValidations(playerAnswer);
        playerAnswerMap.put(playerAnswer.getPlayerId(), question.isCorrect(playerAnswer.getAnswerId()));
    }

    private void runValidations(PlayerAnswer playerAnswer) {
        if(!Objects.equals(question.getId(), playerAnswer.getQuestionId())) {
            throw new WrongQuestionException(this, playerAnswer.getQuestionId());
        }
        if(playerAnswerMap.containsKey(playerAnswer.getPlayerId())) {
            throw new CannotAnswerException(playerAnswer.getPlayerId(), playerAnswer.getQuestionId());
        }
        if(!state.canAnswer()) {
            throw new CannotAnswerException(state);
        }
    }

    public boolean isAnswerCorrect(Long playerId) {
        return playerAnswerMap.getOrDefault(playerId, false);
    }

    public void start() {
        state = state.start();
    }

    public void finish() {
        state = state.finish();
    }
}
