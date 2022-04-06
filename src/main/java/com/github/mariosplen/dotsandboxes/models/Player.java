package com.github.mariosplen.dotsandboxes.models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player {

    private final String name;
    private final Color color;
    private final Image image;
    private int points;
    private Game game;
    public Player(String name, Color color, Image image) {
        this.name = name;
        this.color = color;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void makeMove(Move move) {
        game.getBoard().makeMove(new Move(move, this));
    }


}
