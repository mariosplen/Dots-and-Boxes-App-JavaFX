package com.github.mariosplen.dotsandboxes.models;


import java.util.Random;

public class Game {

    private final Board board;
    private final Player player0;
    private final Player player1;
    private boolean playerZeroTurn;

    public Game(int size, Player player0, Player player1) {
        this.board = new Board(this, size);
        this.player0 = player0;
        this.player1 = player1;

        this.player0.setGame(this);
        this.player1.setGame(this);
        this.playerZeroTurn = new Random().nextBoolean();
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
