package controller;

import model.User;
import view.menus.MainMenu;

import java.util.regex.Matcher;

public class LoginMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("user create (?=.*(--username \\S+|-u \\S+))(?=.*(--nickname \\S+|-n \\S+))(?=.*(--password \\S+|-p \\S+))" +
                "((\\1 \\2 \\3)|(\\1 \\3 \\2)|(\\2 \\1 \\3)|(\\2 \\3 \\1)|(\\3 \\1 \\2)|(\\3 \\2 \\1))"))
            createUser(command);
        else if (command.matches("user login (?=.*(--username \\S+|-u \\S+))(?=.*(--password \\S+|-p \\S+))((\\1 \\2)|(\\2 \\1))"))
            login(command);
        else print("invalid command");
    }

    public void createUser(String command) {
        Matcher usernameMatcher = getMatcher("(--username|-u) (\\S+)", command);
        Matcher nicknameMatcher = getMatcher("(--nickname|-n) (\\S+)", command);
        Matcher passwordMatcher = getMatcher("(--password|-p) (\\S+)", command);
        if (usernameMatcher.find() && nicknameMatcher.find() && passwordMatcher.find()) {
            String username = usernameMatcher.group(2);
            String nickname = nicknameMatcher.group(2);
            String password = passwordMatcher.group(2);
            for (User user : User.getAllUsers()) {
                if (user.getUsername().equals(username)) {
                    print("user with username " + username + " already exists");
                    return;
                } else if (user.getNickname().equals(nickname)) {
                    print("user with nickname " + nickname + " already exists");
                    return;
                }
            }
            new User(username, password, nickname);
            print("user created successfully!");
        }
    }

    public void login(String command) {
        Matcher usernameMatcher = getMatcher("(--username|-u) (\\S+)", command);
        Matcher passwordMatcher = getMatcher("(--password|-p) (\\S+)", command);
        if (usernameMatcher.find() && passwordMatcher.find()) {
            String username = usernameMatcher.group(2);
            String password = passwordMatcher.group(2);
            if (!User.getAllUsers().contains(User.getUserByUsername(username)))
                print("Username and password didn’t match!");
            else if (!User.getUserByUsername(username).getPassword().equals(password))
                print("Username and password didn’t match!");
            else {
                User.currentUser = User.getUserByUsername(username);
                print("user logged in successfully!");
                MainMenu.getInstance().runMenuCommands();
            }
        }
    }
}
