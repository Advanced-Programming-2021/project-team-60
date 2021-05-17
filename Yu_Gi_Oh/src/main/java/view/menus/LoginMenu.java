package view.menus;

import controller.LoginMenuController;
import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    private static LoginMenu instance;
    private LoginMenuController loginMenuController;

    public LoginMenu(Menu parentMenu) {
        super("Login", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(new MainMenu(this));
        setSubMenus(submenus);
        loginMenuController = new LoginMenuController();
        instance = this;
    }

    public static LoginMenu getInstance() {
        if (instance == null) return new LoginMenu(null);
        else return instance;
    }

    @Override
    public void run() {
        loginMenuController.processCommand(input);
        runMenuCommands();
    }

}
