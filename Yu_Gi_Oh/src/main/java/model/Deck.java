package model;


import model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;


public class Deck implements Prototype{
    private SideDeck sideDeck;
    private String name;
    private MainDeck mainDeck;
    protected ArrayList<Card> userCardsAvailableToAdd;


    public Deck(String name) {
        setName(name);
        sideDeck = new SideDeck(name);
        mainDeck = new MainDeck(name);
        userCardsAvailableToAdd = new ArrayList<>();
        for (Card card :  User.currentUser.getUserCards()) {
            userCardsAvailableToAdd.add((Card) card.clone());
        }
    }

    @Override
    public Object clone() {
        Deck deck = new Deck(this.getName());
        deck.mainDeck = (MainDeck) this.mainDeck.clone();
        deck.sideDeck = (SideDeck) this.sideDeck.clone();
        return deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MainDeck getMainDeck() {
        return mainDeck;
    }

    public SideDeck getSideDeck() {
        return sideDeck;
    }

    public String getValidity() {
        return mainDeck.allCards.size()>=40 ? "valid" : "invalid";
    }

    public boolean isValid() {
        return mainDeck.allCards.size() >= 40 ? true: false;
    }

    public void deleteDeck() {

    }

    public boolean doesDeckHaveThreeOfThisCard(String cardName) {
        AtomicInteger counter = new AtomicInteger();
        for (Card card : this.mainDeck.allCards) {
            if (card.getName().equalsIgnoreCase(cardName)) counter.getAndIncrement();
        }
        for (Card card : this.sideDeck.allCards) {
            if (card.getName().equalsIgnoreCase(cardName)) counter.getAndIncrement();
        }
        if (counter.get() == 3) return true;
        else return false;
    }

    public void addCardToDeck(String cardName, String deckType) {
        if (deckType.equals("side")) {
            sideDeck.addCardToDeck(cardName);
        } else
            mainDeck.addCardToDeck(cardName);
    }

    public void removeCardFromDeck(String cardName, String deckType) {
        if (deckType.equals("side")) {
            sideDeck.removeCardFromDeck(cardName);
        } else
            mainDeck.removeCardFromDeck(cardName);
    }

    public boolean isThisCardAvailable(String cardName) {
        for (Card card : userCardsAvailableToAdd) {
            if (card.getName().equalsIgnoreCase(cardName))
                return true;
        }
        return false;
    }


    public void shuffleDeck() {
        Collections.shuffle(this.mainDeck.allCards);
        Collections.shuffle(this.sideDeck.allCards);
    }

}

