package controller;

import model.Deck;
import model.User;
import model.cards.Card;
import model.cards.CardFactory;
import view.menus.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class DeckMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("deck create \\S+")) createDeck(command.split(" "));
        else if (command.matches("deck delete \\S+")) deleteDeck(command.split(" "));
        else if (command.matches("deck set-activate \\S+")) setDeckActive(command.split(" "));
        else if (command.matches("deck add-card (?=.*(--card (\\S+( \\b(?!--deck\\b)\\S+)*)))(?=.*(--deck \\S+))" +
                "((\\1 \\4( --side)?)|(\\4 \\1( --side)?)|(\\1 --side \\4)|(\\4 --side \\1)|((--side )?\\1 \\4)|((--side )?\\4 \\1))"))
            addCardToDeck(command);
        else if (command.matches("deck rm-card (?=.*(--card (\\S+( \\b(?!--deck\\b)\\S+)*)))(?=.*(--deck \\S+))" +
                "((\\1 \\4( --side)?)|(\\4 \\1( --side)?)|(\\1 --side \\4)|(\\4 --side \\1)|((--side )?\\1 \\4)|((--side )?\\4 \\1))"))
            removeCardFromDeck(command);
        else if (command.matches("deck show --all")) showAllDecks();
        else if (command.matches("deck show (?=.*(--deck-name \\S+))((\\1( --side)?)|(--side )?\\1)"))
            showDeck(command);
        else if (command.matches("deck show --cards")) showPurchasedCards();
        else if (command.matches("card show .*")) showCard(command.replace("card show ", ""));
        else print("invalid command");
    }

    private void showCard(String cardName) {
        if (CardFactory.getCardByCardName(cardName) != null)
            print(CardFactory.getCardByCardName(cardName).toString());
        else
            print("card with name "+cardName+" does not exist");
    }

    private void createDeck(String[] commandSplit) {
        if (User.currentUser.getUserDecks().isDeckNameUsedBefore(commandSplit[2]))
            print("deck with name " + commandSplit[2] + " already exists");
        else {
            new Deck(commandSplit[2]);
            User.currentUser.getUserDecks().createDeck(commandSplit[2]);
            print("deck created successfully!");
        }
    }

    private void deleteDeck(String[] commandSplit) {
        if (!User.currentUser.getUserDecks().isDeckNameUsedBefore(commandSplit[2]))
            print("deck with name " + commandSplit[2] + " does not exists");
        else {
            User.currentUser.getUserDecks().deleteDeck(commandSplit[2]);
            print("deck deleted successfully!");
        }
    }

    private void setDeckActive(String[] commandSplit) {
        if (!User.currentUser.getUserDecks().isDeckNameUsedBefore(commandSplit[2]))
            print("deck with name " + commandSplit[2] + " does not exists");
        else {
            User.currentUser.getUserDecks().setActiveDeck(commandSplit[2]);
            print("deck activated successfully!");
        }
    }

    private void addCardToDeck(String command) {
        Matcher cardNameMatcher = getMatcher("--card (\\S+( \\b(?!--deck\\b)\\S+)*)", command);
        Matcher deckNameMatcher = getMatcher("--deck (\\S+)", command);
        deckNameMatcher.find();
        cardNameMatcher.find();
        String cardName = cardNameMatcher.group(1);
        String deckName = deckNameMatcher.group(1);
        String deckType = command.contains("--side") ? "side" : "main";
        if (!User.currentUser.getUserDecks().getDeckByName(deckName).isThisCardAvailable(cardName))
            print("card with name " + cardName + " does not exist");
        else if (!User.currentUser.getUserDecks().isDeckNameUsedBefore(deckName))
            print("deck with name " + deckName + " does not exist");
        else if (!deckType.equals("side") && User.currentUser.getUserDecks().getDeckByName(deckName).getMainDeck().isDeckFull())
            print("main deck if full");
        else if (deckType.equals("side") && User.currentUser.getUserDecks().getDeckByName(deckName).getSideDeck().isDeckFull())
            print("side deck is full");
        else if (User.currentUser.getUserDecks().getDeckByName(deckName).doesDeckHaveThreeOfThisCard(cardName))
            print("there are already three cards with name " + cardName + " in deck " + deckName);
        else {
            User.currentUser.getUserDecks().getDeckByName(deckName).addCardToDeck(cardName, deckType);
            User.currentUser.removeCard(cardName);
            print("card added to deck successfully");
        }
    }

    private void removeCardFromDeck(String command) {
        Matcher cardNameMatcher = getMatcher("--card (\\S+( \\b(?!--deck\\b)\\S+)*)", command);
        Matcher deckNameMatcher = getMatcher("--deck (\\S+)", command);
        deckNameMatcher.find();
        cardNameMatcher.find();
        String cardName = cardNameMatcher.group(1);
        String deckName = deckNameMatcher.group(1);
        String deckType = command.contains("--side") ? "side" : "main";
        if (!User.currentUser.getUserDecks().isDeckNameUsedBefore(deckName))
            print("deck with name " + cardName + " does not exist");
        else if (deckName.equals("side") && User.currentUser.getUserDecks().getDeckByName(deckName).getSideDeck().doesDeckHaveThisCard(cardName))
            print("card with name " + cardName + " does not exist in " + deckType + " deck");
        else if (deckName.equals("main") && User.currentUser.getUserDecks().getDeckByName(deckName).getMainDeck().doesDeckHaveThisCard(cardName))
            Menu.print("card with name " + cardName + " does not exist in " + deckType + " deck");
        else {
            User.currentUser.getUserDecks().getDeckByName(deckName).removeCardFromDeck(cardName, deckType);
            User.currentUser.addCard(CardFactory.getCardByCardName(cardName));
            print("card removed form deck successfully");
        }
    }

    private void showAllDecks() {
        print(User.currentUser.getUserDecks().showAllDecks());
    }

    private void showDeck(String command) {
        String regex = "deck show (?=.*(--deck-name \\S+))((\\1( --side)?)|(--side )?\\1)";
        Matcher matcher = getMatcher(regex, command);
        matcher.find();
        String[] deckNameGroup = matcher.group(1).split(" ");
        if (!User.currentUser.getUserDecks().isDeckNameUsedBefore(deckNameGroup[1]))
            print("deck with name " + deckNameGroup[1] + " does not exist");
        else {
            if (command.contains("--side"))
                print(User.currentUser.getUserDecks().getDeckByName(deckNameGroup[1]).getSideDeck().toString());
            else
                print(User.currentUser.getUserDecks().getDeckByName(deckNameGroup[1]).getMainDeck().toString());
        }
    }

    private void showPurchasedCards() {
        ArrayList<Card> allUserCards = User.currentUser.getUserCards();
        Comparator<Card> userComparator = Comparator.comparing(Card::getName).thenComparing(Card::getName);
        allUserCards.sort(userComparator);
        for (Card card : allUserCards) {
            print(card.getName() + ":" + card.getDescription());
        }
    }
}
