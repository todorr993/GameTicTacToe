package com.company;

//class for storing player's results
//we have 8 winning combination: 3 horizontal lines (rows), 3 vertical lines (columns), 2 diagonals
//row[i] represent that i-th row and it sums all appearance in that row
//0 X 0
//0 0 0
//0 0 0         row[0]=1;column[1]=1
//if any identifiers is equal to 3, player is winner

public class TicTacToePlayer {
    private String name;
    private char sign; //'X' or 'O'
    private int[] rows;
    private int[] columns;
    private int diagonalLeft;
    private int diagonalRight;

    public TicTacToePlayer(String name, char sign) {
        this.name=name;
        this.sign=sign;
        rows=new int[3];
        columns=new int[3];
    }//end constructor

    public String getName() {
        return name;
    }//end method

    public char getSign() {
        return sign;
    }//end method

    //increase sum of corresponding row
    public void increaseRows(int rowNumber){
        rows[rowNumber]=++rows[rowNumber];
    }//end method

    //increase sum of corresponding column
    public void increaseColumns(int columnNumber){
        columns[columnNumber]=++columns[columnNumber];
    }//end method

    //increase sum of left diagonal
    public void increaseDiagonalLeft(){
        ++diagonalLeft;
    }//end method


    //increase sum of right diagonal
    public void increaseDiagonalRight(){
        ++diagonalRight;
    }//end method


    //methods for checking winning state

    public boolean checkRow(int rowNumber)
    {
        if(rows[rowNumber]==3)
            return true;
        return false;
    }//end method

    public boolean checkColumn(int columnNumber)
    {
        if(columns[columnNumber]==3)
            return true;
        return false;
    }//end method

    public boolean checkDiagonalRight (){
        if (diagonalRight==3)
            return true;
        return false;
    }//end method

    public boolean checkDiagonalLeft (){
        if (diagonalLeft==3)
            return true;
        return false;
    }//end method


}//end class
