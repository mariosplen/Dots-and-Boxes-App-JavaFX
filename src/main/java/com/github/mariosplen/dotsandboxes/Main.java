package com.github.mariosplen.dotsandboxes;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage stage;

    private static Scene scene;

    static final int STAGE_DEFAULT_WIDTH = 300;
    static final int STAGE_DEFAULT_HEIGHT = 300;
    //static final int BUTTON_DEFAULT_FONT_SIZE = 40;




    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(Scene scene) {
        stage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);



        primaryStage.setTitle("Dots and Boxes");


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
