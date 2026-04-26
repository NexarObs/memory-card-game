package com.jee.memorycardgame.model;

public class CardModel {
    private int index;
    private String value;
    private boolean matched;
    private boolean flipped;

    public CardModel() {}

    public CardModel(int index, String value) {
        this.index = index;
        this.value = value;
        this.matched = false;
        this.flipped = false;
    }

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public boolean isMatched() { return matched; }
    public void setMatched(boolean matched) { this.matched = matched; }

    public boolean isFlipped() { return flipped; }
    public void setFlipped(boolean flipped) { this.flipped = flipped; }
}