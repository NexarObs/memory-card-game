package com.jee.memorycardgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jee.memorycardgame.model.CardModel;
import com.jee.memorycardgame.model.GameModel;
import com.jee.memorycardgame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameDifficultyService gameDifficultyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // CREATE GAME
    public int createGame(int playerId, String difficulty) {
        List<CardModel> cards = generateBoard(difficulty);
        int totalTries = gameDifficultyService.getTotalTries(difficulty);
        String boardState = serializeBoard(cards, totalTries, difficulty);

        GameModel game = new GameModel();
        game.setPlayerId(playerId);
        game.setDifficulty(difficulty);
        game.setTriesLeft(totalTries);
        game.setBoardState(boardState);

        return gameRepository.createGame(game);
    }

    // LOAD GAME
    public GameModel loadGame(int id) {
        return gameRepository.findById(id);
    }

    // SAVE GAME
    public void saveGame(int gameId, String boardState, int triesLeft) {
        GameModel game = new GameModel();
        game.setId(gameId);
        game.setBoardState(boardState);
        game.setTriesLeft(triesLeft);
        gameRepository.saveGame(game);
    }

    // DELETE GAME (quit or end)
    public void deleteGame(int id) {
        gameRepository.deleteGame(id);
    }

    // GET PLAYER GAMES
    public List<GameModel> getPlayerGames(int playerId) {
        return gameRepository.findByPlayerId(playerId);
    }

    // BOARD GENERATION
    private List<CardModel> generateBoard(String difficulty) {
        int pairCount = gameDifficultyService.getPairCount(difficulty);
        List<String> values = new ArrayList<>();

        for (int i = 0; i < pairCount; i++) {
            String val = String.valueOf((char) ('A' + i));
            values.add(val);
            values.add(val);
        }

        Collections.shuffle(values);

        List<CardModel> cards = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            cards.add(new CardModel(i, values.get(i)));
        }

        return cards;
    }

    // SERIALIZE
    public String serializeBoard(List<CardModel> cards, int triesLeft, String difficulty) {
        try {
            Map<String, Object> state = new LinkedHashMap<>();
            state.put("cards", cards);
            state.put("triesLeft", triesLeft);
            state.put("totalTries", gameDifficultyService.getTotalTries(difficulty));
            state.put("difficulty", difficulty);
            state.put("gridSize", gameDifficultyService.getGridSize(difficulty));
            return objectMapper.writeValueAsString(state);
        } catch (Exception e) {
            throw new RuntimeException("Board serialization failed", e);
        }
    }

    // DESERIALIZE
    public Map<String, Object> deserializeBoard(String boardState) {
        try {
            return objectMapper.readValue(boardState,
                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException("Board deserialization failed", e);
        }
    }
}