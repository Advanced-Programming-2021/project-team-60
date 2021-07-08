package view.menus;

import controller.DuelMenuController;


public class DuelMenu extends Menu {
    private static DuelMenu instance;
    private DuelMenuController duelMenuController;

    protected DuelMenu(Menu parentMenu) {
        super("Duel", parentMenu);
        duelMenuController = DuelMenuController.getInstance();
        instance = this;
    }

    public static DuelMenu getInstance() {
        return instance;
    }

    public String selectMonstersToTribute() {
        print("enter the tribute location number");
        String nextLine = scan.nextLine();
        return nextLine;
    }

    public void graveyard() {
        String nextLine = scan.nextLine();
        while (!nextLine.equalsIgnoreCase("back"))
            nextLine = scan.nextLine();
        return;
    }

    @Override
    public void run() {
        duelMenuController.processCommand(input);
        runMenuCommands();
    }
}
