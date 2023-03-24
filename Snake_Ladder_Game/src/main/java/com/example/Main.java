package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    public static final int Tilesize=50,Width=10, Height=10, buttonline=Height*Tilesize+30, infoline=Height*Tilesize+10;
    
    int DiceValue; boolean firstPlayerTurn=true, gamestart=false;

    private Pane createContent() throws FileNotFoundException{
        Pane root= new Pane();
        root.setPrefSize(Width*Tilesize, Height*Tilesize+70);
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                Tile tile= new Tile(Tilesize);
                tile.setTranslateX(j*Tilesize);
                tile.setTranslateY(i*Tilesize);
                root.getChildren().addAll(tile);
            }
        }
        FileInputStream stream= new FileInputStream("E:\\Projects\\Snake_Ladder_Game\\src\\snake_ladder_image.jpg");
        Image image= new Image(stream);
        ImageView boarImage= new ImageView();
        boarImage.setFitHeight(Height*Tilesize);
        boarImage.setFitWidth(Width*Tilesize);
        boarImage.setImage(image);
       
        Button startButton=new Button("Start");
        startButton.setTranslateX(230);
        startButton.setTranslateY(buttonline);

        Button button1=new Button("Player 1");
        button1.setTranslateX(30);
        button1.setTranslateY(buttonline);

        Button button2=new Button("Player 2");
        button2.setTranslateX(410);
        button2.setTranslateY(buttonline);

        Label DiceLabel= new Label("Start The Game");
        DiceLabel.setTranslateX(210);
        DiceLabel.setTranslateY(infoline);

        Player PlayerFirst= new Player("Player 1",Color.RED, Tilesize/3);
        Player PlayerSecond= new Player("Player 2",Color.BLUE, Tilesize/3);
        
        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                if(gamestart){
                    if(firstPlayerTurn){
                        DiceValue=rollDice();
                        DiceLabel.setText("Dice Value : "+DiceValue);
                        PlayerFirst.movePlayer(DiceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(PlayerFirst.checkWinner()){
                            DiceLabel.setText("Winner is "+ PlayerFirst.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gamestart=false;
                        }
                    }
                }
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                if(gamestart){
                    if(!firstPlayerTurn){
                        DiceValue=rollDice();
                        DiceLabel.setText("Dice Value : "+DiceValue);
                        PlayerSecond.movePlayer(DiceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(PlayerSecond.checkWinner()){
                            DiceLabel.setText("Winner is "+ PlayerSecond.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gamestart=false;
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                gamestart=true;
                startButton.setDisable(true);
                PlayerFirst.setStart();
                PlayerSecond.setStart();
            }
        });
        root.getChildren().addAll(boarImage, startButton, button1, button2, DiceLabel, PlayerFirst.getCoin(), PlayerSecond.getCoin());

        return root;
    }

    private int rollDice(){
        return (int) (Math.random()*6+1);
    }
    
    public class Tile extends Rectangle{
        public Tile(int Tilesize){
           setWidth(Tilesize);
           setHeight(Tilesize);
           setFill(Color.AQUAMARINE);
           setStroke(Color.BLACK);
       }
   }
   
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}