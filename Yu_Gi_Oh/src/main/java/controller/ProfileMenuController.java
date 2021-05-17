package controller;

import model.User;

import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("profile change --nickname (\\S+)")) changeNickname(command);
        else if (command.matches("profile change (?=.*(--password))(?=.*(--current \\S+))(?=.*(--new \\S+))" +
                "((\\1 \\2 \\3)|(\\1 \\3 \\2)|(\\2 \\1 \\3)|(\\2 \\3 \\1)|(\\3 \\1 \\2)|(\\3 \\2 \\1))"))
            changePassword(command);
        else print("invalid command");
    }

    public void changeNickname(String command) {
        Matcher nicknameMatcher = getMatcher("--nickname (\\S+)", command);
        if (nicknameMatcher.find()) {
            String nickname = nicknameMatcher.group(1);
            for (User user : User.getAllUsers()) {
                if (user.getNickname().equals(nickname)) {
                    print("user with nickname " + nickname + " already exists");
                    return;
                }
            }
            User.currentUser.setNickname(nickname);
            print("nickname changed successfully!");
        }
    }

    public void changePassword(String command) {
        Matcher currentPasswordMatcher = getMatcher("--current (\\S+)", command);
        Matcher newPasswordMatcher = getMatcher("--new (\\S+)", command);
        if (currentPasswordMatcher.find() && newPasswordMatcher.find()) {
            String currentPassword = currentPasswordMatcher.group(1);
            String newPassword = newPasswordMatcher.group(1);
            if (!User.currentUser.getPassword().equals(currentPassword)) print("current password is invalid");
            else if (User.currentUser.getPassword().equals(newPassword)) print("￼please enter a new password");
            else {
                User.currentUser.setPassword(newPassword);
                print("password changed successfully!");
            }
        }
    }
}