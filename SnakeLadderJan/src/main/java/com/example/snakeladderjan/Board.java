package com.example.snakeladderjan;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pair<Integer,Integer>> positionCoordinates;

    public Board(){
       populatePositionCoordinates();
    }


    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<Integer,Integer>(0,0));   //dummy values
        int x=1,y=10,xPos,yPos;
        for(int i=0;i<snakeLadder.height;i++){
            x=1;
            for(int j=0;j<snakeLadder.width;j++){
                if(y%2==0){
                    xPos = x*snakeLadder.tileSize-snakeLadder.tileSize/2;
                }
                else {
                    xPos = snakeLadder.width * snakeLadder.tileSize -(x*snakeLadder.tileSize-snakeLadder.tileSize/2);
                }
                yPos = y*snakeLadder.tileSize-snakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<Integer, Integer>(xPos,yPos));
                x++;
            }
            y--;
        }
    }

    public int getXCoordinate(int position){
        return positionCoordinates.get(position).getKey();
    }

    public int getYCoordinate(int position){
        return positionCoordinates.get(position).getValue();
    }


//    public static void main(String[] args) {
//        Board board= new Board();
//        board.populatePositionCoordinates();
//        for(int i=0;i<board.positionCoordinates.size();i++){
//            System.out.println(i+" # x:"+ board.positionCoordinates.get(i).getKey()+" y:"+ board.positionCoordinates.get(i).getValue());
//        }
//    }
}
