package model.game;

import controller.Controller;
import controller.DuelMenuController;
import controller.FileWriterAndReader;
import model.Deck;
import model.cards.Card;
import model.cards.monstercards.MonsterCard;

import java.util.ArrayList;

public class Game {
    private static Game currentGame;
    private Phases phase = Phases.DRAW;
    private int round;
    private int roundCounter;
    private Card selectedCard;
    private Player opponentPlayer;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private boolean hasCardBeenSetOrSummoned = false;
    private ArrayList<Card> cardsPutInThisTurn;

    public Game(String userPlayerUsername, String opponentPlayerUsername, int round, Deck userPlayerDeck, Deck opponentPlayerDeck) {
        player2 = new Player(opponentPlayerUsername, (Deck) opponentPlayerDeck.clone());
        player1 = new Player(userPlayerUsername, (Deck) userPlayerDeck.clone());
        setCurrentPlayer(player1);
        setOpponentPlayer(player2);
        setRound(round);
        roundCounter = 0;
        currentGame = this;
        cardsPutInThisTurn = new ArrayList<>();
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

    public int getRound() {
        return round;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public boolean hasCardBeenSetOrSummoned() {
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


    public void changeTurn() {
        if (currentPlayer == player1) {
            setCurrentPlayer(player2);
            setOpponentPlayer(player1);
        } else {
            setCurrentPlayer(player1);
            setOpponentPlayer(player2);
        }
    }

    public void setMonsterCardsSwitchedToFalse() {
        for (MonsterCard card : currentPlayer.getBoard().getMonsterZone()) {
            if (card != null)
                card.setSwitchedPosition(false);
        }
    }

    public void addCardToSetInThisTurn(Card card) {
        cardsPutInThisTurn.add(card);
    }

    public void resetCardsPutInThisTurn() {
        for (int i = 0; i < cardsPutInThisTurn.size(); i++) {
            cardsPutInThisTurn.remove(i);
        }
    }

    public boolean isCardPutInThisTurn(Card card) {
        for (Card card1 : cardsPutInThisTurn) {
            if (card1 == card) return true;
        }
        return false;
    }

    public void goToNextRound(Player winner, Player loser) {
        roundCounter++;
        Controller.print(winner.getUsername() + " won the game and the score is: " + winner.getLifePoint() + "-" + loser.getLifePoint());
        winner.addToRoundsWon();
        if (round == roundCounter || (winner.getRoundsWon() == 2 && round == 3)) {
            Controller.print(winner.getUsername() + " won the whole match with score: " + winner.getLifePoint() + "-" + loser.getLifePoint());
            winner.win();
            loser.lose();
            FileWriterAndReader.getInstance().write();
            DuelMenuController.endGame();
        } else {
            winner.renewBoard();
            loser.renewBoard();
            phase = Phases.DRAW;
        }
    }

    public void setMonsterCardHasAttackedToFalse() {
        for (MonsterCard monsterCard : player2.getBoard().getMonsterZone()) {
            if (monsterCard != null) monsterCard.setHasAttacked(false);
        }
        for (MonsterCard monsterCard : player1.getBoard().getMonsterZone()) {
            if (monsterCard != null) monsterCard.setHasAttacked(false);
        }
    }
}