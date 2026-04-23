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
   public PlayerModel register(String username, String password) {

    if (playerRepository.existsByUsername(username)) {
        return null;
    }

    PlayerModel player = new PlayerModel();
    player.setUsername(username);
    player.setPassword(passwordEncoder.encode(password));

    playerRepository.save(player);

    return player;
}

    // ---------------- LOGIN ----------------

    public PlayerModel login(String username, String password) {
    Optional<PlayerModel> playerOpt = playerRepository.findByUsername(username);

    if (playerOpt.isEmpty()) return null;

    PlayerModel player = playerOpt.get();

    if (!passwordEncoder.matches(password, player.getPassword())) {
        return null;
    }

    return player;
}
}