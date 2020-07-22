package com.company;


//this class provides methods for playing TicTacToe game
public class Game {

    //private int numberOfMoves;
    private TicTacToe player1;     //to save data of first player
    private TicTacToe player2;     //to save data of second player
    private TicTacToe currentPlayer;


    public Game (String namePlayer1, String namePlayer2){
        player1 = new TicTacToe(namePlayer1, 'X');
        player2 =new TicTacToe(namePlayer2, 'O');
        //numberOfMoves=0;

    }//end constructor

    //identify what player is on the move
    public void whoIsPlaying(int numberOfMoves){
        if(numberOfMoves%2==0)
            currentPlayer= player1;
        else currentPlayer= player2;
    }//end method


    //game flow: add move and check if the current player is winner
    public boolean play(int rows, int columns, int numberOfMoves){

        currentPlayer.addMove(rows, columns);

        //we add one more move and only from the 5th move there can be winner
        if ((numberOfMoves+1)>4 && currentPlayer.checkWinner(rows, columns)) {
            return true;
        }

        return false;
    }//end method


    public String nameOfCurrentPlayer(){
        return currentPlayer.getPlayer().getName();
    }//end method


    public char currentPlayerSing(){
        return currentPlayer.getPlayer().getSign();
    }//end method



}
