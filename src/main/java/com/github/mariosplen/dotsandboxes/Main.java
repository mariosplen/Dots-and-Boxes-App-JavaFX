package com.github.mariosplen.dotsandboxes;

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

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();


        GameStart game = new GameStart(3, List.of(new String[]{"Marios", "Kostas"}));

        Scene scene = new Scene(game.getBoard(), 800, 800);

        stage.setScene(scene);
        stage.show();
    }
}