package com.jee.memorycardgame.model;

public class PlayerModel {

    private int id;
    private String username;
    private String password;

    // spring utilise ce constructeur vide, pour créer l'objet automatiquement 
    // il remplis les champs avec setters 
    public PlayerModel(){}

    
    public void setId(int id){
        this.id= id;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}