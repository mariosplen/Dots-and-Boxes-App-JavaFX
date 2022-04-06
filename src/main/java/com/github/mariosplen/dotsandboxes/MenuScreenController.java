package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Player;
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
import java.util.Arrays;


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
    private TextField playerOneNameField;
    @FXML
    private TextField playerTwoNameField;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker playerOneColorPicker;
    @FXML
    private ColorPicker playerTwoColorPicker;
    @FXML
    private Button startGameButton;
    @FXML
    private Button p0NextButton;
    @FXML
    private Button p0BackButton;
    @FXML
    private Button p1NextButton;
    @FXML
    private Button p1BackButton;
    @FXML
    private Circle circleAvatar0;
    @FXML
    private Circle circleAvatar1;

    public void initialize() {


        File[] images = new File("assets/icons").listFiles();


        System.out.println(Arrays.toString(images));
        assert images != null;
        im0 = new Image(String.valueOf(images[im0Index].toURI()));
        im1 = new Image(String.valueOf(images[im1Index].toURI()));
        circleAvatar0.setFill(new ImagePattern(im0));
        circleAvatar1.setFill(new ImagePattern(im1));


        startGameButton.setOnAction(actionEvent -> {
            playerOneName = playerOneNameField.getText();
            playerTwoName = playerTwoNameField.getText();
            size = (int) sizeSlider.getValue();


            playerOneColor = playerOneColorPicker.getValue();
            playerTwoColor = playerTwoColorPicker.getValue();

            Player p0 = new Player(playerOneName, playerOneColor, playerOneAvatar);
            Player p1 = new Player(playerTwoName, playerTwoColor, playerTwoAvatar);


            App.newGame(size, p0, p1);


        });


        p0NextButton.setOnAction(actionEvent -> {
                    if (im0Index == 36) {
                        im0Index = 0;
                    } else {
                        im0Index++;
                    }
                    circleAvatar0.setFill(new ImagePattern(new Image(String.valueOf(images[im0Index].toURI()))));
                }
        );
        p1NextButton.setOnAction(actionEvent -> {
                    if (im1Index == 36) {
                        im1Index = 0;
                    } else {
                        im1Index++;
                    }
                    circleAvatar1.setFill(new ImagePattern(new Image(String.valueOf(images[im1Index].toURI()))));
                }
        );
        p0BackButton.setOnAction(actionEvent -> {
                    if (im0Index == 0) {
                        im0Index = 36;
                    } else {
                        im0Index--;
                    }
                    circleAvatar0.setFill(new ImagePattern(new Image(String.valueOf(images[im0Index].toURI()))));
                }
        );
        p1BackButton.setOnAction(actionEvent -> {
                    if (im1Index == 0) {
                        im1Index = 36;
                    } else {
                        im1Index--;
                    }
                    circleAvatar1.setFill(new ImagePattern(new Image(String.valueOf(images[im1Index].toURI()))));
                }
        );


    }
}
