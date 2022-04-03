package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Game;
import com.github.mariosplen.dotsandboxes.views.GamePane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Game game;
    private static Stage stage;
    private static Scene scene;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Scene newScene) {
        stage.setScene(newScene);
    }

    public static Game getGame() {
        return game;
    }

    public static void startGame(int size) {
        game = new Game(size);

    }

    public static void startScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new GamePane());

        scene = new Scene(borderPane);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MenuScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);


        primaryStage.setTitle("Dots and Boxes");


        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
