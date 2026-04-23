package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.service.GameService;
import com.jee.memorycardgame.model.GameModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/games")
public class MenuController {

    @Autowired
    private GameService gameService;

    // MENU PAGE
    @GetMapping("")
    public String menu(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) return "redirect:/login";

        model.addAttribute("games", gameService.getPlayerGames(userId));

        return "menu";
    }

    // NEW GAME
    @PostMapping("/new")
    public String newGame(@RequestParam String difficulty, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        int gameId = gameService.createGame(userId, difficulty);

        return "redirect:/game" + gameId;
    }

    // LOAD GAME
    @GetMapping("/load/{id}")
    public String loadGame(@PathVariable int id) {
        return "redirect:/game/save" + id;
    }

    // PLAY PAGE
    @GetMapping("/play/{id}")
    public String playGame(@PathVariable int id, Model model) {

        GameModel game = gameService.loadGame(id);
        model.addAttribute("game", game);

        return "game";
    }
}