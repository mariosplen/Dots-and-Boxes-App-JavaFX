package com.github.mariosplen.dotsandboxes.models;

import java.util.LinkedHashSet;

public class Board {

    private final int size;
    private final Game game;
    private final int[][] squares;
    private final LinkedHashSet<Move> possibleMoves;

    Board(Game game, int size) {
        this.game = game;
        this.size = size;
        squares = new int[size - 1][size - 1];
        this.possibleMoves = new LinkedHashSet<>();
        initializeGameBoard();
    }

    public int[][] getSquares() {
        return squares;
    }

    public boolean isNotOver() {
        return !possibleMoves.isEmpty();
    }

    public int getSize() {
        return size;
    }

    private void initializeGameBoard() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                squares[i][j] = 4;
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == size - 1 && col != size - 1) {
                    possibleMoves.add(new Move(row, col, row, col + 1));
                } else if (col == size - 1 && row != size - 1) {
                    possibleMoves.add(new Move(row, col, row + 1, col));
                } else if (row != size - 1 && col != size - 1) {
                    possibleMoves.add(new Move(row, col, row, col + 1));
                    possibleMoves.add(new Move(row, col, row + 1, col));
                }
            }
        }

    }


    void makeMove(Move move) {
        if (possibleMoves.contains(move)) {
            possibleMoves.remove(move);

            int pointsDone = 0;
            int x = move.getRowFrom();
            int y = move.getColFrom();

            if (move.isHorizontal()) {
                if (x == 0) {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                    }
                } else if (x == size - 1) {
                    squares[x - 1][y]--;
                    if (squares[x - 1][y] == 0) {
                        pointsDone++;
                    }
                } else {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                    }
                    squares[x - 1][y]--;
                    if (squares[x - 1][y] == 0) {
                        pointsDone++;
                    }
                }
            } else {
                if (y == 0) {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                    }
                } else if (y == size - 1) {
                    squares[x][y - 1]--;
                    if (squares[x][y - 1] == 0) {
                        pointsDone++;
                    }
                } else {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                    }
                    squares[x][y - 1]--;
                    if (squares[x][y - 1] == 0) {
                        pointsDone++;
                    }
                }
            }
            if (pointsDone == 0) {
                game.changeCurrentPlayerTurn();
            } else {
                move.setPointsDone(pointsDone);
                move.getPlayer().setPoints(move.getPlayer().getPoints() + pointsDone);
            }
        }
    }


}
