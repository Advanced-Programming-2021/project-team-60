package view.menus;

import controller.ImportExportMenuController;

public class ImportExportMenu extends Menu {
    private ImportExportMenuController importExportMenuController;

    protected ImportExportMenu(Menu parentMenu) {
        super("Import/Export", parentMenu);
        importExportMenuController = new ImportExportMenuController();
    }

    @Override
    public void run() {
        importExportMenuController.processCommand(input);
        runMenuCommands();
    }
}
