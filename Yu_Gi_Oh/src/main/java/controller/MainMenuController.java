package controller;

import model.User;
import model.game.Game;
import view.menus.*;

import java.util.regex.Matcher;

public class MainMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("user logout")) logout();
        else if (command.matches("duel (?=.*(--new))(?=.*(--second-player \\S+))(?=.*(--rounds \\d))" +
                "((\\1 \\2 \\3)|(\\2 \\1 \\3)|(\\1 \\3 \\2)|(\\2 \\3 \\1)|(\\3 \\2 \\1)|(\\3 \\1 \\2))"))
            startNewGame(command);
        else if (command.matches("duel (?=.*(--new))(?=.*(--ai))(?=.*(--rounds \\d))" +
                "((\\1 \\2 \\3)|(\\2 \\1 \\3)|(\\1 \\3 \\2)|(\\2 \\3 \\1)|(\\3 \\2 \\1)|(\\3 \\1 \\2))"))
            statNewGameWithAI(command);
        else print("invalid command");
    }

    public void logout() {
        User.currentUser = null;
        print("user logged out successfully!");
        LoginMenu.getInstance().runMenuCommands();
    }

    private void startNewGame(String command) {
        Matcher roundsMatcher = getMatcher("--rounds (\\d)", command);
        Matcher secondPlayerUsernameMatcher = getMatcher("--second-player (\\S+)", command);
        if (roundsMatcher.find() && secondPlayerUsernameMatcher.find()) {
           int rounds = Integer.parseInt(roundsMatcher.group(1));
            String secondPlayerUsername = secondPlayerUsernameMatcher.group(1);
            if (User.getUserByUsername(secondPlayerUsername) == null)
                print("there is no player with this username");
            if (isUserActiveDeckAvailableAndValid(User.currentUser) && isUserActiveDeckAvailableAndValid(User.getUserByUsername(secondPlayerUsername))) {
                if (rounds > 3 || rounds < 1) print("number of rounds is not supported");
                else {
                    Game game = new Game(User.currentUser.getUsername(), secondPlayerUsername, rounds,
                            User.getUserByUsername(secondPlayerUsername).userDecks.getActiveDeck(), User.currentUser.userDecks.getActiveDeck());
                    DuelMenuController.getInstance().setGame(game);
                    DeckMenu.getInstance().runMenuCommands();
                }
            }
        }
    }


    private void statNewGameWithAI(String command) {
        Matcher roundsMatcher = getMatcher("--rounds (\\d),", command);
        int rounds = 0;
        if (roundsMatcher.find()) rounds = Integer.parseInt(roundsMatcher.group(1));
        if (rounds > 3 || rounds < 1) print("number of rounds is not supported");
        //todo
    }

    private boolean isUserActiveDeckAvailableAndValid(User user) {
        if (user.getUserDecks().getActiveDeck() == null) {
            print(user.getUsername() + " has no active deck");
            return false;
        } else if (user.getUserDecks().getActiveDeck().isValid) {
            print(user.getUsername() + "’s deck is invalid");
            return false;
        }
        return true;
    }
}
