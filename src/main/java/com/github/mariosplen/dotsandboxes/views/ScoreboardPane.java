package com.github.mariosplen.dotsandboxes.views;

import com.github.mariosplen.dotsandboxes.models.Conf;
import com.github.mariosplen.dotsandboxes.models.Score;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreboardPane extends TableView<Score> {


    public ScoreboardPane(Conf gameConf) {
        TableColumn<Score, String> player0 = new TableColumn<>(gameConf.getPlayer0().getName());
        TableColumn<Score, String> player1 = new TableColumn<>(gameConf.getPlayer1().getName());
        TableColumn<Score, String> time = new TableColumn<>("Time");


        player0.setMinWidth(100);
        player0.setCellValueFactory(
                new PropertyValueFactory<>("score0")
        );

        player1.setMinWidth(100);
        player1.setCellValueFactory(
                new PropertyValueFactory<>("score1")
        );

        time.setMinWidth(200);
        time.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );

        getColumns().add(player0);
        getColumns().add(player1);
        getColumns().add(time);

    }


}
