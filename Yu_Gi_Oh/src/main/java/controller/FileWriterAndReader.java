package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Deck;
import model.User;
import model.cards.CardFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriterAndReader {
    private static FileWriterAndReader instance;

    private FileWriterAndReader() {
    }

    public static FileWriterAndReader getInstance() {
        if (instance == null)
            instance = new FileWriterAndReader();
        return instance;
    }


    public void write() {
        try {
            FileWriter writer = new FileWriter("Yu_Gi_Oh/src/main/resources/database/allUsers.json");
            writer.write(new Gson().toJson(User.getAllUsers()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(("Yu_Gi_Oh/src/main/resources/database/allUsers.json"))));
            if (!json.isEmpty()) {
                User.setAllUsers(new Gson().fromJson(json, new TypeToken<List<User>>() {
                }.getType()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        readUserCards();
    }

    public void readUserCards() {
        for (User user : User.getAllUsers()) {
            for (int i = 0; i < user.getUserCards().size(); i++) {
                String name = user.getUserCards().get(i).getName();
                user.getUserCards().remove(i);
                user.getUserCards().add(i, CardFactory.getCardByCardName(name));
            }
            for (Deck deck : user.getUserDecks().getAllDecks()) {
                for (int i = 0; i < deck.getUserCardsAvailableToAdd().size(); i++) {
                    String name = deck.getUserCardsAvailableToAdd().get(i).getName();
                    deck.getUserCardsAvailableToAdd().remove(i);
                    deck.getUserCardsAvailableToAdd().add(i, CardFactory.getCardByCardName(name));
                }
                for (int i = 0; i < deck.getMainDeck().getAllCards().size(); i++) {
                    String name = deck.getMainDeck().getAllCards().get(i).getName();
                    deck.getMainDeck().getAllCards().remove(i);
                    deck.getMainDeck().getAllCards().add(i, CardFactory.getCardByCardName(name));
                }
                for (int i = 0; i < deck.getSideDeck().getAllCards().size(); i++) {
                    String name = deck.getSideDeck().getAllCards().get(i).getName();
                    deck.getSideDeck().getAllCards().remove(i);
                    deck.getSideDeck().getAllCards().add(i, CardFactory.getCardByCardName(name));
                }
            }
            for (int i = 0; i < user.getUserDecks().getActiveDeck().getUserCardsAvailableToAdd().size(); i++) {
                String name = user.getUserDecks().getActiveDeck().getUserCardsAvailableToAdd().get(i).getName();
                user.getUserDecks().getActiveDeck().getUserCardsAvailableToAdd().remove(i);
                user.getUserDecks().getActiveDeck().getUserCardsAvailableToAdd().add(i, CardFactory.getCardByCardName(name));
            }
            for (int i = 0; i < user.getUserDecks().getActiveDeck().getMainDeck().getAllCards().size(); i++) {
                String name = user.getUserDecks().getActiveDeck().getMainDeck().getAllCards().get(i).getName();
                user.getUserDecks().getActiveDeck().getMainDeck().getAllCards().remove(i);
                user.getUserDecks().getActiveDeck().getMainDeck().getAllCards().add(i, CardFactory.getCardByCardName(name));
            }
            for (int i = 0; i < user.getUserDecks().getActiveDeck().getSideDeck().getAllCards().size(); i++) {
                String name = user.getUserDecks().getActiveDeck().getSideDeck().getAllCards().get(i).getName();
                user.getUserDecks().getActiveDeck().getSideDeck().getAllCards().remove(i);
                user.getUserDecks().getActiveDeck().getSideDeck().getAllCards().add(i, CardFactory.getCardByCardName(name));
            }
        }
    }



}
