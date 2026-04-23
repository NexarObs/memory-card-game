package com.jee.memorycardgame.model;

import java.sql.Timestamp;

public class GameModel {

    private int id;
    private int playerId;
    private String difficulty;
    private int triesLeft;
    private String boardState;
    private Timestamp createdAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public int getTriesLeft() { return triesLeft; }
    public void setTriesLeft(int triesLeft) { this.triesLeft = triesLeft; }

    public String getBoardState() { return boardState; }
    public void setBoardState(String boardState) { this.boardState = boardState; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

}