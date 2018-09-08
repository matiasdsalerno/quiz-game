package com.wedgame.quizcreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Quiz {

    private Long id;
    private String title;
    private List<Question> questions;

}
