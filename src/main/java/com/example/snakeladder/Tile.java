package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int tilesize)
    {
        setWidth(tilesize);
        setHeight(tilesize);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
    }
}
