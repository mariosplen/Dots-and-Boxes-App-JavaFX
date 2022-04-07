package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Game;
import com.github.mariosplen.dotsandboxes.models.Player;
import com.github.mariosplen.dotsandboxes.views.BoardPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    static Stage stage;
    static Scene scene;

    public static void newGame(int size, Player player0, Player player1) {

        Game game = new Game(size, player0, player1);
        BorderPane mainBorderPane = new BorderPane();

        BoardPane boardPane = new BoardPane(game);

        mainBorderPane.setCenter(boardPane);
        mainBorderPane.setLeft(boardPane.setText());

        scene = new Scene(mainBorderPane);
        stage.setScene(scene);


    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MenuScreen.fxml"));
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("Dots & Boxes");
        stage.setScene(scene);
        stage.show();


    }


}
