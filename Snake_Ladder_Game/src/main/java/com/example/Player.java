package com.example;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private String name;
    private int position;
    private Circle coin;

    private static Board gameBoard=new Board();

    public Player(String name, Color coinColor, int coinsize){
        this.name=name;
        position=0;
        coin=new Circle(coinsize);
        coin.setFill(coinColor);
        movePlayer(1);
    }

    public void setStart(){
        position=0;
        movePlayer(1);
    }
    
    public void movePlayer(int DiceValue){
        if(position + DiceValue <= 100){
            position += DiceValue;
            TranslateTransition secondMove=null, firstMove= TranslatePlayer();
            
            int newPos= gameBoard.getSnakeLadder(position);
            if(newPos!=position){
                position= newPos;
                secondMove=TranslatePlayer();
            }
            if(secondMove==null){
                firstMove.play();
            }
            else{
                SequentialTransition seq= new SequentialTransition(firstMove,new PauseTransition(Duration.millis(100)),secondMove);
                seq.play();
            }
        }
    }

    public boolean checkWinner(){
        if(position==100)
            return true;
        return false;
    }
    
    private TranslateTransition TranslatePlayer(){
        TranslateTransition move= new TranslateTransition(Duration.millis(1000),coin);
        move.setToX(gameBoard.getXCoordinate(position));
        move.setToY(gameBoard.getYCoordinate(position));
        move.setAutoReverse(false);
        return move;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Circle getCoin() {
        return coin;
    }

    public void setCoin(Circle coin) {
        this.coin = coin;
    }
}