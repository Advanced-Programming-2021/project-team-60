package model.game;

import model.Deck;
import model.User;

public class Player {
    private String username;
    private Board board;
    private int lifePoint = 8000;
    private int maxLifePoint;
    private int roundsWon = 0;

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

    public void renewBoard() {
        board = new Board(User.getUserByUsername(username).getUserDecks().getActiveDeck());
    }

    public String getUsername() {
        return username;
    }

    public int getRoundsWon() {
        return roundsWon;
    }


    public void addToRoundsWon() {
        roundsWon++;
    }

    public void win() {
        User user = User.getUserByUsername(username);
        user.setScore(user.getScore() + ((Game.getCurrentGame().getRound()) * (maxLifePoint + 1000)));
        user.setCoins(user.getCoins() + (Game.getCurrentGame().getRound()) * 1000);
    }

    public void lose() {
        User user = User.getUserByUsername(username);
        user.setCoins(user.getCoins() + ((Game.getCurrentGame().getRound()) * 100));


    }
}