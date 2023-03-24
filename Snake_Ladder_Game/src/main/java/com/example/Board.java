package com.example;

import java.util.ArrayList;

import javafx.util.Pair;

public class Board {
    private ArrayList<Pair<Integer, Integer>> positionCoordinates;

    private ArrayList<Integer> SnakeLadder;
    
    public Board(){
        populatePositionCoordinates();
        setSnakeLadder();
    }

    private void populatePositionCoordinates(){
        positionCoordinates= new ArrayList<>();
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < Main.Height; i++) {
            for (int j = 0; j < Main.Width; j++) {
                //X-Axis
                int xCord=0;
                if(i%2==0){
                    xCord=j*Main.Tilesize + Main.Tilesize/2;
                }
                else{
                    xCord=Main.Height * Main.Tilesize - j * Main.Tilesize - Main.Tilesize / 2;
                }
                //Y-Axis
                int yCord=Main.Height * Main.Tilesize - i * Main.Tilesize - Main.Tilesize / 2;

                positionCoordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }

    private void setSnakeLadder(){
        SnakeLadder= new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            SnakeLadder.add(i);
        }
        SnakeLadder.set(4,25);
        SnakeLadder.set(13,46);
        SnakeLadder.set(27,5);
        SnakeLadder.set(33,49);
        SnakeLadder.set(40,3);
        SnakeLadder.set(42,63);
        SnakeLadder.set(43,18);
        SnakeLadder.set(50,69);
        SnakeLadder.set(54,31);
        SnakeLadder.set(62,81);
        SnakeLadder.set(66,45);
        SnakeLadder.set(74,92);
        SnakeLadder.set(76,58);
        SnakeLadder.set(89,53);
        SnakeLadder.set(99,41);
    }
    
    public int getSnakeLadder(int position){
        return SnakeLadder.get(position);
    }
    
    public int getXCoordinate(int position){
        if(position>0 && position<=100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    public int getYCoordinate(int position){
        if(position>0 && position<=100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }    
}
