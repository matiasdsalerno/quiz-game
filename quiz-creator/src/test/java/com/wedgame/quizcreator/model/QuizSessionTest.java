package com.wedgame.quizcreator.model;

import com.wedgame.quizcreator.model.exception.AnswerNotFoundException;
import com.wedgame.quizcreator.model.exception.CannotStartRoundException;
import com.wedgame.quizcreator.model.exception.QuestionNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.wedgame.quizcreator.utils.QuestionGenerator.generateQuestionAnswer1FalseAnswer2True;
import static org.junit.Assert.*;

public class QuizSessionTest {

    private QuizSession quizSession;

    @Before
    public void setUp() {

        List<Question> questions = Arrays.asList(
                generateQuestionAnswer1FalseAnswer2True(1L, "Donde se conocieron", "En un bar", "En la iglesia"),
                generateQuestionAnswer1FalseAnswer2True(2L, "En que mes se pusieron de novios", "En Octubre", "En Noviembre")
                );

        Quiz quiz = new Quiz(1L, "Casamiento", questions);

        quizSession = new QuizSession(quiz);
    }


    @Test
    public void registerPlayer() {
        quizSession.registerPlayer("mesa 1");
        quizSession.registerPlayer("mesa 2");

        assertEquals(2, quizSession.getPlayers().size());
    }

    @Test(expected = WrongQuestionException.class)
    public void answerWhenQuestionNotPresent() {
        quizSession.startRound();
        quizSession.answer(new PlayerAnswer(3L, 1L, 1L));
    }

    @Test(expected = AnswerNotFoundException.class)
    public void answerWhenAnswerNotFound() {
        quizSession.startRound();
        quizSession.answer(new PlayerAnswer(1L, 5L, 1L));
    }

    @Test
    public void answerWhenAnswerIsCorrect() {
        quizSession.registerPlayer("mesa 1");
        quizSession.registerPlayer("mesa 2");
        quizSession.startRound();
        quizSession.answer(new PlayerAnswer(1L, 2L, 1L));

        assertEquals(1, quizSession.getScore(1L));
    }

    @Test
    public void answerWhenAnswerIsIncorrect() {
        quizSession.registerPlayer("mesa 1");
        quizSession.registerPlayer("mesa 2");
        quizSession.startRound();
        quizSession.answer(new PlayerAnswer(1L, 1L, 1L));

        assertEquals(0, quizSession.getScore(1L));
    }

    @Test
    public void startRound() {
        quizSession.startRound();
        assertEquals(1, quizSession.getRounds().stream().filter(round -> round.getState() == RoundState.STARTED).count());
    }


    @Test(expected = CannotStartRoundException.class)
    public void startRoundWhenAlreadyStarted() {
        quizSession.startRound();
        quizSession.startRound();
    }

    @Test
    public void testEndRound() {
        quizSession.startRound();
        Round currentRound1 = quizSession.getCurrentRound();
        quizSession.finishRound();
        Round currentRound2 = quizSession.getCurrentRound();
        assertNotSame(currentRound1, currentRound2);
    }
}