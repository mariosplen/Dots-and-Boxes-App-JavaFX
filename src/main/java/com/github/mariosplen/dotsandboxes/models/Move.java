package com.github.mariosplen.dotsandboxes.models;


import java.util.Objects;

public class Move {

    private final int rowFrom;
    private final int colFrom;
    private final int rowTo;
    private final int colTo;
    private int pointsDone;
    private Player player;

    Move(Move move, Player player) {
        this(move.rowFrom, move.colFrom, move.rowTo, move.colTo);
        this.player = player;
    }

    public Move(int rowFrom, int colFrom, int rowTo, int colTo) {
        this.rowFrom = rowFrom;
        this.colFrom = colFrom;
        this.rowTo = rowTo;
        this.colTo = colTo;
        this.pointsDone = 0;
        this.player = null;
    }


    public int getRowFrom() {
        return rowFrom;
    }

    public int getColFrom() {
        return colFrom;
    }

    public Player getPlayer() {
        return player;
    }

    int getPointsDone() {
        return pointsDone;
    }

    void setPointsDone(int pointsDone) {
        this.pointsDone = pointsDone;
    }

    public boolean isHorizontal() {
        return rowFrom == rowTo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object == null || getClass() != object.getClass())
            return false;

        Move move = (Move) object;
        return rowFrom == move.rowFrom && colFrom == move.colFrom && rowTo == move.rowTo && colTo == move.colTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowFrom, colFrom, rowTo, colTo);
    }

}