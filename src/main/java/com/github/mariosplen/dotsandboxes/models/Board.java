package com.github.mariosplen.dotsandboxes.models;

import com.github.mariosplen.dotsandboxes.Game;

import java.util.LinkedHashSet;

public class Board {

    private final int size;
    private final int[][] squares;
    LinkedHashSet<Move> possibleMoves;

    public Board(int size) {
        this.size = size;
        squares = new int[this.size - 1][this.size - 1];
        possibleMoves = new LinkedHashSet<>();

        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    public boolean possibleMoveContains(Move move) {
        return possibleMoves.contains(move);
    }

    public void makeMove(Move move) {
        if (possibleMoveContains(move)) {
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
            if (pointsDone != 0) {
                Game.getCurrentPlayer().setPoints(Game.getCurrentPlayer().getPoints() + pointsDone);

            } else {
                Game.changeCurrentPlayerTurn();
            }
        }
    }

    private void initializeBoard() {
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

}
