package com.wedgame.quizcreator.model;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Data
public class QuizSession {

    private final Iterator<Round> roundIterator;
    private Round currentRound;
    private List<Player> players;
    private Quiz quiz;
    private List<Round> rounds;

    public QuizSession(Quiz quiz) {
        this.players = new CopyOnWriteArrayList<>();
        this.rounds = quiz.getQuestions().stream().map(Round::new).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        this.roundIterator = rounds.iterator();
        this.currentRound = roundIterator.next();
        this.quiz = quiz;
    }

    public void startRound() {
        currentRound.start();
    }

    public void finishRound() {
        currentRound.finish();
        currentRound = roundIterator.hasNext() ? roundIterator.next() : null;
    }

    public void registerPlayer(String playerName) {
        Player player = new Player((long) players.size() + 1, playerName);
        players.add(player);
    }

    public void answer(PlayerAnswer playerAnswer) {
        currentRound.answer(playerAnswer);
    }

    public int getScore(long playerId) {
        return rounds.stream()
                .mapToInt(round -> round.isAnswerCorrect(playerId) ? 1 : 0)
                .sum();
    }
}
