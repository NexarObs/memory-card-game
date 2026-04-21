package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //register page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        String result = playerService.login(username, password);

        if (result.equals("LOGIN_SUCCESS")) {
            return "redirect:/game";
        }

        model.addAttribute("error", result);
        return "login";
    }

    // --------- REGISTER ---------
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {

        String result = playerService.register(username, password);

        if (result.equals("REGISTER_SUCCESS")) {
            return "redirect:/game";
        }

        model.addAttribute("error", result);
        return "register";
    }
}
