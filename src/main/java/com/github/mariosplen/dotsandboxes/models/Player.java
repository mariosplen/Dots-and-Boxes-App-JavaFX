package com.github.mariosplen.dotsandboxes.models;

public class Player {


    Game game;
    int points;
    String color;

    Player(Game game, int points) {
        this.game = game;
        this.points = points;
    }

    Player(Game game) {
        this(game, 0);
    }

    public int getPoints() {
        return points;
    }

    void setPoints(int points) {
        this.points = points;
    }

    public void makeMove(Move move) {
        game.getBoard().makeMove(new Move(move, this));
    }


}