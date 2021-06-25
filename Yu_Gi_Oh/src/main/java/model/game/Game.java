package model.game;

import controller.DuelMenuController;
import model.Deck;
import model.cards.Card;

public class Game {
    private static Game currentGame;
    private Phases phase = Phases.DRAW;
    private int round;
    private Card selectedCard;
    private Player opponentPlayer;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private boolean hasCardBeenSetOrSummoned = false;

    public Game(String userPlayerUsername, String opponentPlayerUsername, int round, Deck userPlayerDeck, Deck opponentPlayerDeck) {
        player2 = new Player(opponentPlayerUsername, (Deck) opponentPlayerDeck.clone());
        player1 = new Player(userPlayerUsername, (Deck) userPlayerDeck.clone());
        setCurrentPlayer(player1);
        setOpponentPlayer(player2);
        setRound(round);
        currentGame = this;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public void setHasCardBeenSetOrSummoned(boolean hasCardBeenSetOrSummoned) {
        this.hasCardBeenSetOrSummoned = hasCardBeenSetOrSummoned;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public boolean hasCardBeenSetOrSummoned () {
        return hasCardBeenSetOrSummoned;
    }

    public Phases getPhase() {
        return phase;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public void nextPhase() {
        phase = phase.next();
    }

    public void endGame(Player winner) {

    }

    public void changeTurn() {
        if (currentPlayer == player1) {
            setCurrentPlayer(player2);
            setOpponentPlayer(player1);
        }else  {
            setCurrentPlayer(player1);
            setOpponentPlayer(player2);
        }
    }

}
