package model;

import model.cards.Card;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.SpellCard;
import model.cards.trapcards.TrapCard;

import java.util.ArrayList;
import java.util.Comparator;

public class SideDeck implements Prototype {
    protected ArrayList<Card> allCards;
    private String name;

    public SideDeck (String name) {
        allCards = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCardToDeck(String cardName) {
        Card card = null;
        ArrayList<Card> userCardsNotInDeck =User.currentUser.getUserDecks().getDeckByName(name).userCardsAvailableToAdd;
        for (Card userCard : userCardsNotInDeck) {
            if (userCard.getName().equalsIgnoreCase(cardName)){
                allCards.add(userCard);
                card = userCard;
                break;
            }
        }
        userCardsNotInDeck.remove(card);
    }

    public void removeCardFromDeck(String cardName) {
        Card card = null;
        ArrayList<Card> userCardsNotInDeck =User.currentUser.getUserDecks().getDeckByName(name).userCardsAvailableToAdd;
        for (Card allCard : allCards) {
            if (allCard.getName().equalsIgnoreCase(cardName)){
                card = allCard;
                userCardsNotInDeck.add(allCard);
                break;
            }
        }
        allCards.remove(card);
    }

    @Override
    public Object clone() {
        SideDeck sideDeck = new SideDeck(name);
        sideDeck.allCards = (ArrayList) this.allCards.clone();
        return sideDeck;
    }

     public Boolean doesDeckHaveThisCard(String cardName) {
        if (allCards.size() > 0) {
            for (Card card : allCards) {
                if (card.getName().equals(cardName)) return true ;
            }
            return false;
        }
        return false;
     }


    public boolean isDeckFull() {
        if (this.allCards.size() >= 15)
            return true;
        return false;
    }

    @Override
    public String toString() {
        Comparator<Card> userComparator = Comparator.comparing(Card::getName).thenComparing(Card::getName);
        allCards.sort(userComparator);
        StringBuilder returnString = new StringBuilder();
        returnString.append("Deck:\nSide deck:\nMonsters:\n");
        for (Card card : allCards) {
            if (card instanceof MonsterCard)
                returnString.append(card.getName() + ":" + card.getDescription() + "\n");
        }
        returnString.append("Spell and Trap:\n");
        for (Card card : allCards) {
            if ((card instanceof TrapCard)|(card instanceof SpellCard))
                returnString.append(card.getName() + ":" + card.getDescription() + "\n");
        }
        returnString.deleteCharAt(returnString.length()-1);
        return returnString.toString();
    }
}
