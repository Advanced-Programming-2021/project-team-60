package controller;


import model.cards.Card;
import model.cards.CardFactory;
import model.cards.Position;
import model.cards.monstercards.MonsterCard;
import model.game.Game;
import view.menus.DeckMenu;
import view.menus.DuelMenu;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DuelMenuController extends Controller {
    private static DuelMenuController instance;
    public Game game;

    private DuelMenuController() {
    }


    public static DuelMenuController getInstance() {
        return instance;
    }


    public void processCommand(String command) {
        if (command.matches("select -d")) deselectCard();
        else if (command.matches("select ((--\\S+ \\d)|(--\\S+))"))
            selectFromOwnerCards(command.replace("select ", ""));
        else if (command.matches("select (?=.*(--\\b(?!opponent\\b)\\w+))(?=.*(--opponent))((\\1 \\2)|(\\2 \\1))")) {
            command = command.replace("--opponent", "");
            selectFromOpponentCards(command.replace("select ", ""));
        } else if (command.matches("next phase")) nextPhase();
        else if (command.matches("card show .*")) showCard(command.replace("card show ", ""));
    }

    private void showCard(String cardName) {
        print(CardFactory.getCardByCardName(cardName).toString());
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void deselectCard() {
        if (game.getSelectedCard() == null)
            print("no card is selected yet");
        else
            game.setSelectedCard(null);
    }

    private void selectFromOpponentCards(String location) {
        if (!location.matches("--((monster \\d{1,5})|(spell \\d{1,5})|(field)|(hand \\d{1,6}))"))
            print("invalid selection");
        if (game.getOpponentPlayer().getBoard().getCardInLocation(location) == null)
            print("no card found in the given position");
        else
            game.setSelectedCard(game.getOpponentPlayer().getBoard().getCardInLocation(location));
    }

    private void selectFromOwnerCards(String location) {
        if (!location.matches("--((monster \\d{1,5})|(spell \\d{1,5})|(field)|(hand \\d{1,6}))"))
            print("invalid selection");
        if (game.getCurrentPlayer().getBoard().getCardInLocation(location) == null)
            print("no card found in the given position");
        else
            game.setSelectedCard(game.getCurrentPlayer().getBoard().getCardInLocation(location));
    }

    private void nextPhase() {
        game.nextPhase();
        print("phase: " + game.getPhase().toString());
        switch (game.getPhase()) {
            case DRAW:
                draw();
            case MAIN_1:
                showGameBoard();
            case END:
                game.setHasCardBeenSetOrSummoned(false);
                print("its <next player nickname>’s turn");
        }
    }

    private void draw() {
        game.getCurrentPlayer().getBoard().addCardFromDeckToHand();
        print("new card added to the hand : " +
                game.getCurrentPlayer().getBoard().getHand().get(game.getCurrentPlayer().getBoard().getHand().size() - 1));
    }

    private void summon() {
        SummonSetHandler summonSetHandler = new CardNotSelectHandler();
        CardCantBeSummonedHandler cardCantBeSummonedHandler = new CardCantBeSummonedHandler();
        MonsterSummonNotAllowedInCurrentPhase notAllowedInCurrentPhase = new MonsterSummonNotAllowedInCurrentPhase();
        FullMonsterZoneHandler fullMonsterZoneHandler = new FullMonsterZoneHandler();
        CardAlreadySetOrSummoned cardAlreadySetOrSummoned = new CardAlreadySetOrSummoned();
        summonSetHandler.setNextHandler(cardCantBeSummonedHandler);
        cardCantBeSummonedHandler.setNextHandler(notAllowedInCurrentPhase);
        notAllowedInCurrentPhase.setNextHandler(fullMonsterZoneHandler);
        fullMonsterZoneHandler.setNextHandler(cardAlreadySetOrSummoned);
        cardAlreadySetOrSummoned.setNextHandler(null);
        if (summonSetHandler.handle(game.getSelectedCard())) {
            Boolean wasTribute = false;
            int level = ((MonsterCard) game.getSelectedCard()).getLevel();
            if (level <= 4) {
                tribute(0, wasTribute);
            } else if (level <= 6) {
                if (isEnoughTributeAvailable(1)) tribute(1, wasTribute);
                else print("there are not enough cards for tribute");
            } else if (level <= 8) {
                if (isEnoughTributeAvailable(2)) tribute(2, wasTribute);
                else print("there are not enough cards for tribute");
            }
            if (wasTribute) {
                print("summoned successfully");
                game.getCurrentPlayer().getBoard().putCardInMonsterZone(game.getSelectedCard());
                game.getCurrentPlayer().getBoard().removeCardFromHand(game.getSelectedCard());
                ((MonsterCard)game.getSelectedCard()).setPosition(Position.ATTACK);
                deselectCard();
                game.setHasCardBeenSetOrSummoned(true);
            }
        }
    }

    public void set() {
        SummonSetHandler summonSetHandler = new CardNotSelectHandler();
        CardCantBeSetHandler cardCantBeSetHandler = new CardCantBeSetHandler();
        MonsterSetNotAllowedInCurrentPhase  monsterSetNotAllowedInCurrentPhase = new MonsterSetNotAllowedInCurrentPhase();
        FullMonsterZoneHandler fullMonsterZoneHandler = new FullMonsterZoneHandler();
        CardAlreadySetOrSummoned cardAlreadySetOrSummoned = new CardAlreadySetOrSummoned();
        summonSetHandler.setNextHandler(cardCantBeSetHandler);
        cardCantBeSetHandler.setNextHandler(monsterSetNotAllowedInCurrentPhase);
        monsterSetNotAllowedInCurrentPhase.setNextHandler(fullMonsterZoneHandler);
        fullMonsterZoneHandler.setNextHandler(cardAlreadySetOrSummoned);
        cardAlreadySetOrSummoned.setNextHandler(null);
        if (summonSetHandler.handle(game.getSelectedCard())) {
            print("set successfully");
            game.getCurrentPlayer().getBoard().putCardInMonsterZone(game.getSelectedCard());
            game.getCurrentPlayer().getBoard().removeCardFromHand(game.getSelectedCard());
            ((MonsterCard)game.getSelectedCard()).setPosition(Position.ATTACK);
            deselectCard();
            game.setHasCardBeenSetOrSummoned(true);
        }

    }

    public void tribute(int numberOfTributes, Boolean wasTribute) {
        ArrayList<Integer> locationOfTributes = new ArrayList<>();
        for (int i = 0; i < numberOfTributes; i++) {
            locationOfTributes.add(DuelMenu.getInstance().selectMonstersToTribute());
        }
        for (Integer location : locationOfTributes) {
            if (game.getCurrentPlayer().getBoard().getCardInLocation("--monster " + location) == null) {
                if (numberOfTributes == 1)
                    print("there no monsters one this address");
                else
                    print("there is no monster on one of these addresses");
                return;
            }
        }
        Card[] monsterZone = game.getCurrentPlayer().getBoard().getMonsterZone();
        for (Integer location : locationOfTributes) {
            game.getCurrentPlayer().getBoard().removeCardFromMonsterZone(monsterZone[location]);
            game.getCurrentPlayer().getBoard().putCardInGraveyard(monsterZone[location]);
        }
        wasTribute = true;
    }



    public boolean isEnoughTributeAvailable(int numberOfTributes) {
        MonsterCard[] monsterZone = game.getCurrentPlayer().getBoard().getMonsterZone();
        AtomicInteger counter = new AtomicInteger();
        for (MonsterCard monsterCard : monsterZone) {
            if (monsterCard != null)
                counter.getAndIncrement();
        }
        return (counter.intValue() >= numberOfTributes);
    }

    public void showGameBoard() {
        StringBuilder board = new StringBuilder();
        board.append(game.getOpponentPlayer().showBoard()).reverse();
        board.append(game.getCurrentPlayer().showBoard());
        print(board.toString());
    }
    public static void main(String[] args) {
        DuelMenuController.getInstance().showGameBoard();
    }
}
