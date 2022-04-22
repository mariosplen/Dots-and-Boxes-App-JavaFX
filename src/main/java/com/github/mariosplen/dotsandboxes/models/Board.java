package com.github.mariosplen.dotsandboxes.models;

import com.github.mariosplen.dotsandboxes.App;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Board {

    private final int size;
    private final int[][] squares;
    private final LinkedHashSet<Move> possibleMoves;
    private final ArrayList<int[]> need2BeDrawn;
    private final Player player0, player1;
    private final Conf gameConf;
    private boolean playerZeroTurn;


    public Board(Conf gameConf) {
        this.gameConf = gameConf;
        this.size = gameConf.getSize();
        this.player0 = gameConf.getPlayer0();
        this.player1 = gameConf.getPlayer1();
        this.playerZeroTurn = gameConf.PlayerZeroPlaysFirst();

        squares = new int[this.size - 1][this.size - 1];
        possibleMoves = new LinkedHashSet<>();
        need2BeDrawn = new ArrayList<>();


        initializeBoard();
    }

    public ArrayList<int[]> getNeed2BeDrawn() {
        return need2BeDrawn;
    }

    public int getSize() {
        return size;
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

    public boolean isPossibleMove(Move move) {
        return possibleMoves.contains(move);
    }

    public void markDrawn() {
        need2BeDrawn.clear();
    }

    public void makeMove(Move move) {
        if (isPossibleMove(move)) {

            possibleMoves.remove(move);
            int pointsDone = 0;
            int x = move.getRowFrom();
            int y = move.getColFrom();

            if (move.isHorizontal()) {
                if (x == 0) {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y});
                    }
                } else if (x == size - 1) {
                    squares[x - 1][y]--;
                    if (squares[x - 1][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x - 1, y});
                    }
                } else {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y});
                    }
                    squares[x - 1][y]--;
                    if (squares[x - 1][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x - 1, y});
                    }
                }
            } else {
                if (y == 0) {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y});
                    }
                } else if (y == size - 1) {
                    squares[x][y - 1]--;
                    if (squares[x][y - 1] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y - 1});
                    }
                } else {
                    squares[x][y]--;
                    if (squares[x][y] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y});
                    }
                    squares[x][y - 1]--;
                    if (squares[x][y - 1] == 0) {
                        pointsDone++;
                        need2BeDrawn.add(new int[]{x, y - 1});
                    }
                }
            }

            if (pointsDone != 0) {
                getCurrentPlayer().setPoints(getCurrentPlayer().getPoints() + pointsDone);


            } else {
                changeCurrentPlayerTurn();
            }
            App.refreshScreen(gameConf, this);
        }
    }

    public Player getCurrentPlayer() {
        return playerZeroTurn ? player0 : player1;
    }

    private void changeCurrentPlayerTurn() {
        playerZeroTurn = !playerZeroTurn;
    }
}
