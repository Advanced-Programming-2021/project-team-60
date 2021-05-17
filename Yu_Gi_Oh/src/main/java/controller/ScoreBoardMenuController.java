package controller;

import model.User;

import java.util.Comparator;

public class ScoreBoardMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("scoreboard show")) showScoreboard();
        else print("invalid command");

    }

    public void showScoreboard() {
        int rank = 1;
        Comparator<User> userComparator = Comparator.comparing(User::getScore).reversed().thenComparing(User::getNickname);
        User.getAllUsers().sort(userComparator);
        int previousScore = User.getAllUsers().get(0).getScore();
        for (User user : User.getAllUsers()) {
            if (user.getScore() != previousScore) rank++;
            print(rank + "- " + user.getNickname() + ": " + user.getScore());
            previousScore = user.getScore();
        }
    }
}
