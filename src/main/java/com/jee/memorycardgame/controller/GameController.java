package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.model.GameModel;
import com.jee.memorycardgame.service.GameDifficultyService;
import com.jee.memorycardgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameDifficultyService gameDifficultyService;

    // LOAD GAME
    @GetMapping("/game/{id}")
    public String showGame(
            @PathVariable int id,
            HttpSession session,
            Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return "redirect:/login";

        GameModel game = gameService.loadGame(id);
        if (game == null)
            return "redirect:/menu";

        if (game.getPlayerId() != userId)
            return "redirect:/menu";

        Map<String, Object> boardState = gameService.deserializeBoard(game.getBoardState());

        session.setAttribute("activeGame", game);

        model.addAttribute("game", game);
        model.addAttribute("boardState", boardState);
        model.addAttribute("gridSize", gameDifficultyService.getGridSize(game.getDifficulty()));

        return "game";
    }

    // SAVE GAME
    @PostMapping("/games/save")
    @ResponseBody
    public String saveGame(
            @RequestParam int gameId,
            @RequestParam String boardState,
            @RequestParam int triesLeft,
            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return "unauthorized";

        gameService.saveGame(gameId, boardState, triesLeft);
        session.removeAttribute("activeGame");

        return "saved";
    }

    // QUIT GAME
    @PostMapping("/games/quit")
    @ResponseBody
    public String quitGame(
            @RequestParam int gameId,
            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return "unauthorized";

        gameService.deleteGame(gameId);
        session.removeAttribute("activeGame");

        return "deleted";
    }

    // WIN / LOSE — game over, no save
    @PostMapping("/games/end")
    @ResponseBody
    public String endGame(
            @RequestParam int gameId,
            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return "unauthorized";

        gameService.deleteGame(gameId);
        session.removeAttribute("activeGame");

        return "ended";
    }
}