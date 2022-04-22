package com.github.mariosplen.dotsandboxes.views;

import com.github.mariosplen.dotsandboxes.models.Board;
import com.github.mariosplen.dotsandboxes.models.Conf;
import com.github.mariosplen.dotsandboxes.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class ScorePane extends VBox {


    public ScorePane(Conf gameConf, Board board) {

        Rectangle separator = new Rectangle();
        separator.setWidth(30);
        separator.setHeight(6);

        Label l1 = new Label(board.getCurrentPlayer().getName() + "'s");
        l1.setFont(new Font("Arial", 30));
        l1.setTextFill(board.getCurrentPlayer().getColor());

        Label l2 = new Label("Turn!");
        l2.setFont(new Font("Arial", 30));
        setAlignment(Pos.CENTER);

        HBox hbox = new HBox();

        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buildPlayerScoreNodes(gameConf.getPlayer0(), false));

        hbox.getChildren().add(separator);

        hbox.getChildren().addAll(buildPlayerScoreNodes(gameConf.getPlayer1(), true));

        getChildren().addAll(hbox, l1, l2);
    }

    ObservableList<Node> buildPlayerScoreNodes(Player player, boolean secondPlayer) {

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        Label name = new Label(player.getName());
        name.setFont(new Font("Arial", 20));

        Circle avatar = new Circle(250, 200, 75);
        avatar.setEffect(new DropShadow(+25d, 0d, +2d, player.getColor()));
        avatar.setFill(new ImagePattern(player.getImage()));
        vBox.getChildren().addAll(name, avatar);

        Label score = new Label(Integer.toString(player.getPoints()));
        score.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 90));
        score.setTextFill(player.getColor());

        ObservableList<Node> list = FXCollections.observableArrayList();
        list.addAll(vBox, score);

        if (secondPlayer) {
            FXCollections.reverse(list);
        }

        return list;

    }


}
