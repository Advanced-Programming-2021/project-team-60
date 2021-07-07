package model;

import model.cards.Card;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.SpellCard;
import model.cards.trapcards.TrapCard;

import java.util.ArrayList;
import java.util.Comparator;

public class MainDeck implements Prototype {
    protected ArrayList<Card> allCards;
    private String name;

    public MainDeck(String name) {
        allCards = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCardToDeck(String cardName) {
        Card card = null;
        ArrayList<Card> userCardsAvailableToAdd = User.currentUser.getUserDecks().getDeckByName(name).userCardsAvailableToAdd;
        for (Card userCard : userCardsAvailableToAdd) {
            if (userCard.getName().equalsIgnoreCase(cardName)) {
                card = userCard;
                allCards.add(card);
                break;
            }
        }
        userCardsAvailableToAdd.remove(card);
    }

    public void removeCardFromDeck(String cardName) {
        Card card = null;
        for (Card allCard : allCards) {
            if (allCard.getName().equalsIgnoreCase(cardName)) {
                card = allCard;
                User.currentUser.getUserDecks().getDeckByName(name).userCardsAvailableToAdd.add(allCard);
                break;
            }
        }
        allCards.remove(card);
    }

    @Override
    public Object clone() {
        MainDeck mainDeck = new MainDeck(this.name);
        mainDeck.allCards = (ArrayList) this.allCards.clone();
        return mainDeck;
    }

    public Boolean doesDeckHaveThisCard(String cardName) {
        if (allCards.size() > 0) {
            for (Card card : allCards) {
                if (card.getName().equals(cardName)) return true;
            }
            return false;
        }
        return false;
    }

    public boolean isDeckFull() {
        if (this.allCards.size() >= 60)
            return true;
        return false;
    }

    @Override
    public String toString() {
        Comparator<Card> userComparator = Comparator.comparing(Card::getName).thenComparing(Card::getName);
        allCards.sort(userComparator);
        StringBuilder returnString = new StringBuilder();
        returnString.append("Deck:\nMain deck:\nMonsters:\n");
        for (Card card : allCards) {
            if (card instanceof MonsterCard)
                returnString.append(card.getName() + ":" + card.getDescription() + "\n");
        }
        returnString.append("Spell and Trap:\n");
        for (Card card : allCards) {
            if ((card instanceof TrapCard) | (card instanceof SpellCard))
                returnString.append(card.getName() + ":" + card.getDescription() + "\n");
        }
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.toString();
    }

}
