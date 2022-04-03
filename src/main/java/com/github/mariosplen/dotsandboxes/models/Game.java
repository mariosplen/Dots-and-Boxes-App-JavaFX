package com.github.mariosplen.dotsandboxes.models;

import java.util.Random;

public class Game {

    private final Board board;
    private final Player player0;
    private final Player player1;
    private boolean playerZeroTurn;

    public Game(int size) {
        this.board = new Board(this, size);

        Random random = new Random();
        this.playerZeroTurn = random.nextBoolean();

        this.player0 = new Player(this);
        this.player1 = new Player(this);

    }


    public Player getPlayer0() {
        return player0;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return playerZeroTurn ? player0 : player1;
    }


    void changeCurrentPlayerTurn() {
        playerZeroTurn = !playerZeroTurn;
    }


}
