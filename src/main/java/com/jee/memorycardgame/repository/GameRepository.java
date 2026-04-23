package com.jee.memorycardgame.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.jee.memorycardgame.model.GameModel;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class GameRepository {

    @Autowired
    private DataSource dataSource;

    // CREATE GAME
    public int createGame(GameModel game) {
        String sql = "INSERT INTO games (player_id, difficulty, tries_left, board_state) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, game.getPlayerId());
            ps.setString(2, game.getDifficulty());
            ps.setInt(3, game.getTriesLeft());
            ps.setString(4, game.getBoardState());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    // GET USER GAMES
    public List<GameModel> findByPlayerId(int playerId) {
        List<GameModel> games = new ArrayList<>();
        String sql = "SELECT * FROM games WHERE player_id = ? ORDER BY created_at DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GameModel g = new GameModel();
                g.setId(rs.getInt("id"));
                g.setDifficulty(rs.getString("difficulty"));
                g.setCreatedAt(rs.getTimestamp("created_at"));
                games.add(g);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return games;
    }

    // LOAD GAME
    public GameModel findById(int id) {
        String sql = "SELECT * FROM games WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                GameModel g = new GameModel();
                g.setId(rs.getInt("id"));
                g.setPlayerId(rs.getInt("player_id"));
                g.setDifficulty(rs.getString("difficulty"));
                g.setBoardState(rs.getString("board_state"));
                return g;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}