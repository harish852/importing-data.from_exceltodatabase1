package com.example.snakeladderjan;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {    //rec is a shape class
    public Tile (int size){
        setWidth(size);
        setHeight(size);
        setFill(Color.RED);
        setStroke(Color.BLACK);  //border
    }
}
