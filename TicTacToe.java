package com.company;

//this class implement methods that makes TicTacToePlayer class easier to use
public class TicTacToe {

    private TicTacToePlayer playerScore;
    private PlayerInput playerInput;
    private boolean isWinner;

    public TicTacToe(String playerName, char sign) {
        PlayerInputFactory playerInputFactory=new PlayerInputFactory();

        playerScore = new TicTacToePlayer(playerName, sign);
        playerInput=playerInputFactory.factoryMethod(playerName, sign);
        isWinner=false;
    }//end constructor


    //call all corresponding increase methods for adding this move
    public void addMove(int rowID, int columnID){
            addValueInRows(rowID);
            addValueInColumns(columnID);

        if (columnID==rowID)
            {
                addValueInLeftDiagonal();
                if (columnID==1) {
                    addValueInRightDiagonal();
                }
            }else if (rowID==0 && columnID==2 || rowID==2 && columnID==0)
                addValueInRightDiagonal();
    }//end method


    public void addValueInRows(int rowID){
        playerScore.increaseRows(rowID);
        if(playerScore.checkRow(rowID))
            isWinner=true;
    }

    public void addValueInColumns(int columnID){
        playerScore.increaseColumns(columnID);
        if(playerScore.checkColumn(columnID))
            isWinner=true;
    }

    public void addValueInLeftDiagonal(){
        playerScore.increaseDiagonalLeft();
        if(playerScore.checkDiagonalLeft())
            isWinner=true;
    }


    public void addValueInRightDiagonal(){
        playerScore.increaseDiagonalRight();
        if(playerScore.checkDiagonalRight())
            isWinner=true;
    }




    public boolean checkWinner(){
        return isWinner;
    }//end method


    public TicTacToePlayer getPlayer(){
        return this.playerScore;
    }//end method

    public String readInput(){
        return playerInput.askForInput();
    }



}//end method
