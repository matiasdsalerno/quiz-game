package com.wedgame.quizcreator.model;

import com.wedgame.quizcreator.utils.QuestionGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundTest {

    @Test
    public void testAnswer() {
        Round round = new Round(QuestionGenerator.generateQuestionAnswer1FalseAnswer2True(1L, "a?", "b", "a"));
        round.start();
        round.answer(new PlayerAnswer(1L, 2L, 1L));

        assertNotNull(round.getPlayerAnswerMap().get(1L));
    }

    @Test(expected = WrongQuestionException.class)
    public void testAnswerWhenWrongQuestion() {
        Round round = new Round(QuestionGenerator.generateQuestionAnswer1FalseAnswer2True(1L, "a?", "b", "a"));
        round.start();
        round.answer(new PlayerAnswer(2L, 2L, 1L));

    }

    @Test
    public void testIsAnswerCorrect() {
        Round round = new Round(QuestionGenerator.generateQuestionAnswer1FalseAnswer2True(1L, "a?", "b", "a"));
        round.start();
        round.answer(new PlayerAnswer(1L, 2L, 1L));

        assertTrue(round.isAnswerCorrect(1L));
    }


    @Test
    public void testIsAnswerCorrectWhenNoAnswer() {
        Round round = new Round(QuestionGenerator.generateQuestionAnswer1FalseAnswer2True(1L, "a?", "b", "a"));
        round.start();
        assertFalse(round.isAnswerCorrect(1L));
    }

    @Test
    public void testIsAnswerIncorrect() {
        Round round = new Round(QuestionGenerator.generateQuestionAnswer1FalseAnswer2True(1L, "a?", "b", "a"));
        round.start();
        round.answer(new PlayerAnswer(1L, 1L, 1L));

        assertFalse(round.isAnswerCorrect(1L));
    }

}