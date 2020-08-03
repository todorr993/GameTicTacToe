package com.company;

public class AIPlayerInput implements PlayerInput {
    private TicTacToeAI ai;

    public AIPlayerInput(char sing){
        ai=new TicTacToeAI(sing);
    }

    @Override
    public String askForInput() {
        return ai.findBestMove(Stage.board);
    }
}//end class
