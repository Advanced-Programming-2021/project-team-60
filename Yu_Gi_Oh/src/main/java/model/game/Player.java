package model.game;

import model.Deck;

public class Player {
    private String username;
    private Board board;
    private int lifePoint = 8000;

    public Player(String name, Deck deck) {
        setUsername(name);
        deck.shuffleDeck();
        board = new Board(deck);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public Board getBoard() {
        return board;
    }

    public String getUsername() {
        return username;
    }
}


