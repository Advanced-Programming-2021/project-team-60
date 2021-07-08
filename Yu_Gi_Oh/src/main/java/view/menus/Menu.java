package view.menus;


import controller.FileWriterAndReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    private String name;
    private Menu parentMenu;
    private ArrayList<Menu> subMenus;
    protected static Scanner scan;
    protected String input = "";
    public static Stage stage;

    protected Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public static void setScanner(Scanner scanner) {
        scan = scanner;
    }

    public void setSubMenus(ArrayList<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void runMenuCommands() {
        input = scan.nextLine();
        if (input.matches("menu exit")) {
            if (this.parentMenu != null)
                parentMenu.runMenuCommands();
            else {
                FileWriterAndReader.getInstance().write();
                System.exit(1);
            }
        } else if (input.matches("menu show-current")) {
            print(this.name);
            this.runMenuCommands();
        } else if (input.matches("menu enter \\S+") ) {
            if (this.subMenus != null) {
                String[] inputSplit = input.split("\\s");
                int counter = -1;
                boolean isSubmenuFounded = false;
                for (Menu subMenu : this.subMenus) {
                    counter++;
                    if (subMenu.name.equalsIgnoreCase(inputSplit[2])) {
                        isSubmenuFounded = true;
                        break;
                    }
                }
                if (isSubmenuFounded && !(this.name.matches("Login") && !(this.subMenus.get(counter).name.equalsIgnoreCase("duel") )))
                    this.subMenus.get(counter).runMenuCommands();
                else {
                    print("menu navigation is not possible");
                    this.runMenuCommands();
                }
            }
            else {
                print("menu navigation is not possible");
                this.runMenuCommands();
            }
        } else {
            this.run();
        }
    }

    protected static Matcher getMatcher(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }


    protected abstract void run();

    public static void print(String output) {
        System.out.println(output);
    }


}
