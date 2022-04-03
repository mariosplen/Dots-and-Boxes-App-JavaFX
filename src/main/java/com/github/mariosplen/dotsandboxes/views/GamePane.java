package com.github.mariosplen.dotsandboxes.views;


import com.github.mariosplen.dotsandboxes.App;
import com.github.mariosplen.dotsandboxes.models.Move;
import com.github.mariosplen.dotsandboxes.models.Player;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;


public class GamePane extends GridPane {
    static final int DISTANCE = 100;
    private static final int RADIUS = 6;

    private static final int LINE_WIDTH = 4;
    private static final int LINE_DISTANCE = DISTANCE - 2 * RADIUS;
    private static final int size = App.getGame().getBoard().getSize();

    private final Set<Line> clickedLines = new HashSet<>();


    public GamePane() {
        initializeBoard();
    }

    private void initializeBoard() {
        setAlignment(Pos.CENTER);
        setGridLinesVisible(true);
        for (int row = 0; row < 2 * size - 1; row++) {
            for (int col = 0; col < 2 * size - 1; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        // DOTS
                        Circle c = new Circle(RADIUS, Color.BLACK);


                        add(c, row, col);
                        GridPane.setHalignment(c, HPos.CENTER);
                        GridPane.setValignment(c, VPos.CENTER);
                    } else {
                        // VERTICAL LINES
                        Line line = createLine(row, col);


                        add(line, row, col);
                        GridPane.setHalignment(line, HPos.CENTER);

                    }
                } else {
                    if (col % 2 == 0) {
                        Line line = createLine(row, col);


                        add(line, row, col);
                    } else {
                        // BOXES
                        Rectangle box = new Rectangle();
                        box.setHeight(LINE_DISTANCE);
                        box.setHeight(LINE_DISTANCE);


                        add(box, row, col);
                    }
                }

            }
        }

    }


    private Line createLine(int r, int c) {
        boolean isHorizontal = c % 2 == 0;

        Line line = new Line();

        if (isHorizontal) {
            line.setEndX(LINE_DISTANCE);
        } else {
            line.setEndY(LINE_DISTANCE);
        }
        line.setStrokeWidth(LINE_WIDTH);
        line.setStroke(Color.WHITESMOKE);
        line.getStrokeDashArray().addAll(10d, 12d);
        line.setStrokeDashOffset(5d);

        // Conversion from GUI Grid to Game Grid
        int fromRow = r / 2;
        int fromCol = c / 2;
        int toRow = r - fromRow;
        int toCol = c - fromCol;

        line.setOnMouseEntered(event -> {
            if (!clickedLines.contains(line)) {
                line.setStroke(Color.BLACK);
            }
        });

        line.setOnMouseExited(event -> {
            if (!clickedLines.contains(line)) {
                line.setStroke(Color.WHITESMOKE);
            }
        });

        line.setOnMouseClicked(event -> {
            if (!clickedLines.contains(line)) {
                Move move = new Move(fromRow, fromCol, toRow, toCol);
                Player currentPlayer = App.getGame().getCurrentPlayer();
                currentPlayer.makeMove(move);
                line.getStrokeDashArray().clear();
                line.setStroke(Color.RED);
                clickedLines.add(line);
            }


            System.out.println("CLICKED");

        });

        return line;
    }


}