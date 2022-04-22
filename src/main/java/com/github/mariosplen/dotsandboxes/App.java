package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Board;
import com.github.mariosplen.dotsandboxes.models.Conf;
import com.github.mariosplen.dotsandboxes.views.BoardPane;
import com.github.mariosplen.dotsandboxes.views.ScorePane;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


public class App extends Application {

    static Stage stage;
    static Scene scene;
    static BorderPane borderPane;

    //private static final ObservableList<Score> scores = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MenuScreen.fxml"));
        Pane controller = fxmlLoader.load();

        // Weird bug fix UNKNOWN CAUSE When under scaling appears wrong background color
        controller.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        controller.getStylesheets().add(String.valueOf(App.class.getResource("BootstrapFX.css")));

        stage.setScene(applyScaling(controller));
        stage.show();

    }

    public static void beginNewGame(Conf gameConf) {

        Board board = new Board(gameConf);
        BoardPane boardPane = new BoardPane(board);

        ScorePane scorePane = new ScorePane(gameConf, board);

        borderPane = new BorderPane();
        borderPane.setCenter(boardPane);
        borderPane.setTop(scorePane);
















        // MUST  MAKE AUTOMATICALLY!! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        borderPane.setPrefWidth(500);
        borderPane.setPrefHeight(600);

        // Weird bug fix UNKNOWN CAUSE When under-scaling appears wrong background color
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        stage.setScene(applyScaling(borderPane));
    }

    private static Scene applyScaling(Pane controller) {

        final Pane root = new Pane();   //necessary evil
        root.getChildren().add(controller);     //necessary evil
        Scale scale = new Scale(1, 1, 0, 0);
        scale.xProperty().bind(root.widthProperty().divide(controller.getPrefWidth()));     //must match with the one in the controller
        scale.yProperty().bind(root.heightProperty().divide(controller.getPrefHeight()));   //must match with the one in the controller
        root.getTransforms().add(scale);

        scene = new Scene(root, controller.getPrefWidth(), controller.getPrefHeight());

        //add listener for the use of scene.setRoot()
        scene.rootProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Parent> arg0, Parent oldValue, Parent newValue) {
                scene.rootProperty().removeListener(this);
                scene.setRoot(root);
                ((Region) newValue).setPrefWidth(controller.getPrefWidth());     //make sure is a Region!
                ((Region) newValue).setPrefHeight(controller.getPrefHeight());   //make sure is a Region!
                root.getChildren().clear();
                root.getChildren().add(newValue);
                scene.rootProperty().addListener(this);

            }
        });

        return scene;
    }

    public static void refreshScreen(Conf gameConf, Board board) {
        borderPane.setTop(new ScorePane(gameConf, board));
    }


}
