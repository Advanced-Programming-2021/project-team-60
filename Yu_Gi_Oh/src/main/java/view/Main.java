package view;


import controller.FileWriterAndReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.cards.CardFactory;
import view.menus.LoginMenu;
import view.menus.Menu;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CardFactory.readCVSOfCards();
        FileWriterAndReader.getInstance().read();
        LoginMenu loginMenu = new LoginMenu(null);
        loginMenu.setScanner(scanner);
        loginMenu.runMenuCommands();
       // launch(args);
    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
//        Scene scene = new Scene(pane);
//        stage.setScene(scene);
//        Menu.stage = stage;
//    }
}
