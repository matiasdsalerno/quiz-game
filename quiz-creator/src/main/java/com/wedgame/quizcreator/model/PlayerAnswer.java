package com.wedgame.quizcreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerAnswer {

    private Long questionId;
    private Long answerId;
    private Long playerId;

}
