package com.github.mariosplen.dotsandboxes;

import com.github.mariosplen.dotsandboxes.models.Board;
import com.github.mariosplen.dotsandboxes.models.Player;
import com.github.mariosplen.dotsandboxes.views.BoardPane;

import java.util.Random;

public class Game {


    public static final int DISTANCE = 150;
    public static final int RADIUS = 12;
    public static final int LINE_WIDTH = 8;
    public static final int LINE_DISTANCE = DISTANCE - 2 * RADIUS;
    private static boolean playerZeroTurn = new Random().nextBoolean();
    private static Player player0, player1;
    private final BoardPane boardPane;

    Game(int size, Player player0, Player player1) {
        Game.player0 = player0;
        Game.player1 = player1;


        Board board = new Board(size);
        this.boardPane = new BoardPane(board);
    }


    public static Player getCurrentPlayer() {
        return playerZeroTurn ? player0 : player1;
    }

    public static void changeCurrentPlayerTurn() {
        playerZeroTurn = !playerZeroTurn;
    }

    public BoardPane getBoardPane() {
        return boardPane;
    }


}
