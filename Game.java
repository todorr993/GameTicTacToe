package com.company;


//this class provides methods for playing TicTacToe game
public class Game {

    private TicTacToe player1;     //to save data of first player
    private TicTacToe player2;     //to save data of second player
    private TicTacToe currentPlayer;
    private int numberOfMoves;
    private GameEnd gameEndStatus;


    public Game (String namePlayer1, String namePlayer2){
        player1 = new TicTacToe(namePlayer1, 'X');
        player2 =new TicTacToe(namePlayer2, 'O');

        numberOfMoves=0;

    }//end constructor

    //identify what player is on the move
    public void assignCurrentPlayer(){
        if(numberOfMoves%2==0)
            currentPlayer= player1;
        else currentPlayer= player2;
    }//end method


    //read player input
    public String takePlayerInput(){
        return currentPlayer.readInput();
    }//end method

    //add move
    public void addMove(int rows, int columns){
        currentPlayer.addMove(rows, columns);
        ++numberOfMoves;
    }//end method

    //check if game is over
    public boolean isGameOver(int rowID, int columnID){
        //only after 4 moves, there can be winner
        if(numberOfMoves>4)
        {
            //check if current player won
            if (checkVictory(rowID, columnID)){
                gameEndStatus= GameEnd.WIN;
                return true;
            }else if (numberOfMoves==9)   //if numberOfMoves=9, then there is no more empty cells on the board
            {
                gameEndStatus= GameEnd.DRAW;
                return true;
            }
        }return false;
    }


    //check is the current player winner
    public boolean checkVictory(int rows, int columns){
        if (currentPlayer.checkWinner()) {
            return true;
        }

        return false;
    }//end method



    public void setGameEndStatus(GameEnd gameEndStatus) {
        this.gameEndStatus = gameEndStatus;
    }

    public GameEnd getGameEndStatus(){
        return gameEndStatus;
    }
    public String nameOfPlayerOnTheMove(){
        return currentPlayer.getPlayer().getName();
    }//end method



    //return the name of another play-not the one who is playing
    public String nameOfAnotherPlayer(){
        String anotherPlayerName;
        ++numberOfMoves;
        assignCurrentPlayer();
        anotherPlayerName=nameOfPlayerOnTheMove();
        --numberOfMoves;
        assignCurrentPlayer();
        return anotherPlayerName;
    }//end method


    public char currentPlayerSing(){
        return currentPlayer.getPlayer().getSign();
    }//end method



}
