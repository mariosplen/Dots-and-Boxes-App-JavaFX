package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Player;
import com.github.mariosplen.dotsandboxes.views.BoardPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.util.Objects;


public class MenuScreenController {


    private static int size;
    private static String playerOneName;
    private static String playerTwoName;
    private static Image playerOneAvatar;
    private static Image playerTwoAvatar;
    private static Color playerOneColor;
    private static Color playerTwoColor;
    Image im0;
    Image im1;
    int im0Index = 12;
    int im1Index = 1;
    @FXML
    private TextField playerOneNameField, playerTwoNameField;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker playerOneColorPicker, playerTwoColorPicker;
    @FXML
    private Button startGameButton;
    @FXML
    private Button p0NextButton, p0BackButton, p1NextButton, p1BackButton;
    @FXML
    private Circle circleAvatar0, circleAvatar1;

    public void initialize() {


        File[] images = new File("assets/icons").listFiles();

        assert images != null;
        im0 = new Image(String.valueOf(images[im0Index].toURI()));
        im1 = new Image(String.valueOf(images[im1Index].toURI()));

        playerOneAvatar = im0;
        playerTwoAvatar = im1;
        circleAvatar0.setFill(new ImagePattern(im0));
        circleAvatar1.setFill(new ImagePattern(im1));


        startGameButton.setOnAction(actionEvent -> {

            playerOneName = playerOneNameField.getText();
            if (Objects.equals(playerOneName, "")) {
                playerOneName = "Player1";
            }
            playerTwoName = playerTwoNameField.getText();
            if (Objects.equals(playerTwoName, "")) {
                playerTwoName = "Player2";
            }
            size = (int) sizeSlider.getValue();

            playerOneColor = playerOneColorPicker.getValue();
            playerTwoColor = playerTwoColorPicker.getValue();

            Player p0 = new Player(playerOneName, playerOneColor, playerOneAvatar);
            Player p1 = new Player(playerTwoName, playerTwoColor, playerTwoAvatar);

            BoardPane boardPane = new Game(size, p0, p1).getBoardPane();
            App.setNewScene(boardPane);

        });


        p0NextButton.setOnAction(actionEvent -> {
                    if (im0Index == 36) {
                        im0Index = 0;
                    } else {
                        im0Index++;
                    }
                    im0 = new Image(String.valueOf(images[im0Index].toURI()));
                    playerOneAvatar = im0;

                    circleAvatar0.setFill(new ImagePattern(im0));
                }
        );
        p1NextButton.setOnAction(actionEvent -> {
                    if (im1Index == 36) {
                        im1Index = 0;
                    } else {
                        im1Index++;
                    }
                    im1 = new Image(String.valueOf(images[im1Index].toURI()));
                    playerTwoAvatar = im1;

                    circleAvatar1.setFill(new ImagePattern(im1));
                }
        );
        p0BackButton.setOnAction(actionEvent -> {
                    if (im0Index == 0) {
                        im0Index = 36;
                    } else {
                        im0Index--;
                    }
                    im0 = new Image(String.valueOf(images[im0Index].toURI()));
                    playerOneAvatar = im0;

                    circleAvatar0.setFill(new ImagePattern(im0));
                }
        );
        p1BackButton.setOnAction(actionEvent -> {
                    if (im1Index == 0) {
                        im1Index = 36;
                    } else {
                        im1Index--;
                    }
                    im1 = new Image(String.valueOf(images[im1Index].toURI()));
                    playerTwoAvatar = im1;

                    circleAvatar1.setFill(new ImagePattern(im1));
                }
        );


    }
}
