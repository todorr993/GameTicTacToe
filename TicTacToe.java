package com.company;

//this class implement methods that makes TicTacToePlayer class easier to use
public class TicTacToe {

    private TicTacToePlayer playerScore;

    public TicTacToe(String playerName, char sign) {
        playerScore = new TicTacToePlayer(playerName, sign);
    }//end constructor

    //call all corresponding increase methods for adding this move
    public void addMove(int rowID, int columnID){
            playerScore.increaseRows(rowID);
            playerScore.increaseColumns(columnID);
            if (columnID==rowID)
            {
                playerScore.increaseDiagonalLeft();
                if (columnID==1)
                    playerScore.increaseDiagonalRight();
            }else if (rowID==0 && columnID==2 || rowID==2 && columnID==0)
                playerScore.increaseDiagonalRight();
    }//end method

    //for the last made move, check if it brings the victory
    public boolean checkWinner(int rowID, int columnID){
        if(playerScore.checkRow(rowID)) return true;
        if(playerScore.checkColumn(columnID)) return true;
        if(playerScore.checkDiagonalLeft() ) return true;
        if(playerScore.checkDiagonalRight() ) return true;

        return false;
    }//end method


    public TicTacToePlayer getPlayer(){
        return this.playerScore;
    }//end method



}//end method
