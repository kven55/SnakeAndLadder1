package com.example.snakeladder;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Dice {
    private static int randomDiceValue = 0;

    public int generateDiceValue()
    {
        randomDiceValue = (int)(Math.random() * 6) + 1;
        return randomDiceValue;
    }
    public RotateTransition rollDice()
    {
        RotateTransition rt = new RotateTransition();
        rt.setAutoReverse(false);
        rt.setByAngle(360.0);
        rt.setDuration(Duration.millis(200));
        rt.setNode(SnakeLadder.diceImageView);
        rt.setOnFinished(event -> {
            SnakeLadder.diceImageView.setImage(new Image("C:\\Users\\DELL\\IdeaProjects\\SnakeLadder\\src\\Dice Pics\\Dice" + randomDiceValue + ".png"));
        });
        return rt;
    }

}
