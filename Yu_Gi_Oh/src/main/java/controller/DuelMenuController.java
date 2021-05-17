package controller;


import model.game.Game;

public class DuelMenuController extends Controller {
    private static DuelMenuController instance;
    public Game game;

    private DuelMenuController() {
    }


    public static DuelMenuController getInstance() {
        return instance;
    }


    public void processCommand(String command) {
        if (command.matches("select -d")) deselectCard(command);
        else if (command.matches("select ((--\\S+ \\d)|(--\\S+))"))
            selectFromOwnerCards(command.replace("select ", ""));
        else if (command.matches("select (?=.*(--\\b(?!opponent\\b)\\w+))(?=.*(--opponent))((\\1 \\2)|(\\2 \\1))")) {
            command = command.replace("--opponent", "");
            selectFromOpponentCards(command.replace("select ", ""));
        } else if (command.matches("next phase")) nextPhase();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void deselectCard(String command) {
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
                print("its <next player nickname>’s turn");
        }
    }

    private void draw() {
        game.getCurrentPlayer().getBoard().addCardFromDeckToHand();
        print("new card added to the hand : "+
                game.getCurrentPlayer().getBoard().getHand().get(game.getCurrentPlayer().getBoard().getHand().size()-1));
    }

    /*private void summon () {
        CardNotSelectHandler cardNotSelectHandler = new CardNotSelectHandler();
        CardCantBeSummonedHandler cardCantBeSummonedHandler = new CardCantBeSummonedHandler();
        NotAllowedInCurrentPhase notAllowedInCurrentPhase = new NotAllowedInCurrentPhase();
        FullMonsterZoneHandler fullMonsterZoneHandler = new FullMonsterZoneHandler();
        CardAlreadySetOrSummoned cardAlreadySetOrSummoned = new CardAlreadySetOrSummoned();
        cardAlreadySetOrSummoned.setNextHandler(cardNotSelectHandler);
        cardCantBeSummonedHandler.setNextHandler(notAllowedInCurrentPhase);
        notAllowedInCurrentPhase.setNextHandler(fullMonsterZoneHandler);
        fullMonsterZoneHandler.setNextHandler(cardNotSelectHandler);
        cardAlreadySetOrSummoned.setNextHandler(null);

    }*/

    public void showGameBoard() {
        StringBuilder board = new StringBuilder();
        board.append(game.getOpponentPlayer().showBoard()).reverse();
        board.append(game.getCurrentPlayer().showBoard());
        print(board.toString());
    }
}
