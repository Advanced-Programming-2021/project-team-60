package model;

import java.util.ArrayList;
import java.util.Comparator;

public class UserDecks {
    private ArrayList<Deck> allDecks;
    private Deck activeDeck;

    public UserDecks() {
        allDecks = new ArrayList<>();
    }

    public void createDeck(String deckName) {
        allDecks.add(new Deck(deckName));
    }

    public boolean isDeckNameUsedBefore(String deckName) {
        if (allDecks.size() > 0) {
            for (Deck deck : allDecks) {
                if (deck.getName().matches(deckName)) return true;
            }
            return false;
        }
        return false;
    }

    public void deleteDeck(String deckName) {
        allDecks.remove(this.getDeckByName(deckName));
    }

    public void setActiveDeck(String deckName) {
        activeDeck = getDeckByName(deckName);
    }

    public ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    public String showAllDecks() {
        StringBuilder allDecksStringBuilder = new StringBuilder();
        allDecksStringBuilder.append("Decks:\nActive deck:\n");
        if (activeDeck != null) {
            allDecksStringBuilder.append(activeDeck.getName() +
                    ": main deck " + activeDeck.getMainDeck().allCards.size() +
                    ",side deck " + activeDeck.getSideDeck().allCards.size() +
                    ", " + activeDeck.getValidity()+"\n");
        }
        allDecksStringBuilder.append("Other decks:");
        allDecks.sort(new DeckSorter());
        if (allDecks.size() > 0) {
            for (Deck deck : allDecks) {
                if(activeDeck !=null){
                    if (deck.getName().matches(activeDeck.getName())) continue;
                }
                allDecksStringBuilder.append("\n"+deck.getName() +
                        ": main deck " + deck.getMainDeck().allCards.size() +
                        ",side deck " + deck.getSideDeck().allCards.size() +
                        ", " + deck.getValidity());
            }
        }
        return allDecksStringBuilder.toString();
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : allDecks) {
            if (deck.getName().matches(deckName)) return deck;
        }
        return null;
    }
}
