package com.company;


//this class provides method for finding best move using minmax algorithm
public class TicTacToeAI {

    private char board[][];
    private char singAI;
    private char signOpponent;

    public TicTacToeAI(char singAI)
    {
        this.singAI=singAI;
        this.signOpponent = singAI=='X'?'O':'X';
    }

    //check the board and return 10 if the maximizer player won, -10 if opponent won
    public int evaluate()
    {

        // Check all rows winning combination
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2])
            {
                if (board[row][0] == signOpponent)
                    return -10;
                else if (board[row][0] == singAI)
                    return +10;
            }
        }

        // Check all columns winning combination
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col])
            {
                if (board[0][col] == signOpponent)
                    return -10;

                else if (board[0][col] == singAI)
                    return +10;
            }
        }

        // Check left diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if (board[0][0] == signOpponent)
                return -10;
            else if (board[0][0] == singAI)
                return +10;
        }

        //check right diagonal
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if (board[0][2] == signOpponent)
                return -10;
            else if (board[0][2] == singAI)
                return +10;
        }

        // otherwise return 0
        return 0;
    }//end method


    //check if there is no empty cells on board
    public boolean isMovesLeft()
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }//end method


    //---- minimax recursive algorithm   ------
    //maximizer=AI; minimizer=opponent
    //go through border[][] and find all empty cells
    //after maximizer move, we want to return max value of all corresponding output
    //after minimizer move, we want to return min value of all corresponding output
    public int minimax (char[][] board, int depth, boolean isMax){

        //first evaluate current board[][] state
        int score = evaluate();

        //if Maximizer won, return his score (10)-depth
        //here adding depth effect to score make quicker wins (wins with less steps) better as choice
        if (score==10)
            return score-depth;

        //if Minimizer won, return his score (-10)+depth
        //here adding depth effect to score make quicker wins (wins with less steps) better as choice
        if(score==-10)
            return score+depth;

        //if there is no more empty cells on the board, return 0
        if(!isMovesLeft())
            return 0;

        // If this maximizer's move
        if(isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]==' ')
                    {
                        // Make the move
                        board[i][j] = singAI;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board, depth + 1, false));


                        // Undo the move
                        board[i][j] = ' ';


                    }
                }
            }
            return best;
        }
        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == ' ')
                    {
                        // Make the move
                        board[i][j] = signOpponent;


                        // Call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(board, depth + 1, true));

                        // Undo the move
                        board[i][j] = ' ';

                    }
                }
            }
            return best;
        }

    }//end method


    //method to evaluate best move
    //traverse all cells and call minimax function for every empty cell
    public String findBestMove(char board[][])
    {
        int bestVal = -1000;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;
        this.board=board;



        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == ' ')
                {
                    // Make the move
                    board[i][j] = singAI;


                    // compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false);


                    // Undo the move
                    board[i][j] = ' ';


                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return ++bestMove[1]+" "+ ++bestMove[0];
    }//end method



}//end class
