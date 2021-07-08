package model.game;

import model.Deck;
import model.User;
import model.cards.Card;
import model.cards.Location;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.SpellCard;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> graveyard;
    private MonsterCard[] monsterZone;
    private Card[] spellAndTrapZone;
    private SpellCard fieldZone;
    private ArrayList<Card> hand;
    private Deck deck;

    public Board(Deck deck) {
        graveyard = new ArrayList<>();
        monsterZone = new MonsterCard[5];
        spellAndTrapZone = new Card[5];
        hand = new ArrayList<>();
        setDeck(deck);
        for (int i = 1; i < 6; i++) {
            addCardFromDeckToHand();
        }
        for (Card card : deck.getMainDeck().getAllCards()) {
            card.setLocation(Location.DECK);
            card.setBoard(this);
        }
        for (Card card : deck.getSideDeck().getAllCards()) {
            card.setLocation(Location.DECK);
            card.setBoard(this);
        }
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    public void putCardInFieldZone(Card card) {
        card.setLocation(Location.FIELD_ZONE);
        fieldZone = (SpellCard) card;
    }

    public void putCardInGraveyard(Card card) {
        card.setLocation(Location.GRAVEYARD);
        graveyard.add(card);
    }

    public void putCardInMonsterZone(Card card) {
        card.setLocation(Location.MONSTER_ZONE);
        for (int i = 0; i < monsterZone.length; i++) {
            if (monsterZone[i] == null) {
                monsterZone[i] = (MonsterCard) card;
                break;
            }
        }
    }

    public void putCardInSpellAndTrapZone(Card card) {
        card.setLocation(Location.SPELL_TRAP_ZONE);
        for (int i = 0; i < spellAndTrapZone.length; i++) {
            if (spellAndTrapZone[i] == null) {
                spellAndTrapZone[i] = card;
                break;
            }
        }
    }

    public void putCardInHand(Card card) {
        card.setLocation(Location.HAND);
        hand.add(card);
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public Card getFieldZone() {
        return fieldZone;
    }


    public MonsterCard[] getMonsterZone() {
        return monsterZone;
    }

    public Card[] getSpellAndTrapZone() {
        return spellAndTrapZone;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }


    public void removeCardFromGraveyard(Card card) {
        card.setLocation(null);
        graveyard.remove(card);
    }

    public void removeCardFromMonsterZone(Card card) {
        card.setLocation(null);
        for (int i = 0; i < monsterZone.length; i++) {
            if (monsterZone[i] == card) monsterZone[i] = null;
        }
    }

    public void removeCardFromHand(Card card) {
        card.setLocation(null);
        hand.remove(card);
    }

    public void removeCardFromFieldZone(Card card) {
        card.setLocation(null);
        fieldZone = null;
    }

    public void removeCardFromSpellTrapZone(Card card) {
        card.setLocation(null);
        for (int i = 0; i < 5; i++) {
            if (spellAndTrapZone[i] == card) {
                spellAndTrapZone[i] = null;
                break;
            }
        }
    }

    public void removeCardFromMainDeck(Card card) {
        card.setLocation(null);
        deck.getMainDeck().getAllCards().remove(card);
    }

    public Card getCardInLocation(String location) {
        if (location.matches("--monster \\d")) {
            int monsterNumber = Integer.parseInt(location.replace("--monster ", ""));
            return monsterZone[monsterNumber - 1];
        } else if (location.matches("--spell \\d")) {
            int spellNumber = Integer.parseInt(location.replace("--spell ", ""));
            return spellAndTrapZone[spellNumber - 1];
        } else if (location.matches("--hand \\d")) {
            int handNumber = Integer.parseInt(location.replace("--hand ", ""));
            if (handNumber > hand.size()) return null;
            return hand.get(handNumber - 1);
        } else if (location.matches("--field"))
            return fieldZone;
        return null;
    }

    public String addCardFromDeckToHand() {
        if (deck.getMainDeck().getAllCards().size() > 0) {
            Card card = deck.getMainDeck().getAllCards().get((deck.getMainDeck().getAllCards().size() - 1));
            removeCardFromMainDeck(card);
            putCardInHand(card);
            return "done";
        } else
            return "end";
    }

    public String toStringAsCurrent() {
        Player current = Game.getCurrentGame().getCurrentPlayer();
        StringBuilder returnString = new StringBuilder();
        if (fieldZone == null) returnString.append("E\t  \t  \t  \t  \t  \t");
        else returnString.append("O\t  \t  \t  \t  \t  \t");
        returnString.append(graveyard.size() + "\n\t");
        int[] counter = {5, 3, 1, 2, 4};
        for (int i : counter) {
            if (monsterZone[i - 1] == null) returnString.append("E \t");
            else {
                switch (monsterZone[i - 1].getPosition()) {
                    case ATTACK:
                        returnString.append("OO\t");
                        break;
                    case DEFENCE:
                        if (monsterZone[i - 1].isHidden()) returnString.append("DH\t");
                        else returnString.append("DO\t");
                        break;
                }
            }
        }
        returnString.append("\n\t");
        for (int i : counter) {
            if (spellAndTrapZone[i - 1] == null) returnString.append("E \t");
            else if (spellAndTrapZone[i - 1].isHidden()) returnString.append("H \t");
            else returnString.append("O \t");
        }
        returnString.append("\n  \t  \t  \t  \t  \t  \t" + deck.getMainDeck().getAllCards().size() + "\n");
        for (int i = 1; i <= hand.size(); i++) {
            returnString.append("c\t");
        }
        returnString.append("\n" + current.getUsername() + ":" + current.getLifePoint());
        return returnString.toString();
    }

    public boolean isCardForPlayer(Card card) {
        for (Card card1 : monsterZone) {
            if (card == card1) return true;
        }
        for (Card card1 : hand) {
            if (card == card1) return true;
        }
        for (Card card1 : spellAndTrapZone) {
            if (card == card1) return true;
        }
        for (Card card1 : graveyard) {
            if (card == card1) return true;
        }
        if (card == fieldZone) return true;
        return false;
    }

    public String toStringAsOpponent() {
        Player opponent = Game.getCurrentGame().getOpponentPlayer();
        StringBuilder returnString = new StringBuilder();
        returnString.append(opponent.getUsername() + ":" + opponent.getLifePoint() + "\n\t");
        for (int i = 0; i < hand.size(); i++) returnString.append("c \t");
        returnString.append("\n" + opponent.getBoard().deck.getMainDeck().getAllCards().size() + "\n\t");
        int[] counter = {4, 2, 1, 3, 5};
        for (int i : counter) {
            if (spellAndTrapZone[i - 1] == null) returnString.append("E \t");
            else if (spellAndTrapZone[i - 1].isHidden()) returnString.append("H \t");
            else returnString.append("O \t");
        }
        returnString.append("\n\t");
        for (int i : counter) {
            if (monsterZone[i - 1] == null) returnString.append("E \t");
            else {
                switch (monsterZone[i - 1].getPosition()) {
                    case ATTACK:
                        returnString.append("OO\t");
                        break;
                    case DEFENCE:
                        if (monsterZone[i - 1].isHidden()) returnString.append("DH\t");
                        else returnString.append("DO\t");
                        break;
                }
            }
        }
        returnString.append("\n" + opponent.getBoard().getGraveyard().size() + "\t  \t  \t  \t  \t  \t");
        if (opponent.getBoard().getCardInLocation("--field") == null) returnString.append("E ");
        else returnString.append("O ");
        return returnString.toString();
    }
}
