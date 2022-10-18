import java.util.ArrayList;

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

    public int evaluate()
    {
        int Xdoubles = 0;
        int Odoubles = 0;
        int Xsingles = 0;
        int Osingles = 0;

        //Check to see who won if game has ended
        if(checkWin() == true)
        {
            if((gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2]))
            {
                if(gameBoard[0][1] == X)
                {
                    return 10;
                }else{
                    return -10;
                }
            }
            if((gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2]))
            {
                if(gameBoard[1][1] == X)
                {
                    return 10;
                }else{
                    return -10;
                }
            }
            if((gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2]))
            {
                if(gameBoard[2][1] == X)
                {
                    return 10;
                }else[
                    return -10;
                ]
            }
            if((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) ||
            (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]))
            {
                if(gameBoard[1][1] == X)
                {
                    return 10;
                }else{
                    return -10;
                }
            }
        }

        //If there are no open spaces, there is a draw
        //return 0
        if(OpenSpaces().length == 0)
        {
            return 0;
        }

        for(int i = 0; i < 3; i++)
        {
            //Checking horizontal values
            if(gameBoard[i][0] == gameBoard[i][1] || gameBoard[i][1] == gameBoard[i][2])
            {
                if(gameBoard[i][1] == X)
                {
                    Xdoubles++;
                }else{
                    Odoubles++;
                }
            }

            //Checking verticle values
            if(gameBoard[0][i] == gameBoard[1][i] || gameBoard[1][i] == gameBoard[2][i])
            {
                if(gameBoard[1][i] == X)
                {
                    Xdoubles++;
                }else{
                    Odoubles++;
                }
            }
        }

        //Checking diagonal values
        if(gameBoard[0][0] == gameBoard[1][1] || gameBoard[1][1] == gameBoard[2][2])
        {
            if(gameBoard[1][1] == X)
            {
                Xdoubles++;
            }else{
                Odoubles++;
            }
        }
        if(gameBoard[0][2] == gameBoard[1][1] || gameBoard[1][1] == gameBoard[2][0])
        {
            if(gameBoard[1][1] == X)
            {
                Xdoubles++;
            }else{
                Odoubles++;
            }
        }



        //Checking for singular values
        //Checking first row
        if(gameBoard[0][0] != EMPTY || gameBoard[0][1] != gameBoard[0][0] || gameBoard[1][0] != gameBoard[0][0] || gameBoard[1][1] != gameBoard[0][0])
        {
            if(gameBoard[0][0] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[0][1] != EMPTY || gameBoard[0][0] != gameBoard[0][1] || gameBoard[0][2] != gameBoard[0][1] || gameBoard[1][1] != gameBoard[0][1])
        {
            if(gameBoard[0][1] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[0][2] != EMPTY || gameBoard[0][1] != gameBoard[0][2] || gameBoard[1][2] != gameBoard[0][2] || gameBoard[1][1] != gameBoard[0][2])
        {
            if(gameBoard[0][2] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }

        //Checking second row
        if(gameBoard[1][0] != EMPTY || gameBoard[1][1] != gameBoard[0][0] || gameBoard[1][0] != gameBoard[1][1] || gameBoard[1][0] != gameBoard[1][2])
        {
            if(gameBoard[1][0] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[1][1] != EMPTY || gameBoard[0][0] != gameBoard[1][1] || gameBoard[0][2] != gameBoard[1][1] || gameBoard[2][2] != gameBoard[1][1] ||
        gameBoard[0][2] != gameBoard[1][1] || gameBoard[0][1] != gameBoard[1][1] || gameBoard[1][0] != gameBoard[1][1] || gameBoard[1][2] != gameBoard[1][1] ||
        gameBoard[2][1] != gameBoard[1][1])
        {
            if(gameBoard[1][1] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[1][2] != EMPTY || gameBoard[0][2] != gameBoard[1][2] || gameBoard[2][2] != gameBoard[1][2] || gameBoard[1][1] != gameBoard[1][2])
        {
            if(gameBoard[1][2] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }

        //Checking third row
        if(gameBoard[2][0] != EMPTY || gameBoard[1][0] != gameBoard[2][0] || gameBoard[2][1] != gameBoard[2][0] || gameBoard[1][1] != gameBoard[2][0])
        {
            if(gameBoard[2][0] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[2][1] != EMPTY || gameBoard[2][0] != gameBoard[2][1] || gameBoard[2][2] != gameBoard[2][1] || gameBoard[1][1] != gameBoard[2][1])
        {
            if(gameBoard[2][1] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }
        if(gameBoard[2][2] != EMPTY || gameBoard[2][1] != gameBoard[2][2] || gameBoard[1][2] != gameBoard[2][2] || gameBoard[1][1] != gameBoard[2][2])
        {
            if(gameBoard[2][2] == X)
            {
                Xsingles++;
            }else{
                Osingles++;
            }
        }

        int eval = (3*Xdoubles) + Xsingles - ((3*Odoubles) + Osingles);
        return eval;
    }

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


    public int[][] OpenSpaces()
    {
        ArrayList<int[]> countlist = new ArrayList<int[]>();
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[i][j] == EMPTY)
                {
                    countlist.add(new int[]{i,j});
                }
            }
        }

        int[][] count = new int[countlist.size()][2];
        for(int i = 0; i < countlist.size(); i++)
        {
            count[i] = countlist.get(i);
        }
        return count;
    }
}