package com.github.mariosplen.dotsandboxes.models;


public record Move(int rowFrom, int colFrom, int rowTo, int colTo) {

    public int getRowFrom() {
        return rowFrom;
    }

    public int getColFrom() {
        return colFrom;
    }

    public boolean isHorizontal() {
        return rowFrom == rowTo;
    }


}