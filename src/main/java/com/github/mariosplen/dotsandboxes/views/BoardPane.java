package com.github.mariosplen.dotsandboxes.views;

import com.github.mariosplen.dotsandboxes.models.Board;
import com.github.mariosplen.dotsandboxes.models.Move;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class BoardPane extends GridPane {

    public final int DISTANCE = 150;
    public final int RADIUS = 12;
    public final int LINE_WIDTH = 8;
    public final int LINE_DISTANCE = DISTANCE - 2 * RADIUS;
    private final Board board;
    private final ScaleTransition st0, st1;


    public BoardPane(Board board) {
        this.board = board;
        int size = board.getSize();

        st0 = setScaleTransition();
        st1 = setScaleTransition();

        initializeBoardPane(size);
    }

    private ScaleTransition setScaleTransition() {
        ScaleTransition st = new ScaleTransition(Duration.millis(700), null);
        st.setByX(0.35);
        st.setByY(0.35);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);

        return st;
    }

    void initializeBoardPane(int size) {
        setAlignment(Pos.CENTER);

        for (int row = 0; row < 2 * size - 1; row++) {
            for (int col = 0; col < 2 * size - 1; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        // DOTS
                        Circle c = new Circle(RADIUS, Color.BLACK);


                        add(c, row, col);
                        setHalignment(c, HPos.CENTER);
                        setValignment(c, VPos.CENTER);

                    } else {
                        // VERTICAL LINES
                        Line line = createLine(row, col);


                        add(line, row, col);
                        setHalignment(line, HPos.CENTER);

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
        line.setStroke(Color.WHITE);
        line.getStrokeDashArray().addAll(10d, 12d);
        line.setStrokeDashOffset(8d);

        // Conversion from GUI Grid to Board Grid
        int fromRow = r / 2;
        int fromCol = c / 2;
        int toRow = r - fromRow;
        int toCol = c - fromCol;

        line.setOnMouseEntered(event -> {
            Move move = new Move(fromRow, fromCol, toRow, toCol);
            if (board.isPossibleMove(move)) {
                line.setStroke(Color.BLACK);
                Circle cr0, cr1;

                if (isHorizontal) {
                    cr0 = getCircleByRowColumnIndex(c, r + 1);

                    cr1 = getCircleByRowColumnIndex(c, r - 1);

                } else {
                    cr0 = getCircleByRowColumnIndex(c - 1, r);

                    cr1 = getCircleByRowColumnIndex(c + 1, r);


                }


                st1.setNode(cr1);
                st0.setNode(cr0);
                cr0.toFront();
                cr1.toFront();
                st0.play();
                st1.play();


            }

        });

        line.setOnMouseExited(event -> {
            Move move = new Move(fromRow, fromCol, toRow, toCol);
            if (board.isPossibleMove(move)) {
                line.setStroke(Color.WHITE);
            }
            st1.jumpTo(Duration.ZERO);
            st1.stop();

            st0.jumpTo(Duration.ZERO);
            st0.stop();
        });

        line.setOnMouseClicked(event -> {
            Move move = new Move(fromRow, fromCol, toRow, toCol);
            if (board.isPossibleMove(move)) {

                line.setStroke(board.getCurrentPlayer().getColor());
                board.makeMove(move);
                line.getStrokeDashArray().clear();
                for (int[] box : board.getNeed2BeDrawn()) {
                    showLetter(box[0], box[1]);
                }
                board.markDrawn();


            }
        });

        return line;
    }


    private void showLetter(int r, int c) {


        Label l = new Label();
        l.setTextFill(board.getCurrentPlayer().getColor());
        l.setText(board.getCurrentPlayer().getName());
        setHalignment(l, HPos.CENTER);
        setValignment(l, VPos.CENTER);
        ScaleTransition st = new ScaleTransition(Duration.millis(500), l);
        st.setByX(1.5f);
        st.setByY(1.5f);


        st.play();

        add(l, 2 * (r) + 1, 2 * (c) + 1);

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
}
