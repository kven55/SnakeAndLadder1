package com.example.snakeladder;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static int tilesize = 50 , width = 10 , height = 10; //tilesize 50 implies its width and height to be 40
    public static int buttonLine = 570 , infoLine = 550;
    public static ImageView diceImageView;
    public static Dice dice = new Dice();
    Player playerOne , playerTwo;
    private boolean isGameStarted = false , playerOneTurn = false , playerTwoTurn = false;
    private Pane createGame()
    {
        Pane root = new Pane();
        root.setPrefSize(tilesize * width , tilesize * height + 120); //ADDITON OF 50 FOR EXTRA PLAYING BUTTONS BELOW TABLE

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tilesize);
                tile.setTranslateX(j * tilesize);
                tile.setTranslateY(i * tilesize);
                root.getChildren().add(tile);
            }
        }

        //ADDING AN IMAGE
        Image img = new Image("C:\\Users\\DELL\\IdeaProjects\\SnakeLadder\\src\\main\\SNL2.jpg");
        ImageView board = new ImageView(); //FOR DISPLAYING IMAGE
        board.setImage(img);
        board.setFitHeight(tilesize * height);
        board.setFitWidth((tilesize * width));

        //ADDING BUTTONS AND LABELS AT BOTTOM FOR PLAYERS
        Button playerOneButton = new Button("PLAYER 1");
        playerOneButton.setStyle("-fx-text-fill : White; -fx-background-color : maroon; -fx-border-color : black; -fx-border-width : 4 ; -fx-font-size : 1.25em ;");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setDisable(true);

        Button startButton = new Button("START");
        startButton.setStyle("-fx-text-fill : White; -fx-background-color : lawngreen; -fx-border-color : black; -fx-border-width : 4 ; -fx-font-size : 1.25em;");
        startButton.setTranslateX(220);
        startButton.setTranslateY(buttonLine);

        Button playerTwoButton = new Button("PLAYER 2");
        playerTwoButton.setStyle("-fx-text-fill : White; -fx-background-color : darkslategrey; -fx-border-color : black; -fx-border-width : 4 ; -fx-font-size : 1.25em ;");
        playerTwoButton.setTranslateX(405);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setDisable(true);

        Label playerOneLabel = new Label("");
        playerOneLabel.setStyle("-fx-text-fill : maroon; -fx-font-weight : bold; -fx-font-size : 1.15em;");
        playerOneLabel.setTranslateX(20);
        playerOneLabel.setTranslateY(infoLine);

        Label diceLabel = new Label("");
        diceLabel.setStyle("-fx-text-fill : black; -fx-font-weight : bold;");
        diceLabel.setTranslateX(220);
        diceLabel.setTranslateY(infoLine);

        diceImageView = new ImageView();
        diceImageView.setFitHeight(40);
        diceImageView.setFitWidth(40);
        diceImageView.setTranslateX(235);
        diceImageView.setTranslateY(infoLine - 40);

        Label playerTwoLabel = new Label("");
        playerTwoLabel.setStyle("-fx-text-fill : darkslategrey; -fx-font-weight : bold; -fx-font-size : 1.15em;");
        playerTwoLabel.setTranslateX(405);
        playerTwoLabel.setTranslateY(infoLine);

        //DECLARING PLAYERS
        playerOne = new Player(tilesize - 3, Color.MAROON , "Amit");
        playerTwo = new Player(tilesize - 9 , Color.DARKSLATEGRAY , "Arun");


        //MOVING PLAYERS
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isGameStarted && playerOneTurn)
                {
                    int diceValue = dice.generateDiceValue();
                    RotateTransition rt = dice.rollDice();
                    SequentialTransition st = playerOne.movePlayer(diceValue);
                    SequentialTransition seq = new SequentialTransition();

                    if(st == null)
                        seq.getChildren().add(rt);
                    else
                        seq.getChildren().addAll(rt , st);

                    seq.play();
                    diceLabel.setText("DICE VALUE : " + diceValue);


                    //WINNING CONDITION
                    if(playerOne.isWinner())
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Congratulations " + playerOne.getName() + "! You Won The Game");
                        alert.setTitle("WINNER");
                        alert.setHeaderText("");
                        alert.show();

                        playerOneTurn = false;
                        playerOneButton.setDisable(true);
                        playerOneLabel.setText("");


                        playerTwoTurn = false;
                        playerTwoButton.setDisable(true);
                        playerTwoLabel.setText("");

                        isGameStarted = false;
                        startButton.setDisable(false);
                        startButton.setText("RESTART");
                        diceLabel.setText("RESTART GAME");

                    }
                    else
                    {
                        playerOneTurn = false;
                        playerOneButton.setDisable(true);
                        playerOneLabel.setText("");

                        playerTwoTurn = true;
                        playerTwoButton.setDisable(false);
                        playerTwoLabel.setText("Your Turn");
                    }

                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerOne.bringToStart(); //IN CASE OF RESTARTING MATCH
                playerTwo.bringToStart();

                isGameStarted = true;
                diceLabel.setText("GAME STARTED");
                startButton.setDisable(true);

                playerOneLabel.setText("Your Turn");
                playerOneButton.setDisable(false);
                playerOneButton.setText(playerOne.getName());
                playerOneTurn = true;

                playerTwoTurn = false;
                playerTwoButton.setText(playerTwo.getName());
//                playerTwoButton.setDisable(true);
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isGameStarted && playerTwoTurn)
                {
                    int diceValue = dice.generateDiceValue();
                    RotateTransition rt = dice.rollDice();
                    SequentialTransition st = playerTwo.movePlayer(diceValue);
                    SequentialTransition seq = new SequentialTransition();

                    if(st == null)
                        seq.getChildren().add(rt);
                    else
                        seq.getChildren().addAll(rt , st);

                    seq.play();
                    diceLabel.setText("DICE VALUE : " + diceValue);

                    //WINNING CONDITION
                    if(playerTwo.isWinner())
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Congratulations " + playerTwo.getName() + "! You Won The Game");
                        alert.setTitle("WINNER");
                        alert.setHeaderText("");
                        alert.show();

                        playerOneTurn = false;
                        playerOneButton.setDisable(true);
                        playerOneLabel.setText("");

                        playerTwoTurn = false;
                        playerTwoButton.setDisable(true);
                        playerTwoLabel.setText("");

                        isGameStarted = false;
                        startButton.setDisable(false);
                        startButton.setText("RESTART");
                        diceLabel.setText("RESTART GAME");


                    }
                    else
                    {
                        playerTwoTurn = false;
                        playerTwoButton.setDisable(true);
                        playerTwoLabel.setText("");
                        playerOneTurn = true;
                        playerOneButton.setDisable(false);
                        playerOneLabel.setText("Your Turn");
                    }

                }
            }
        });


        root.getChildren().addAll(board , playerOneButton , startButton , playerTwoButton,
                                  playerOneLabel , diceImageView , playerTwoLabel , playerOne.getCoin(),
                                  playerTwo.getCoin() , diceLabel);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createGame());
        stage.setTitle("Snake and Ladder");
        Image icon = new Image("C:\\Users\\DELL\\IdeaProjects\\SnakeLadder\\src\\Dice Pics\\SNL Logo.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}