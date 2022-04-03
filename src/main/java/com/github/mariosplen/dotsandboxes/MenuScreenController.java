package com.github.mariosplen.dotsandboxes;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuScreenController {

    private static final Font font = new Font(14);
    private static int size;
    private static String playerOneName;
    private static String playerTwoName;
    private static String playerOneAvatar;
    private static String playerTwoAvatar;
    private static String playerOneColor;
    private static String playerTwoColor;
    @FXML
    private Label playerOneNameLabel;
    @FXML
    private TextField playerOneNameField;
    @FXML
    private Label playerTwoNameLabel;
    @FXML
    private TextField playerTwoNameField;
    @FXML
    private Label playerOneAvatarLabel;
    @FXML
    private ComboBox<String> playerOneAvatarField;
    @FXML
    private Label playerTwoAvatarLabel;
    @FXML
    private ComboBox<String> playerTwoAvatarField;
    @FXML
    private Label playerOneColorLabel;
    @FXML
    private ColorPicker playerOneColorPicker;
    @FXML
    private Label playerTwoColorLabel;
    @FXML
    private ColorPicker playerTwoColorPicker;
    @FXML
    private Button startGameButton;

    public static int getSize() {
        return size;
    }

    static String getPlayerOneName() {
        return playerOneName;
    }

    static String getPlayerTwoName() {
        return playerTwoName;
    }

    static String getPlayerOneAvatar() {
        return playerOneAvatar;
    }

    static String getPlayerTwoAvatar() {
        return playerTwoAvatar;
    }

    static String getPlayerOneColor() {
        return playerOneColor;
    }

    static String getPlayerTwoColor() {
        return playerTwoColor;
    }

    public void initialize() {

        size = 5;
        playerOneNameLabel.setFont(font);
        playerTwoNameLabel.setFont(font);
        playerOneAvatarLabel.setFont(font);
        playerTwoAvatarLabel.setFont(font);
        playerOneColorLabel.setFont(font);
        playerTwoColorLabel.setFont(font);

        playerOneColorPicker.setValue(Color.BLACK);
        playerTwoColorPicker.setValue(Color.BLACK);

        startGameButton.setOnAction(actionEvent -> {
            playerOneName = playerOneNameField.getText();
            playerTwoName = playerTwoNameField.getText();

            playerOneAvatar = playerOneAvatarField.getValue();
            playerTwoAvatar = playerTwoAvatarField.getValue();


            playerOneColor = playerOneColorPicker.getValue().toString();
            playerTwoColor = playerTwoColorPicker.getValue().toString();


            App.startGame(size);
            App.startScene();
            App.setStage(App.getScene());


        });
    }
}
