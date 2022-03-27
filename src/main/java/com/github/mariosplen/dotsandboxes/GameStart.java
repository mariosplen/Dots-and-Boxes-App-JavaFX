package com.github.mariosplen.dotsandboxes;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GameStart {

//    number of dots = size^2
//    number of Hedges = size^2 - size
//    number of Vedges = size^2 - size
//    number of boxes = (size - 1)^2

    private final Set pressedLines;
    private final int size;

    private final GridPane root;
    Game game;


    public GameStart(int rows, List<String> players) {
        this.size = rows;
        root = new GridPane();
        pressedLines = new HashSet();
        makeBoard();
        game = new Game(rows, rows , players);


    }

    private void makeBoard() {
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(false);

        for (int col = 0; col < 2 * size - 1; col++) {

            root.getColumnConstraints()
                    .add(new ColumnConstraints(-1, -1, -1, Priority.ALWAYS, HPos.CENTER, false));
            for (int row = 0; row < 2 * size - 1; row++) {
                if (col == 0) {
                    root.getRowConstraints()
                            .add(new RowConstraints(-1, -1, -1, Priority.ALWAYS, VPos.CENTER, false));
                }

                if (col % 2 == 0) {
                    if (row % 2 == 0) {

                        Ellipse c = new Ellipse();
                        c.radiusXProperty().bind(root.widthProperty().divide(2).divide(2 * size - 1));
                        c.radiusYProperty().bind(root.heightProperty().divide(2).divide(2 * size - 1));

                        root.add(c, col, row);

                    } else {
                        Line l = new Line();

                        l.getStrokeDashArray().addAll(2d, 21d);
                        l.setStrokeLineCap(StrokeLineCap.ROUND);
                        l.endYProperty().bind(root.heightProperty().divide(2 * size - 1).divide(1.5));
                        l.setStrokeWidth(15);

                        l.setStroke(Color.WHITE);

                        l.setOnMouseEntered(event -> {
                            if (!pressedLines.contains(l)) {
                                l.setStroke(Color.RED);
                            }
                        });

                        l.setOnMouseExited(event -> {
                            if (!pressedLines.contains(l)) {
                                l.setStroke(Color.WHITE);
                            }
                        });

                        l.setOnMouseClicked(event -> {
                            if (!pressedLines.contains(l)) {
                                l.getStrokeDashArray().clear();
                                l.setStroke(Color.RED);
                                pressedLines.add(l);
                                Dot d0 = new Dot((GridPane.getRowIndex(l) - 1) * 0.5 ,GridPane.getColumnIndex(l) * 0.5 );
                                Dot d1 = new Dot((GridPane.getRowIndex(l) + 1) * 0.5,GridPane.getColumnIndex(l) * 0.5);
                                game.join(d0,d1,game.nextPlayerName());
                            }

                        });

                        root.add(l, col, row);
                    }

                } else {
                    if (row % 2 == 0) {
                        Line l = new Line();

                        l.getStrokeDashArray().addAll(2d, 21d);
                        l.setStrokeLineCap(StrokeLineCap.ROUND);
                        l.endXProperty().bind(root.widthProperty().divide(2 * size - 1).divide(1.5));
                        l.setStrokeWidth(15);
                        l.setStroke(Color.WHITE);

                        l.setOnMouseEntered(event -> {
                            if (!pressedLines.contains(l)) {
                                l.setStroke(Color.RED);
                            }
                        });

                        l.setOnMouseExited(event -> {
                            if (!pressedLines.contains(l)) {
                                l.setStroke(Color.WHITE);
                            }
                        });

                        l.setOnMouseClicked(event -> {

                            if (!pressedLines.contains(l)) {
                                l.getStrokeDashArray().clear();
                                l.setStroke(Color.RED);
                                System.out.println("" + GridPane.getRowIndex(l) + "" + GridPane.getColumnIndex(l));
                                pressedLines.add(l);



                                Dot d0 = new Dot((GridPane.getRowIndex(l)) * 0.5 ,(GridPane.getColumnIndex(l) - 1) * 0.5 );
                                Dot d1 = new Dot((GridPane.getRowIndex(l)) * 0.5,(GridPane.getColumnIndex(l) + 1) * 0.5);
                                game.join(d0,d1,game.nextPlayerName());



                            }
                        });

                        root.add(l, col, row);
                    } else {
                        Label l = new Label("");

                        l.centerShapeProperty();

                        root.add(l, col, row);

                    }
                }
            }
        }
    }



    public GridPane getBoard() {
        return root;
    }
}
