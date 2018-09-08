package com.wedgame.quizcreator.model;

import com.wedgame.quizcreator.model.exception.AnswerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Question {

    private Long id;
    private Long quizId;
    private String question;
    private List<Answer> answers;

    public Boolean isCorrect(Long answerId) {
        return answers.stream()
                .filter(answer -> answer.getId().equals(answerId))
                .findFirst()
                .map(Answer::getCorrect)
                .orElseThrow(() -> new AnswerNotFoundException(this.id, answerId));
    }

    public static class QuestionBuilder {

        public QuestionBuilder addAnswer(String answer, boolean isCorrect) {
            if(Objects.isNull(this.answers)) {
                this.answers = new ArrayList<>();
            }
            this.answers.add(new Answer(Integer.valueOf(this.answers.size() + 1).longValue(), this.id, answer, isCorrect));
            return this;
        }
    }

}
