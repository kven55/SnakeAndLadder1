package com.example.snakeladder;

import javafx.util.Pair;

public class Board {
    Pair<Integer,Integer>[] positionCoordinates;
    int[] snakeLadderPosition;
    Board()
    {
        positionCoordinates = new Pair[101];
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    private void populatePositionCoordinates()
    {
        int ctr = 1; //for traversing through arr
        positionCoordinates[0] = new Pair(0 , 0); //dummy value bcz we dont need 0index(WE ARE JUST MAPPING THE BOARD FROM 1 TO 100)

        for (int i = 0; i < SnakeLadder.height; i++) {

            int xcord = 0;

            for (int j = 0; j < SnakeLadder.width; j++) {
                if(i % 2 == 0) //IN EVEN ROWS XCOORDINATES INCREASES
                {
                    xcord =  (j * SnakeLadder.tilesize) + (SnakeLadder.tilesize / 2);
                }
                else
                {
                    xcord = (SnakeLadder.width * SnakeLadder.tilesize) - (j * SnakeLadder.tilesize) - (SnakeLadder.tilesize / 2);
                }

                int ycord = (SnakeLadder.height * SnakeLadder.tilesize) - (i * SnakeLadder.tilesize) - (SnakeLadder.tilesize / 2);
                positionCoordinates[ctr++] = new Pair(xcord , ycord);
            }
        }
    }

    // MAPPING THE SNAKES AND LADDER TO INDEXES
    private void populateSnakeLadder()
    {
        snakeLadderPosition = new int[101];
        snakeLadderPosition[4] = 25;
        snakeLadderPosition[13] = 46;
        snakeLadderPosition[33] = 49;
        snakeLadderPosition[42] = 63;
        snakeLadderPosition[50] = 69;
        snakeLadderPosition[62] = 81;
        snakeLadderPosition[74] = 92;
        snakeLadderPosition[27] = 5;
        snakeLadderPosition[40] = 3;
        snakeLadderPosition[43] = 18;
        snakeLadderPosition[54] = 31;
        snakeLadderPosition[66] = 45;
        snakeLadderPosition[76] = 58;
        snakeLadderPosition[89] = 53;
        snakeLadderPosition[99] = 41;
    }

    //TO GET NEW POSITION AFTER SNAKE OR LADDER(IF ANY)
    public int newPosition(int currentPosition)
    {
        if(currentPosition >= 1 && currentPosition <= 100)
            return snakeLadderPosition[currentPosition];

        return -1;
    }


    int getXCoordinates(int Position)
    {
        if(Position >= 1 && Position <= 100)
            return positionCoordinates[Position].getKey();

        return -1;
    }

    int getYCoordinates(int Position)
    {
        if(Position >= 1 && Position <= 100)
            return positionCoordinates[Position].getValue();

        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 1; i < 101; i++) {
//            System.out.println(Board.positionCoordinates[i].getKey() + " " +
//                    Board.positionCoordinates[i].getValue());
//        }
//    }
}
