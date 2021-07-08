package view.menus;

import controller.MainMenuController;
import view.Main;

import java.util.ArrayList;

public class MainMenu extends Menu {
    private static MainMenu instance;
    private MainMenuController mainMenuController;

    public MainMenu(Menu parentMenu) {
        super("Main", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(new DeckMenu(this));
        submenus.add(new DuelMenu(this));
        submenus.add(new ProfileMenu(this));
        submenus.add(new ScoreBoardMenu(this));
        submenus.add(new ShopMenu(this));
        submenus.add(new ImportExportMenu(this));
        setSubMenus(submenus);
        mainMenuController = new MainMenuController();
        instance = this;
    }

    public static MainMenu getInstance() {
        return instance;
    }

    @Override
    public void run() {
        mainMenuController.processCommand(input);
        runMenuCommands();
    }
}
