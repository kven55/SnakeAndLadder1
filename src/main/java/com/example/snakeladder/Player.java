package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;
    static Board gameBoard = new Board();

    public Player(int tilesize , Color coinColor , String name)
    {
        coin = new Circle(tilesize / 2);
        coin.setFill(coinColor);
        coin.setStroke(Color.BLACK);
        coin.setStrokeWidth(2);
        this.name = name;
        currentPosition = 0;
        movePlayer(1);
    }

    public SequentialTransition movePlayer(int diceValue)
    {
        SequentialTransition SQ = new SequentialTransition();

        if((currentPosition + diceValue) <= 100)
        {
            currentPosition += diceValue;

            TranslateTransition firstMove = translateAnimation(diceValue) , secondMove = null;

//            int x = gameBoard.getXCoordinates(currentPosition);
//            int y = gameBoard.getYCoordinates(currentPosition);
//            coin.setTranslateX(x);
//            coin.setTranslateY(y);

            int newPosition = gameBoard.newPosition(currentPosition);
            if(newPosition != 0 && newPosition != -1) //if no ladder/snake is there then that value will be 0 at snake ladder position arr
            {
                currentPosition = newPosition;
                secondMove = translateAnimation(diceValue);
            }

            if(currentPosition == 1) //ADDING THIS FOR INITIAL POSITIONS FOR PLAYERS(ADDITIONALLY ADDED BCZ OF CHANGES IN SNAKE&LADDER CODE FOR ROTATION OF DICE)
                firstMove.play();

            else if(secondMove == null)
            {
//              firstMove.play();
                SQ.getChildren().add(firstMove);
            }
            else
            {
//                SequentialTransition SQ = new SequentialTransition(firstMove ,
//                                                            new PauseTransition(Duration.seconds(0.5)), secondMove);
//                SQ.play();
                SQ.getChildren().addAll(firstMove , new PauseTransition(Duration.seconds(0.5)) , secondMove);
            }
            return SQ;
        }
        return null;
    }
    //FOR BETTER AESTHETICS WHILE MOVING A PLAYER
    private TranslateTransition translateAnimation(int diceValue)
    {
        TranslateTransition animateCoin = new TranslateTransition(Duration.seconds(0.25 * diceValue) , coin);
        animateCoin.setToX(gameBoard.getXCoordinates(currentPosition));
        animateCoin.setToY(gameBoard.getYCoordinates(currentPosition));
        animateCoin.setAutoReverse(false);
        return animateCoin;
    }

    public boolean isWinner()
    {
        if(currentPosition == 100)
            return true;

        return false;
    }

    public void bringToStart()
    {
        currentPosition = 0;
        movePlayer(1);
    }


    //using getters for getting components for players during the game
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
