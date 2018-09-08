package com.wedgame.quizcreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {

    private Long playerId;
    private String name;
}
