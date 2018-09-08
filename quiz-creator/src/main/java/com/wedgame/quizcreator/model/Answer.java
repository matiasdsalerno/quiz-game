package com.wedgame.quizcreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private Long id;
    private Long questionId;
    private String answer;
    private Boolean correct;

}
