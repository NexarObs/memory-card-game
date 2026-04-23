package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.model.GameModel;
import com.jee.memorycardgame.service.GameDifficultyService;
import com.jee.memorycardgame.service.GameService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameDifficultyService gameDifficultyService;

    @GetMapping("/game/{id}")
    public String showGame(
            @PathVariable int id,
            HttpSession session,
            Model model
    ) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        GameModel game = gameService.loadGame(id);

        if (game == null) {
            return "redirect:/games";
        }

        session.setAttribute("activeGame", game);

        int gridSize = gameDifficultyService.getGridSize(game.getDifficulty());

        model.addAttribute("game", game);
        model.addAttribute("difficulty", game.getDifficulty());
        model.addAttribute("gridSize", gridSize);

        return "game";
    }
}