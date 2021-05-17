package view.menus;

import controller.ImportExportMenuController;

import java.io.IOException;

public class ImportExportMenu extends Menu {
    private ImportExportMenuController importExportMenuController;

    protected ImportExportMenu(Menu parentMenu) {
        super("Import/Export", parentMenu);
        importExportMenuController = new ImportExportMenuController();
    }

    @Override
    public void run() {
        try {
            importExportMenuController.processCommand(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        runMenuCommands();
    }
}
