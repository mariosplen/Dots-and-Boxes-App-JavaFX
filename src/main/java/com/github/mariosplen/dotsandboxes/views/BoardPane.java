package com.github.mariosplen.dotsandboxes.views;


import com.github.mariosplen.dotsandboxes.models.Game;
import com.github.mariosplen.dotsandboxes.models.Move;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;


public class BoardPane extends GridPane {
    private final Game game;
    private final int size;
    private final int DISTANCE = 150;
    private final int RADIUS = 12;
    private final int LINE_WIDTH = 8;
    private final int LINE_DISTANCE = DISTANCE - 2 * RADIUS;
    private final Set<Line> clickedLines = new HashSet<>();
    ScaleTransition st1;
    ScaleTransition st0;


    public BoardPane(Game game) {
        this.game = game;
        size = game.getBoard().getSize();
        System.out.println(size);
        initializeBoard();
    }

    private void initializeBoard() {
        setAlignment(Pos.CENTER);
        setGridLinesVisible(false);
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
        line.setStrokeDashOffset(8d);

        // Conversion from GUI Grid to Game Grid
        int fromRow = r / 2;
        int fromCol = c / 2;
        int toRow = r - fromRow;
        int toCol = c - fromCol;

        line.setOnMouseEntered(event -> {
            if (!clickedLines.contains(line)) {
                line.setStroke(Color.BLACK);
                if (isHorizontal) {
                    Circle cr0 = getCircleByRowColumnIndex(c, r + 1);
                    cr0.toFront();
                    st0 = new ScaleTransition(Duration.millis(700), cr0);

                    st0.setByX(0.35);
                    st0.setByY(0.35);
                    st0.setCycleCount(Animation.INDEFINITE);
                    st0.setAutoReverse(true);

                    st0.play();


                    Circle cr1 = getCircleByRowColumnIndex(c, r - 1);
                    cr1.toFront();
                    st1 = new ScaleTransition(Duration.millis(700), cr1);

                    st1.setByX(0.35);
                    st1.setByY(0.35);
                    st1.setCycleCount(Animation.INDEFINITE);
                    st1.setAutoReverse(true);

                    st1.play();


                } else {
                    Circle cr0 = getCircleByRowColumnIndex(c + 1, r);
                    cr0.toFront();
                    st0 = new ScaleTransition(Duration.millis(700), cr0);

                    st0.setByX(0.35);
                    st0.setByY(0.35);
                    st0.setCycleCount(Animation.INDEFINITE);
                    st0.setAutoReverse(true);

                    st0.play();


                    Circle cr1 = getCircleByRowColumnIndex(c - 1, r);
                    cr1.toFront();
                    st1 = new ScaleTransition(Duration.millis(700), cr1);

                    st1.setByX(0.35);
                    st1.setByY(0.35);
                    st1.setCycleCount(Animation.INDEFINITE);
                    st1.setAutoReverse(true);

                    st1.play();
                }


            }

        });

        line.setOnMouseExited(event -> {
            if (!clickedLines.contains(line)) {
                line.setStroke(Color.WHITESMOKE);


            }
            st1.jumpTo(Duration.ZERO);
            st1.stop();

            st0.jumpTo(Duration.ZERO);
            st0.stop();
        });

        line.setOnMouseClicked(event -> {

            if (!clickedLines.contains(line)) {
                line.setStroke(game.getCurrentPlayer().getColor());
                Move move = new Move(fromRow, fromCol, toRow, toCol);
                game.getCurrentPlayer().makeMove(move);
                line.getStrokeDashArray().clear();
                clickedLines.add(line);

                int[][] sq = game.getBoard().getSquares();
                for (int j = 0; j < sq[0].length; j++) {
                    for (int i = 0; i < sq.length; i++) {

                        if (sq[i][j] == 0) {
                            add(new Label(game.getCurrentPlayer().getName() + " wins box"), 2 * (i) + 1, 2 * (j) + 1);
                        }
                    }
                }
                if (!game.getBoard().isNotOver()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("GAME OVER");
                    String winner;
                    if (game.getPlayer0().getPoints() > game.getPlayer1().getPoints()) {
                        winner = game.getPlayer0().getName();
                    } else if (game.getPlayer0().getPoints() < game.getPlayer1().getPoints()) {
                        winner = game.getPlayer1().getName();

                    } else {
                        winner = "Tie";
                    }
                    alert.setContentText("WINNER IS " + winner);
                    alert.showAndWait();
                }
            }
        });

        return line;
    }

    private Circle getCircleByRowColumnIndex(final int row, final int column) {
        Circle result = null;

        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == column) {
                result = (Circle) node;
                break;
            }
        }

        return result;
    }

    public VBox setText() {
        VBox vBox = new VBox();
        Circle circle = new Circle();
        circle.setRadius(RADIUS);
        circle.setFill(new ImagePattern(game.getPlayer0().getImage()));
        vBox.getChildren().add(circle);
        return vBox;
    }


}