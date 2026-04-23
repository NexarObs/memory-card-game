package com.jee.memorycardgame.service;

import org.springframework.stereotype.Service;

@Service
public class GameDifficultyService {

    public int getGridSize(String difficulty) {

        if (difficulty == null) return 2;

        switch (difficulty.toLowerCase()) {
            case "easy":
                return 2;

            case "medium":
                return 3;

            case "hard":
                return 4;

            default:
                return 2;
        }
    }
}