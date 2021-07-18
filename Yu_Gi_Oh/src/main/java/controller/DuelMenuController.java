package controller;


import model.User;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.Location;
import model.cards.Position;
import model.cards.monstercards.MonsterCard;
import model.cards.spellcards.Icon;
import model.cards.spellcards.SpellCard;
import model.game.Game;
import view.menus.DuelMenu;
import view.menus.MainMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class DuelMenuController extends Controller {
    private static DuelMenuController instance;
    private Boolean wasTribute;
    public Game game;

    private DuelMenuController() {
    }


    public static DuelMenuController getInstance() {
        if (instance == null)
            instance = new DuelMenuController();
        return instance;
    }


    public void processCommand(String command) {
        if (command.matches("select -d")) deselectCard();
        else if (command.matches("select ((--\\S+ \\d)|(--\\S+))"))
            selectFromOwnerCards(command.replace("select ", ""));
        else if (command.matches("select (?=.*(--\\b(?!opponent\\b)\\w+( \\d)?))(?=.*(--opponent))((\\1 \\3)|(\\3 \\1))")) {
            command = command.replace("--opponent", "");
            selectFromOpponentCards(command.replace("select ", "").trim());
        } else if (command.matches("next phase")) nextPhase();
        else if (command.matches("card show .*")) showCard(command.replace("card show ", ""));
        else if (command.equalsIgnoreCase("summon")) summon();
        else if (command.equalsIgnoreCase("set")) set();
        else if (command.matches("set --position ((attack)|(defence))"))
            changePosition(command.replace("set --position ", ""));
        else if (command.equalsIgnoreCase("flip-summon")) flipSummon();
        else if (command.matches("attack \\d")) {
            attack(Integer.parseInt(command.replace("attack ", "")));
        } else if (command.equalsIgnoreCase("attack direct")) attackDirect();
        else if (command.equalsIgnoreCase("activate effect")) activateEffect();
        else if (command.equalsIgnoreCase("show graveyard")) showGraveyard();
        else if (command.equalsIgnoreCase("surrender")) surrender();
        else if (command.matches("increase --LP \\d+")) cheatIncreaseLP(command.replace("increase --LP ", ""));
        else if (command.matches("duel set-winner \\S+"))
            cheatSetWinner(command.replace("duel set-winner ", ""));
        else print("invalid command");
    }

    private void cheatSetWinner(String nickname) {
        if (User.getUserByUsername(game.getOpponentPlayer().getUsername()).getNickname().equalsIgnoreCase(nickname))
            game.goToNextRound(game.getOpponentPlayer(), game.getCurrentPlayer());
        else if (User.getUserByUsername(game.getCurrentPlayer().getUsername()).getNickname().equalsIgnoreCase(nickname))
            game.goToNextRound(game.getCurrentPlayer(), game.getOpponentPlayer());
    }

    private void cheatIncreaseLP(String amount) {
        game.getCurrentPlayer().setLifePoint(game.getCurrentPlayer().getLifePoint() + Integer.parseInt(amount));
    }

    private void showCard(String cardName) {
        if (cardName.equalsIgnoreCase("--selected")) {
            if (game.getSelectedCard() == null)
                print("no card is selected yet");
            else if (game.getOpponentPlayer().getBoard().isCardForPlayer(game.getSelectedCard()) && game.getSelectedCard().isHidden())
                print("card is not visible");
            else print(game.getSelectedCard().toString());
        } else if (CardFactory.getCardByCardName(cardName) != null)
            print(CardFactory.getCardByCardName(cardName).toString());
        else
            print("card with name " + cardName + " does not exist");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void deselectCard() {
        if (game.getSelectedCard() == null)
            print("no card is selected yet");
        else {
            game.setSelectedCard(null);
            print("card deselected");
        }
    }

    private void selectFromOpponentCards(String location) {
        if (!location.matches("--((monster [12345])|(spell [12345])|(field))"))
            print("invalid selection");
        else if (game.getOpponentPlayer().getBoard().getCardInLocation(location) == null)
            print("no card found in the given position");
        else {
            game.setSelectedCard(game.getOpponentPlayer().getBoard().getCardInLocation(location));
            print("card selected");
        }
    }

    private void selectFromOwnerCards(String location) {
        if (!location.matches("--((monster [12345])|(spell [12345])|(field)|(hand [123456]))"))
            print("invalid selection");
        else if (game.getCurrentPlayer().getBoard().getCardInLocation(location) == null)
            print("no card found in the given position");
        else {
            game.setSelectedCard(game.getCurrentPlayer().getBoard().getCardInLocation(location));
            print("card selected");
        }
    }

    private void nextPhase() {
        game.nextPhase();
        print("phase: " + game.getPhase().toString());
        switch (game.getPhase()) {
            case DRAW:
                draw();
                break;
            case MAIN_1:
            case MAIN_2:
                showGameBoard();
                break;
            case END:
                game.setMonsterCardsSwitchedToFalse();
                game.setMonsterCardHasAttackedToFalse();
                game.setHasCardBeenSetOrSummoned(false);
                print("its " + game.getOpponentPlayer().getUsername() + "’s turn");
                game.changeTurn();
                game.resetCardsPutInThisTurn();
                game.setSelectedCard(null);
                break;
        }
    }

    private void draw() {
        String condition = game.getCurrentPlayer().getBoard().addCardFromDeckToHand();
        if (condition.equalsIgnoreCase("done")) {
            print("new card added to the hand : " +
                    game.getCurrentPlayer().getBoard().getHand().get(game.getCurrentPlayer().getBoard().getHand().size() - 1).getName());
        } else
            game.goToNextRound(game.getCurrentPlayer(), game.getOpponentPlayer());
    }

    private void summon() {
        CardHandler monsterHandler = new CardNotSelect();
        CardCantBeSummoned cardCantBeSummonedHandler = new CardCantBeSummoned();
        MonsterSummonNotAllowedInCurrentPhase notAllowedInCurrentPhase = new MonsterSummonNotAllowedInCurrentPhase();
        FullMonsterZone fullMonsterZoneHandler = new FullMonsterZone();
        CardAlreadySetOrSummoned cardAlreadySetOrSummoned = new CardAlreadySetOrSummoned();
        monsterHandler.setNextHandler(cardCantBeSummonedHandler);
        cardCantBeSummonedHandler.setNextHandler(notAllowedInCurrentPhase);
        notAllowedInCurrentPhase.setNextHandler(fullMonsterZoneHandler);
        fullMonsterZoneHandler.setNextHandler(cardAlreadySetOrSummoned);
        cardAlreadySetOrSummoned.setNextHandler(null);
        if (monsterHandler.handle(game.getSelectedCard())) {
            wasTribute = false;
            int level = ((MonsterCard) game.getSelectedCard()).getLevel();
            if (level <= 4) {
                tribute(0);
            } else if (level <= 6) {
                if (isEnoughTributeAvailable(1)) tribute(1);
                else print("there are not enough cards for tribute");
            } else if (level <= 8) {
                if (isEnoughTributeAvailable(2)) tribute(2);
                else print("there are not enough cards for tribute");
            }
            if (wasTribute) {
                print("summoned successfully");
                game.addCardToSetInThisTurn(game.getSelectedCard());
                game.getCurrentPlayer().getBoard().removeCardFromHand(game.getSelectedCard());
                game.getCurrentPlayer().getBoard().putCardInMonsterZone(game.getSelectedCard());
                ((MonsterCard) game.getSelectedCard()).setPosition(Position.ATTACK);
                game.getSelectedCard().activateEffect();
                game.setSelectedCard(null);
                game.setHasCardBeenSetOrSummoned(true);
                showGameBoard();
            }
        }
    }

    public void set() {
        CardHandler cardHandler = new CardNotSelect();
        CardCantBeSet cardCantBeSetHandler = new CardCantBeSet();
        CardSetOrChangeNotAllowedInCurrentPhase cardSetOrChangeNotAllowedInCurrentPhase = new CardSetOrChangeNotAllowedInCurrentPhase();
        CardHandler fullZone;
        if (game.getSelectedCard() instanceof MonsterCard) fullZone = new FullMonsterZone();
        else fullZone = new FullSpellAndTrapZone();
        cardHandler.setNextHandler(cardCantBeSetHandler);
        cardCantBeSetHandler.setNextHandler(cardSetOrChangeNotAllowedInCurrentPhase);
        cardSetOrChangeNotAllowedInCurrentPhase.setNextHandler(fullZone);
        if (game.getSelectedCard() instanceof MonsterCard) {
            CardAlreadySetOrSummoned cardAlreadySetOrSummoned = new CardAlreadySetOrSummoned();
            fullZone.setNextHandler(cardAlreadySetOrSummoned);
            cardAlreadySetOrSummoned.setNextHandler(null);
        } else fullZone.setNextHandler(null);
        if (cardHandler.handle(game.getSelectedCard())) {
            game.addCardToSetInThisTurn(game.getSelectedCard());
            print("set successfully");
            game.getCurrentPlayer().getBoard().removeCardFromHand(game.getSelectedCard());
            if (game.getSelectedCard() instanceof MonsterCard) {
                game.getCurrentPlayer().getBoard().putCardInMonsterZone(game.getSelectedCard());
                ((MonsterCard) game.getSelectedCard()).setPosition(Position.DEFENCE);
                game.setHasCardBeenSetOrSummoned(true);
            } else {
                game.getCurrentPlayer().getBoard().putCardInSpellAndTrapZone(game.getSelectedCard());
            }
            (game.getSelectedCard()).setHidden(true);
            game.setSelectedCard(null);
            showGameBoard();
        }
    }

    public void tribute(int numberOfTributes) {
        ArrayList<Integer> locationOfTributes = new ArrayList<>();
        for (int i = 0; i < numberOfTributes; ) {
            String string = DuelMenu.getInstance().selectMonstersToTribute();
            if (string.matches("\\d")) {
                locationOfTributes.add(Integer.parseInt(string));
                i++;
            } else if (string.equalsIgnoreCase("cancel")) {
                wasTribute = false;
                return;
            }
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
            Card card = monsterZone[location - 1];

            game.getCurrentPlayer().getBoard().removeCardFromMonsterZone(card);
            game.getCurrentPlayer().getBoard().putCardInGraveyard(card);
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

    public void activateEffect() {
        CardHandler cardHandler = new CardNotSelect();
        CardEffectCantBeActivated cardEffectCantBeActivated = new CardEffectCantBeActivated();
        EffectActivationNotAllowedInCurrentPhase effectActivationNotAllowedInCurrentPhase = new EffectActivationNotAllowedInCurrentPhase();
        CardAlreadyActivated cardAlreadyActivated = new CardAlreadyActivated();
        FullSpellAndTrapZone fullSpellAndTrapZone = new FullSpellAndTrapZone();
        SpellPreparationNotDone spellPreparationNotDone = new SpellPreparationNotDone();
        cardHandler.setNextHandler(cardEffectCantBeActivated);
        cardEffectCantBeActivated.setNextHandler(effectActivationNotAllowedInCurrentPhase);
        effectActivationNotAllowedInCurrentPhase.setNextHandler(cardAlreadyActivated);
        cardAlreadyActivated.setNextHandler(fullSpellAndTrapZone);
        fullSpellAndTrapZone.setNextHandler(spellPreparationNotDone);
        if (cardHandler.handle(game.getSelectedCard())) {
            Controller.print("spell activated");
            if (game.getSelectedCard().getLocation() == Location.HAND) {
                if (((SpellCard) game.getSelectedCard()).getIcon() == Icon.FIELD) {
                    game.getCurrentPlayer().getBoard().putCardInGraveyard(game.getCurrentPlayer().getBoard().getFieldZone());
                    game.getCurrentPlayer().getBoard().putCardInFieldZone(game.getSelectedCard());
                } else
                    game.getCurrentPlayer().getBoard().putCardInSpellAndTrapZone(game.getSelectedCard());
                game.getCurrentPlayer().getBoard().removeCardFromHand(game.getSelectedCard());
                game.getSelectedCard().activateEffect();
                game.getSelectedCard().setHasBeenActivated(true);
            }
            game.getSelectedCard().setHidden(false);
            game.setSelectedCard(null);
        }
    }

    public void changePosition(String positionToChange) {
        CardHandler monsterHandler = new CardNotSelect();
        CardPositionCantBeChanged cardPositionCantBeChanged = new CardPositionCantBeChanged();
        CardSetOrChangeNotAllowedInCurrentPhase monsterSetOrChangeNotAllowedInCurrentPhase = new CardSetOrChangeNotAllowedInCurrentPhase();
        monsterHandler.setNextHandler(cardPositionCantBeChanged);
        cardPositionCantBeChanged.setNextHandler(monsterSetOrChangeNotAllowedInCurrentPhase);
        if (monsterHandler.handle(game.getSelectedCard())) {
            if (((MonsterCard) game.getSelectedCard()).getPosition().getString().equalsIgnoreCase(positionToChange))
                Controller.print("this card is already in the wanted position");
            else if (((MonsterCard) game.getSelectedCard()).isSwitchedPosition())
                Controller.print("you already changed this card position in this turn");
            else {
                ((MonsterCard) game.getSelectedCard()).switchPosition();
                ((MonsterCard) game.getSelectedCard()).setSwitchedPosition(true);
                Controller.print("monster card position changed successfully");
                game.setSelectedCard(null);
            }
        }
        game.setSelectedCard(null);
    }


    public void flipSummon() {
        CardHandler monsterHandler = new CardNotSelect();
        CardPositionCantBeChanged cardPositionCantBeChanged = new CardPositionCantBeChanged();
        MonsterFlipSummonNotAllowedInCurrentPhase changeNotAllowedInCurrentPhase = new MonsterFlipSummonNotAllowedInCurrentPhase();
        CardCantBeFlipped cardCantBeFlipped = new CardCantBeFlipped();
        monsterHandler.setNextHandler(cardPositionCantBeChanged);
        cardPositionCantBeChanged.setNextHandler(changeNotAllowedInCurrentPhase);
        changeNotAllowedInCurrentPhase.setNextHandler(cardCantBeFlipped);
        if (monsterHandler.handle(game.getSelectedCard())) {
            game.getSelectedCard().setHidden(false);
            ((MonsterCard) game.getSelectedCard()).setPosition(Position.ATTACK);
            Controller.print("flip summoned successfully");
            game.getSelectedCard().activateEffect();
            game.setSelectedCard(null);
        }
    }

    public void attack(int positionNumber) {
        CardHandler monsterHandler = new CardNotSelect();
        CardCantAttack cardCantAttack = new CardCantAttack();
        MonsterAttackNotAllowedInCurrentPhase monsterAttackNotAllowedInCurrentPhase = new MonsterAttackNotAllowedInCurrentPhase();
        MonsterAlreadyAttacked monsterAlreadyAttacked = new MonsterAlreadyAttacked();
        monsterHandler.setNextHandler(cardCantAttack);
        cardCantAttack.setNextHandler(monsterAttackNotAllowedInCurrentPhase);
        monsterAttackNotAllowedInCurrentPhase.setNextHandler(monsterAlreadyAttacked);
        if (monsterHandler.handle(game.getSelectedCard())) {
            if (game.getOpponentPlayer().getBoard().getCardInLocation("--monster " + positionNumber) == null)
                Controller.print("there is no card to attack here");
            else {
                ((MonsterCard) game.getSelectedCard()).attackMonster((MonsterCard) game.getOpponentPlayer().getBoard().getCardInLocation("--monster " + positionNumber));
                ((MonsterCard) game.getSelectedCard()).setHasAttacked(true);
                game.setSelectedCard(null);
            }
        }
    }

    public void attackDirect() {
        CardHandler monsterHandler = new CardNotSelect();
        CardCantAttack cardCantAttack = new CardCantAttack();
        MonsterAttackNotAllowedInCurrentPhase monsterAttackNotAllowedInCurrentPhase = new MonsterAttackNotAllowedInCurrentPhase();
        MonsterAlreadyAttacked monsterAlreadyAttacked = new MonsterAlreadyAttacked();
        CardCantAttackDirectly cantAttackDirectly = new CardCantAttackDirectly();
        monsterHandler.setNextHandler(cardCantAttack);
        cardCantAttack.setNextHandler(monsterAttackNotAllowedInCurrentPhase);
        monsterAttackNotAllowedInCurrentPhase.setNextHandler(monsterAlreadyAttacked);
        monsterAlreadyAttacked.setNextHandler(cantAttackDirectly);
        if (monsterHandler.handle(game.getSelectedCard())) {
            ((MonsterCard) game.getSelectedCard()).attackLifePoint();
            ((MonsterCard) game.getSelectedCard()).setHasAttacked(true);
            game.setSelectedCard(null);
        }
    }


    public void showGameBoard() {
        StringBuilder board = new StringBuilder();
        board.append(game.getOpponentPlayer().getBoard().toStringAsOpponent());
        board.append("\n\n--------------------------\n\n");
        board.append(game.getCurrentPlayer().getBoard().toStringAsCurrent());
        print(board.toString());
    }

    public void showGraveyard() {
        Comparator<Card> cardComparator = Comparator.comparing(Card::getName);
        game.getCurrentPlayer().getBoard().getGraveyard().sort(cardComparator);
        if (game.getCurrentPlayer().getBoard().getGraveyard().isEmpty())
            print("graveyard empty");
        for (Card card : game.getCurrentPlayer().getBoard().getGraveyard()) {
            print(card.getName() + ":" + card.getDescription());
        }
        DuelMenu.getInstance().graveyard();
    }

    public void surrender() {
        game.goToNextRound(game.getOpponentPlayer(), game.getCurrentPlayer());
    }

    public static void endGame() {
        MainMenu.getInstance().runMenuCommands();
    }


}