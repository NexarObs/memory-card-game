package com.jee.memorycardgame.repository;

import com.jee.memorycardgame.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
public class PlayerRepository {
 
    @Autowired
    private DataSource dataSource;
 
    /**
     *trouve player par username
     * Retourne Optional.empty() si non trouvé.
     * sinon elle remplis playerModel
     */
    public Optional<PlayerModel> findByUsername(String username) {
        String sql = "SELECT id, username, password FROM players WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PlayerModel player = mapRow(rs);
                    return Optional.of(player);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByUsername: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
 
    // verifie l'existance de player par username return booleen
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM players WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur existsByUsername: " + e.getMessage(), e);
        }
    }
 
    // save player in database
    public void save(PlayerModel player) {
        String sql = "INSERT INTO players (username, password) VALUES ( ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getPassword());
            ps.executeUpdate();
 
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    player.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save player: " + e.getMessage(), e);
        }
    }
 
    // remplire le model
    private PlayerModel mapRow(ResultSet rs) throws SQLException {
        PlayerModel p = new PlayerModel();
        p.setId(rs.getInt("id"));
        p.setUsername(rs.getString("username"));
        p.setPassword(rs.getString("password"));
        return p;
    }
}