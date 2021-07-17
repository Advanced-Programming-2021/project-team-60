package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

public class CardDeck extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL cardDeck = getClass().getResource("/fxml/CardDeck.fxml");
        Parent root = FXMLLoader.load(cardDeck);
        Scene scene = new Scene(root);
        stage.setTitle("CardDeck");
        root.getStyleClass().add("backGround");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void showCardDeck(MouseEvent mouseEvent) {
    }
}
