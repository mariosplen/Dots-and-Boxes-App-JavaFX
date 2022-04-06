package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class MenuScreenController {


    private static int size;
    private static String playerOneName;
    private static String playerTwoName;
    private static Image playerOneAvatar;
    private static Image playerTwoAvatar;
    private static Color playerOneColor;
    private static Color playerTwoColor;

    @FXML
    private TextField playerOneNameField;

    @FXML
    private TextField playerTwoNameField;

    @FXML
    private Slider sizeSlider;

//    @FXML
//    private ComboBox<String> playerOneAvatarField;
//
//    @FXML
//    private Image playerTwoAvatarField;

    @FXML
    private ColorPicker playerOneColorPicker;

    @FXML
    private ColorPicker playerTwoColorPicker;
    @FXML
    private Button startGameButton;


    public void initialize() {


        startGameButton.setOnAction(actionEvent -> {
            playerOneName = playerOneNameField.getText();
            playerTwoName = playerTwoNameField.getText();
            size = (int) sizeSlider.getValue();
//            playerOneAvatar = playerOneAvatarField.getValue();
//            playerTwoAvatar = playerTwoAvatarField.getValue();


            playerOneColor = playerOneColorPicker.getValue();
            playerTwoColor = playerTwoColorPicker.getValue();

            Player p0 = new Player(playerOneName, playerOneColor, playerOneAvatar);
            Player p1 = new Player(playerTwoName, playerTwoColor, playerTwoAvatar);


            App.newGame(size, p0, p1);


        });
    }
}
