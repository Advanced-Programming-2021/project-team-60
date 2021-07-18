package controller;

import model.User;

import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {
    public void processCommand(String command) {
        if (command.matches("profile change (--nickname|-n) (\\S+)")) changeNickname(command);
        else if (command.matches("profile change (?=.*(--password|-p))(?=.*(--current \\S+|-c \\S+))(?=.*(--new \\S+|-n \\S+))" +
                "((\\1 \\2 \\3)|(\\1 \\3 \\2)|(\\2 \\1 \\3)|(\\2 \\3 \\1)|(\\3 \\1 \\2)|(\\3 \\2 \\1))"))
            changePassword(command);
        else if (command.matches("profile change (--username|-u) (\\S+)")) changeUsername(command);
        else print("invalid command");
    }

    public void changeNickname(String command) {
        Matcher nicknameMatcher = getMatcher("(--nickname|-n) (\\S+)", command);
        if (nicknameMatcher.find()) {
            String nickname = nicknameMatcher.group(2);
            for (User user : User.getAllUsers()) {
                if (user.getNickname().equals(nickname)) {
                    print("user with nickname " + nickname + " already exists");
                    return;
                }
            }
            User.currentUser.setNickname(nickname);
            print("nickname changed successfully!");
            FileWriterAndReader.getInstance().write();
        }
    }

    public void changePassword(String command) {
        Matcher currentPasswordMatcher = getMatcher("(--current|-c) (\\S+)", command);
        Matcher newPasswordMatcher = getMatcher("(--new|-n) (\\S+)", command);
        if (currentPasswordMatcher.find() && newPasswordMatcher.find()) {
            String currentPassword = currentPasswordMatcher.group(2);
            String newPassword = newPasswordMatcher.group(2);
            if (!User.currentUser.getPassword().equals(currentPassword)) print("current password is invalid");
            else if (User.currentUser.getPassword().equals(newPassword)) print("￼please enter a new password");
            else {
                User.currentUser.setPassword(newPassword);
                print("password changed successfully!");
                FileWriterAndReader.getInstance().write();
            }
        }
    }
    public void changeUsername(String command){
        Matcher usernameMatcher = getMatcher("(--username|-u) (\\S+)", command);
        if (usernameMatcher.find()) {
            String username = usernameMatcher.group(2);
            for (User user : User.getAllUsers()) {
                if (user.getUsername().equals(username)) {
                    print("user with username " + username + " already exists");
                    return;
                }
            }
            User.currentUser.setUsername(username);
            print("username changed successfully!");
            FileWriterAndReader.getInstance().write();
        }

    }
}