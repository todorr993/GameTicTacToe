package com.company;

import java.util.Scanner;


//this class handles input/output
//provide all mandatory information to calls of methods of Game class (name of players, users' moves)
//

public class Stage {

    static char[][] board;

    private Scanner scanner;
    private boolean resign;
    private Game game;


    public Stage (){
        scanner=new Scanner(System.in);
        board=new char[3][3];
    }//end constructor


    //initialize Game
    public void startGame(){
        String player1=askForName();

        //
        while (player1.equalsIgnoreCase("ai")) {
            System.out.println("First player can not be AI, please insert your name again.");
            player1 = askForName();
        }

        String player2=askForName();

        game=new Game(player1, player2);

        restartGame();
        playGame();
    }//end method


    // play game flow
    public void playGame(){
        int[] coordinates;

        while(true){
            //read current player input
            coordinates=readPlayerInput();

            //if the current player has resigned, end the game
            if (resign) {
                break;
            }

            updateGame(coordinates[0], coordinates[1]);

            //check end of the game(victory/no more moves)
            if (game.isGameOver(coordinates[0], coordinates[1])) {
                break;
            }
        }//end while

        //print status of the game:
        printEndStatus();

        //ask to play again
        playAgain();

    }//end method


    //read input from current player
    public int[] readPlayerInput (){
        boolean inputValid=false;
        int[] inputCoordinates=new int[2];

        //determine who is on the move
        game.assignCurrentPlayer();

        //until the input is valid ask current player to make move
        while (!inputValid) {
            String input;

            showStage();
            System.out.println(game.nameOfPlayerOnTheMove() + " is playing:");
            input = game.takePlayerInput();

            //check if player has resigned
            if (isResign(input)) {
                resign=true;
                game.setGameEndStatus(GameEnd.RESIGN);
                return null;
            }

            //check input format: two values
            if (isPlayerInputValid(input)) {

                inputValid=true;

                //vertical - row number
                inputCoordinates[0] = Integer.parseInt(input.split(" ")[1]);
                //horizontal - column number
                inputCoordinates[1] = Integer.parseInt(input.split(" ")[0]);

                //decrease value of coordinates for 1, since board dimensions start from 0
                --inputCoordinates[0];
                --inputCoordinates[1];


            }

        }//end while

        return inputCoordinates;
    }//end method



    //update board and add move in game class
    public void updateGame(int rowID, int columnID){
        //play the move
        game.addMove(rowID, columnID);

        //add move on the board
        board[rowID][columnID] = game.currentPlayerSing();

    }//end method



    //ask to play again
    public void playAgain()
    {
        System.out.println("Do you want to play again?");
        if(scanner.nextLine().equalsIgnoreCase("yes"))
        {
            startGame();
        }else closeScanner();

    }//end method


    //print message about game end
    public void printEndStatus(){
        showStage();
        switch (game.getGameEndStatus()) {
            case WIN:
                System.out.println("End of the game. " + game.nameOfPlayerOnTheMove() + " is winner!");
                break;
            case DRAW:
                System.out.println("End of the game. Nobody won.");
                break;
            case RESIGN:
                System.out.println(game.nameOfPlayerOnTheMove()+" has resigned.");
                System.out.println(game.nameOfAnotherPlayer()+" is winner!");
                break;
        }//end switch
    }//end method



    //restart stage = assign ' ' to all elements of board[][] and resign=false
    public void restartGame()
    {
        resign=false;
        for (int i = 0; i < 3 ; i++)
            for (int j = 0; j < 3; j++)
                board[i][j]=' ';
    }


    // create board of the game in required format
    public void showStage(){
        for (int i = 0; i < 8; i++) {
            if (i == 0)
                System.out.println("    1   2   3  ");
            else if (i % 2 != 0)
                System.out.println("  ~~~~~~~~~~~~~");
            else System.out.println(((i / 2) + " | "+board[i/2-1][0]+" | "+board[i/2-1][1]+" | "+board[i/2-1][2]+" |"));
        }
    }//end method


    public String  askForName(){
        System.out.println("Please insert name:");
        return scanner.nextLine();
    }

    //check if player has resigned
    public boolean isResign(String input)
    {
        if (input.equalsIgnoreCase("resign"))
            return true;
        else return false;

    }//end method


    //is input valid
    public boolean isPlayerInputValid(String input) {

        String[] coordinates=input.split(" ");

        if (coordinates.length != 2) {
            InputError.INVALID_COORDINATES_FORMAT.printMessage();
            return false;
        }else {

            try {
                //Integer.parseInt() returns an NumberFormatException if string can not be convert to int
                int rowID = Integer.parseInt(input.split(" ")[1]);
                int colID = Integer.parseInt(input.split(" ")[0]);

                //decrease value of coordinates for 1, since board dimensions start from 0
                --rowID;
                --colID;

                //check if numbers are in playable area range and if the cell is free
                if (areCoordinatesValueValid(rowID, colID) && isPlaceAvailable(rowID, colID)) {
                    return true;
                }

            } catch (NumberFormatException e) {
                InputError.INVALID_COORDINATES_FORMAT.printMessage();
                return false;
            }
        }
    return false;
    }//end method


    public boolean isPlaceAvailable(int rowID, int columnID){
        if (board[rowID][columnID]!=' ') {
            InputError.INVALID_SPACE.printMessage();
            return false;
        }
        return true;
    }//end method




    //check if coordinates value are inside playable area
    public boolean areCoordinatesValueValid(int rowID, int columnID){
        if (rowID < 0 || rowID > 2 || columnID < 0 || columnID > 2) {
            InputError.INVALID_COORDINATES_VALUE.printMessage();
            return false;
        }else       return true;
    }//end method




    public void closeScanner(){
        scanner.close();
    }//end method



}//end class
