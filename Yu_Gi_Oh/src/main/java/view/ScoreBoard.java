package view;

import controller.ScoreBoardMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import view.menus.ScoreBoardMenu;

import java.net.URL;
import java.util.ArrayList;

public class ScoreBoard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL scoreBoard = getClass().getResource("/fxml/ScoreBoard.fxml");
        Parent root = FXMLLoader.load(scoreBoard);
        Scene scene = new Scene(root);
        stage.setTitle("ScoreBoard");
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

    public void showScoreBoard(MouseEvent mouseEvent) {
        ScoreBoardMenuController.showScoreboard();
    }
}
