package controller;

import model.User;
import model.cards.Card;
import model.cards.CardFactory;

import java.util.Comparator;
import java.util.regex.Matcher;

public class ShopMenuController extends Controller {

    public void processCommand(String command) {
        if (command.matches("shop buy .*")) buyCard(command);
        else if (command.matches("shop show --all")) showAllCards();
        else if (command.matches("card show .*")) showCard(command.replace("card show ",""));
        else print("invalid command");
    }

    private void showCard (String cardName) {
        print(CardFactory.getCardByCardName(cardName).toString());
    }

    private void buyCard(String command) {
        Matcher cardNameMatcher = getMatcher("shop buy (.*)", command);
        if (cardNameMatcher.find()) {
            String cardName = cardNameMatcher.group(1);
            if (CardFactory.getCardByCardName(cardName) == null)
                print("there is no card with this name");
            else {
                Card card = CardFactory.getCardByCardName(cardName);
                if (User.currentUser.getCoins() < card.getPrice()) print("not enough money");
                else User.currentUser.addCard(card);
            }
        }
    }

    private void showAllCards() {
        Comparator<Card> cardComparator = Comparator.comparing(Card::getName);
        CardFactory.getAllCards().sort(cardComparator);
        for (Card card : CardFactory.getAllCards()) {
            print(card.getName() + ":" + card.getPrice());
        }
    }
}
