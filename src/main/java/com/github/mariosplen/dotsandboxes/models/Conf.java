package com.github.mariosplen.dotsandboxes.models;

public record Conf(int size, Player player0, Player player1, boolean playerZeroPlaysFirst) {

    public int getSize() {
        return size;
    }

    public Player getPlayer0() {
        return player0;
    }

    public Player getPlayer1() {
        return player1;
    }

    public boolean PlayerZeroPlaysFirst() {
        return playerZeroPlaysFirst;
    }

}
