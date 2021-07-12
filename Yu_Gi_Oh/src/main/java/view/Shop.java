package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Shop extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL scoreBoard = getClass().getResource("/fxml/Shop.fxml");
        Parent root = FXMLLoader.load(scoreBoard);
        Scene scene = new Scene(root);
        stage.setTitle("Shop");
        root.getStyleClass().add("button");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
