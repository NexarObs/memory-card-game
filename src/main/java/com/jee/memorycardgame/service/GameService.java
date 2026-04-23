package com.jee.memorycardgame.service;

import com.jee.memorycardgame.repository.GameRepository;
import com.jee.memorycardgame.model.GameModel;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public int createGame(int playerId, String difficulty) {

        GameModel game = new GameModel();
        game.setPlayerId(playerId);
        game.setDifficulty(difficulty);
        game.setTriesLeft(10); // exemple
        game.setBoardState("NEW");

        return gameRepository.createGame(game);
    }

    public List<GameModel> getPlayerGames(int playerId) {
        return gameRepository.findByPlayerId(playerId);
    }

    public GameModel loadGame(int id) {
        return gameRepository.findById(id);
    }
}