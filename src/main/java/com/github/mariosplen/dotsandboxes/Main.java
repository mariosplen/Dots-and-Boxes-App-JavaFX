package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.logic.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

// FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
// Scene scene = new Scene(fxmlLoader.load(), 320, 240);


        Game game = new Game(2, 4, List.of(new String[]{"Marios", "John"}));
        GameGUI gameGUI = new GameGUI(game);

        Scene scene = new Scene(gameGUI.getBoard(), 800, 800);

        stage.setScene(scene);
        stage.show();
    }
}