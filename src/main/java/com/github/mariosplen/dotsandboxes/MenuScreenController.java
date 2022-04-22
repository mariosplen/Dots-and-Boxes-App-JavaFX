package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Conf;
import com.github.mariosplen.dotsandboxes.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;


public class MenuScreenController {

    File[] images;
    @FXML
    private TextField playerOneNameField, playerTwoNameField;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker playerOneColorPicker, playerTwoColorPicker;
    @FXML
    private Button startGameButton, p0NextButton, p0BackButton, p1NextButton, p1BackButton;
    @FXML
    private Circle circleAvatar0, circleAvatar1;
    private String playerOneName;
    private String playerTwoName;
    private Image playerOneAvatar;
    private Image playerTwoAvatar;
    private Image im0;
    private Image im1;
    private int im0Index = 0;
    private int im1Index = 0;

    public void initialize() {

        // Get images from folder
        images = new File("assets/icons").listFiles();

        setDefaultAvatars(images);

        // Check for valid parameters and start game
        startGameButton.setOnAction(actionEvent -> startGameButtonPressed());

        // Cycle between avatar buttons
        p0NextButton.setOnAction(actionEvent -> changeAvatar(true, true));
        p1NextButton.setOnAction(actionEvent -> changeAvatar(false, true));
        p0BackButton.setOnAction(actionEvent -> changeAvatar(true, false));
        p1BackButton.setOnAction(actionEvent -> changeAvatar(false, false));


    }

    private void setDefaultAvatars(File[] images) {
        // Set default avatars
        if (images != null && images.length > 1) {
            im0 = new Image(String.valueOf(images[im0Index].toURI()));
            im1 = new Image(String.valueOf(images[++im1Index].toURI()));
        } else {
            throw new RuntimeException("Avatar assets not found!");
        }
        playerOneAvatar = im0;
        playerTwoAvatar = im1;
        circleAvatar0.setFill(new ImagePattern(im0));
        circleAvatar1.setFill(new ImagePattern(im1));

    }

    private void startGameButtonPressed() {
        if (checkIfNamesValid()) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Names must be 8 or less characters long", ButtonType.CLOSE);
            alert.showAndWait();
        } else {
            // Get size from slider
            int size = (int) sizeSlider.getValue();

            // Get player colors
            Color playerOneColor = playerOneColorPicker.getValue();
            Color playerTwoColor = playerTwoColorPicker.getValue();

            // Create players
            Player p0 = new Player(playerOneName, playerOneColor, playerOneAvatar);
            Player p1 = new Player(playerTwoName, playerTwoColor, playerTwoAvatar);


            // Start new game with game parameters
            App.beginNewGame(new Conf(size, p0, p1, true));
        }

    }

    private void changeAvatar(boolean isPlayer0, boolean isNextButton) {
        if (isPlayer0) {
            if (isNextButton) {
                if (im0Index == images.length - 1) {
                    im0Index = 0;
                } else {
                    im0Index++;
                }
            } else {
                if (im0Index == 0) {
                    im0Index = images.length - 1;
                } else {
                    im0Index--;
                }
            }
            im0 = new Image(String.valueOf(images[im0Index].toURI()));
            playerOneAvatar = im0;
            circleAvatar0.setFill(new ImagePattern(im0));
        } else {
            if (isNextButton) {
                if (im1Index == images.length - 1) {
                    im1Index = 0;
                } else {
                    im1Index++;
                }
            } else {
                if (im1Index == 0) {
                    im1Index = images.length - 1;
                } else {
                    im1Index--;
                }
            }
            im1 = new Image(String.valueOf(images[im1Index].toURI()));
            playerTwoAvatar = im1;
            circleAvatar1.setFill(new ImagePattern(im1));
        }
    }

    private boolean checkIfNamesValid() {

        // Check if name is under 8 chars and set default name if no name is set
        boolean invalidName = false;
        playerOneName = playerOneNameField.getText();
        playerTwoName = playerTwoNameField.getText();
        if (playerOneName.isEmpty()) {
            playerOneName = "Player1";
        } else if (playerOneName.length() > 8) {
            invalidName = true;
            playerOneNameField.setStyle("-fx-border-color: red ; -fx-border-width: 3px ;");

        } else {
            playerOneNameField.setStyle("-fx-border-width: 0px ;");
        }

        if (playerTwoName.isEmpty()) {
            playerTwoName = "Player2";
        } else if (playerTwoName.length() > 8) {
            invalidName = true;
            playerTwoNameField.setStyle("-fx-border-color: red ; -fx-border-width: 3px ;");

        } else {
            playerTwoNameField.setStyle("-fx-border-width: 0px ;");
        }
        return invalidName;

    }

}