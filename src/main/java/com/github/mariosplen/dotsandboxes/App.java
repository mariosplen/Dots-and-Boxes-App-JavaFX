package com.github.mariosplen.dotsandboxes;

//import com.github.mariosplen.dotsandboxes.models.Game;

import com.github.mariosplen.dotsandboxes.models.Board;
import com.github.mariosplen.dotsandboxes.models.Conf;
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

    public static void setNewScene(BoardPane boardPane) {


        BorderPane mainBorderPane = new BorderPane();

        //BoardPane boardPane = new BoardPane(game);


        mainBorderPane.setCenter(boardPane);

        scene = new Scene(mainBorderPane);
        stage.setScene(scene);


    }

    public static void beginNewGame(Conf gameConf) {

        Board board = new Board(gameConf);
        BoardPane boardPane = new BoardPane(board);

        stage.setScene(new Scene(boardPane));

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MenuScreen.fxml"));

        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(App.class.getResource("BootstrapFX.css")));

        stage.setTitle("Dots & Boxes");
        stage.setScene(scene);
        stage.show();


    }


}
