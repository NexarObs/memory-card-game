package com.jee.memorycardgame.service;

import org.springframework.stereotype.Service;

@Service
public class GameDifficultyService {

    public int getGridSize(String difficulty) {
        if (difficulty == null) return 2;
        switch (difficulty.toLowerCase()) {
            case "easy":   return 2;
            case "medium": return 4;
            case "hard":   return 4;
            default:       return 2;
        }
    }

    public int getTotalTries(String difficulty) {
        if (difficulty == null) return 3;
        switch (difficulty.toLowerCase()) {
            case "easy":   return 3;
            case "medium": return 8;
            case "hard":   return 10;
            default:       return 3;
        }
    }

    public int getPairCount(String difficulty) {
        if (difficulty == null) return 2;
        switch (difficulty.toLowerCase()) {
            case "easy":   return 2;  // 2x2 = 4 cards, 2 pairs
            case "medium": return 6;  // 3x4 = 12 cards, 6 pairs
            case "hard":   return 8;  // 4x4 = 16 cards, 8 pairs
            default:       return 2;
        }
    }
}