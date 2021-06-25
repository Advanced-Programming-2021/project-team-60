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

    public int selectMonstersToTribute() {
        print("enter the tribute location number");
        String nextLine = scan.nextLine();
        if (nextLine.matches("\\d"))
            return Integer.parseInt(nextLine);
        else
            selectMonstersToTribute();
        return 0;
    }

    @Override
    public void run() {
        duelMenuController.processCommand(input);
        runMenuCommands();
    }
}
