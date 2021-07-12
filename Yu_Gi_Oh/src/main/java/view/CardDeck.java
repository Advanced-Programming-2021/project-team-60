package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class CardDeck extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL scoreBoard = getClass().getResource("/fxml/CardDeck.fxml");
        Parent root = FXMLLoader.load(scoreBoard);
        Scene scene = new Scene(root);
        stage.setTitle("ScoreBoard");
        root.getStyleClass().add("button");
        stage.setScene(scene);
        stage.show();
    }
}
