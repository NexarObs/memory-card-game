package com.jee.memorycardgame.controller;

import com.jee.memorycardgame.model.PlayerModel;
import com.jee.memorycardgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage(HttpSession session) {

        if (session.getAttribute("userId") != null) {
            return "redirect:/games";
        }

        return "login";
    }

    // ================= REGISTER PAGE =================
    @GetMapping("/register")
    public String registerPage(HttpSession session) {

        if (session.getAttribute("userId") != null) {
            return "redirect:/games";
        }

        return "register";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        PlayerModel player = playerService.login(username, password);

        if (player != null) {

            
            session.setAttribute("userId", player.getId());
            session.setAttribute("username", player.getUsername());

            return "redirect:/games";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model,
                           HttpSession session) {

        PlayerModel player = playerService.register(username, password);

        if (player != null) {

            session.setAttribute("userId", player.getId());
            session.setAttribute("username", player.getUsername());

            return "redirect:/games";
        }

        model.addAttribute("error", "Registration failed");
        return "register";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}