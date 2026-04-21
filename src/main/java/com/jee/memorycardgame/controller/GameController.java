package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.model.GameModel;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/game")
    public String showGame(HttpSession session, Model model) {

        GameModel activeGame =
                (GameModel) session.getAttribute("activeGame");

        model.addAttribute("game", activeGame);

        return "game";
    }
}