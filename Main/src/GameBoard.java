public class GameBoard{
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private char[][] gameBoard = {{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}};

    
    /*  
        checks that the given coords are inbounds and not already occupied
        and places a piece (X for player 1, 0 for player 2)
        returns true if a piece places succesfully, or false otherwise
     */
    public boolean tryPlacePiece(int player, int row, int column){
        if(row < 0 || row > 2){
            return false;
        }else if(column < 0 || column > 2){
            return false;
        }else if(gameBoard[row][column] != EMPTY)
        {
            return false;
        }else{
            if(player == 1)
            {
                gameBoard[row][column] = X;
            }else if(player == 0){
                gameBoard[row][column] = O;    
            }
            return true;
        }
    }

    /* 
        Checks if there is a winner in the current state
    */

    public boolean checkWin(){
        // int counterX = 0;
        // int counterO = 0;
        // int counterXV = 0;
        // int counterOV = 0;
    
        // for(int i = 0; i < 3; i++)
        // {
        //     counterX = 0;
        //     counterO = 0;
        //     for(int j = 0; j < 3; j++)
        //     {
        //         if(gameBoard[i][j] == X)
        //         {
        //             counterX++;
        //         }else if(gameBoard[i][j] == O)
        //         {
        //             counterO++;
        //         }
        //     }
        //     if(counterX == 3 || counterO == 3)
        //     {
        //         return true;
        //     }
        //     if(gameBoard[i][0] == X || gameBoard[i][1] == X || gameBoard[i][2] == X)
        //     {
        //         counterXV++;
        //     }else if(gameBoard[i][0] == X || gameBoard[i][1] == O || gameBoard[i][2] == O)
        //     {
        //         counterOV++;
        //     }
        // }
        // if(counterOV == 3 || counterXV == 3)
        // {
        //     return true;
        // }

        // return false;

        if((gameBoard[0][0] == 'X' && gameBoard[0][1] == 'X' && gameBoard[0][2] == 'X') ||
           (gameBoard[1][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[1][2] == 'X') ||
           (gameBoard[2][0] == 'X' && gameBoard[2][1] == 'X' && gameBoard[2][2] == 'X') ||

           (gameBoard[0][0] == 'X' && gameBoard[1][0] == 'X' && gameBoard[2][0] == 'X') ||
           (gameBoard[0][1] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][1] == 'X') ||
           (gameBoard[0][2] == 'X' && gameBoard[1][2] == 'X' && gameBoard[2][2] == 'X') ||

           (gameBoard[0][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X') ||
           (gameBoard[0][2] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][0] == 'X') ||

           (gameBoard[0][0] == 'O' && gameBoard[0][1] == 'O' && gameBoard[0][2] == 'O') ||
           (gameBoard[1][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[1][2] == 'O') ||
           (gameBoard[2][0] == 'O' && gameBoard[2][1] == 'O' && gameBoard[2][2] == 'O') ||

           (gameBoard[0][0] == 'O' && gameBoard[1][0] == 'O' && gameBoard[2][0] == 'O') ||
           (gameBoard[0][1] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][1] == 'O') ||
           (gameBoard[0][2] == 'O' && gameBoard[1][2] == 'O' && gameBoard[2][2] == 'O') ||

           (gameBoard[0][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][2] == 'O') ||
           (gameBoard[0][2] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][0] == 'O'))
        {
            return true;
        }
        return false;

    }

    /*
        Returns higher numbers if player 1 is at an advantage
        or lower numbers if player 2 is at an advantage
    */

    // public int evaluate(){

    // }

    /*
        Prints out Current Gameboard
    */
    public void print()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[i][j] == X)
                {
                    System.out.print(" X ");
                }else if(gameBoard[i][j] == O)
                {
                    System.out.print(" O ");
                }else{
                    System.out.print("   ");
                }
                if(j < 2){
                    System.out.print('|');
                }
            }
            System.out.println("");
            if(i < 2){
                System.out.print("---+---+---");
                System.out.println("");
            }
        }
    }

    /*
        Creates and returns a clone of this game board
    */
    public GameBoard clone(){
        GameBoard clone = new GameBoard();

        clone.gameBoard = gameBoard;

        return clone;
    }


}