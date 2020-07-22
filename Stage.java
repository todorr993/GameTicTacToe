package com.company;

import java.util.Scanner;


//this class handles input/output
//provide all mandatory information to Game class (name of players, users' moves)
//make instance of AI player that returns moves of first player
public class Stage {
    private Scanner scanner;
    private char[][] board;
    private int numberOfMoves;
    private Game game;
    private TicTacToeAI playerAI;


    public Stage (){
        scanner=new Scanner(System.in);
        board=new char[3][3];
        playerAI=new TicTacToeAI();
    }//end constructor

    //method to restart stage = assign ' ' to all elements of board[][]
    public void restartBoard ()
    {
        numberOfMoves = 0;

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

    //game stage flow
    public void startGame(){
        boolean resign=false;
        String player2name;

        player2name=askForName();
        game=new Game("Computer", player2name);

        //clean the board
        restartBoard();

        //start the game
        while (true){
            String input;
            int[] xy=new int[2];        //for input coordinates xy[0]=x, xy[1]=y

            //show current board
            showStage();

            //identify what player is on the move
            game.whoIsPlaying(numberOfMoves);
            System.out.println(game.nameOfCurrentPlayer() + " is playing:");

            //check if it is player move
            if(numberOfMoves%2!=0) {

                //if the current player has resigned, next player on the move is winner
                if ((input = scanner.nextLine()).equalsIgnoreCase("resign")) {
                    resign=true;
                    System.out.println("End of the game. " + game.nameOfCurrentPlayer() +" is winner!");
                    break;
                }

                //check input format, if there are is 2 'words' written
                if (input.split(" ").length == 2) {
                    try {
                        //this  method Integer.parseInt() throws NumberFormatException if string is not number
                        //horizontal - columns
                        xy[1] = Integer.parseInt(input.split(" ")[0]);
                        //vertical - rows
                        xy[0] = Integer.parseInt(input.split(" ")[1]);

                        //check if numbers are in range: 1 to 3
                        if (xy[0] < 1 || xy[0] > 3 || xy[1] < 1 || xy[1] > 3) {
                            System.out.println("Invalid input: those coordinates are outside the playable area");
                            continue;
                        }
                        //decrease it for one, since board dimension starts from 0
                        xy[0]--;
                        xy[1]--;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: you must enter the x and y coordinates separated by spaces");
                        continue;
                    }
                } else {
                    System.out.println("Invalid input: you must enter the x and y coordinates separated by spaces");
                    continue;
                }
            }else { //otherwise computer should make move, call AI methods to evaluate best move
                xy= playerAI.findBestMove(board);
            }

            //check if the cell on the board is empty
            if (board[xy[0]][xy[1]]!=' '){
                System.out.println("Invalid input: that space is already taken");
                continue;
            }else
            {
                //add move and return true if true that move was winning move
                if(game.play(xy[0], xy[1], numberOfMoves++))
                {
                    board[xy[0]][xy[1]]=game.currentPlayerSing();
                    showStage();
                    System.out.println("End of the game. "+game.nameOfCurrentPlayer()+" is winner!");
                    break;
                }
                //add move on board
                board[xy[0]][xy[1]]=game.currentPlayerSing();


                //if it is 9th move and no winner till now, then it is end of the game
                if(numberOfMoves==9) {
                    showStage();
                    System.out.println("End of the game. Nobody won.");
                    break;
                }
            }

        }//end while

        //ask to play again only if in case that nobody 'resign' in the game
        if (!resign)
            playAgain();

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


    public void closeScanner(){
        scanner.close();
    }//end method



}//end class
