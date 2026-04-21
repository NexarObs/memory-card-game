package com.jee.memorycardgame.service;

import com.jee.memorycardgame.model.PlayerModel;
import com.jee.memorycardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ---------------- REGISTER ----------------
    public String register(String username, String password) {

        // check if player existe
        if (playerRepository.existsByUsername(username)) {
            return "USERNAME_ALREADY_EXISTS";
        }

        // create player
        PlayerModel player = new PlayerModel();
        player.setUsername(username);
        player.setPassword(passwordEncoder.encode(password));
        player.setScore(0);

        playerRepository.save(player);

        return "REGISTER_SUCCESS";
    }

    // ---------------- LOGIN ----------------
    public String login(String username, String password) {

        Optional<PlayerModel> playerOpt = playerRepository.findByUsername(username);

        //if player not found
        if (playerOpt.isEmpty()) {
            return "USER_NOT_FOUND";
        }

        PlayerModel player = playerOpt.get();

        // check password
        if (!passwordEncoder.matches(password, player.getPassword())) {
            return "WRONG_PASSWORD";
        }

        return "LOGIN_SUCCESS";
    }
}