import java.util.ArrayList;

public class GameBoard{
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private char[][] gameBoard = {{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}};
    private int[][] intboard = {{0,0,0},
                                {0,0,0},
                                {0,0,0}};


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
                intboard[row][column] = -1;
            }else if(player == 0){
                gameBoard[row][column] = O;
                intboard[row][column] = 1;    
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
        if(OpenSpaces().length == 0)
        {
            return true;
        }
        return false;

    }

    /*
        Returns higher numbers if player 1 is at an advantage
        or lower numbers if player 2 is at an advantage
    */

    public int evaluate2()
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
                    return 100;
                }else{
                    return -100;
                }
            }
            if((gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2]))
            {
                if(gameBoard[1][1] == X)
                {
                    return 100;
                }else{
                    return -100;
                }
            }
            if((gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2]))
            {
                if(gameBoard[2][1] == X)
                {
                    return 100;
                }else{
                    return -100;
                }
            }
            if((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) ||
            (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]))
            {
                if(gameBoard[1][1] == X)
                {
                    return 100;
                }else{
                    return -100;
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


    /*Evaluate with intboard */
    public int evaluate()
    {
        int eval = 0;

        if(OpenSpaces().length == 0)
        {
            return 0;
        }


        int rowtally1 = 0;
        int rowtally2 = 0;
        int rowtally3 = 0;
        int[] rowtally = new int[3];

        int coltally1 = 0;
        int coltally2 = 0;
        int coltally3 = 0;
        int[] coltally = new int[3];

        int diagtally1 = intboard[0][0] + intboard[1][1] + intboard[2][2];
        int diagtally2 = intboard[2][0] + intboard[1][1] + intboard[0][2];

        for(int i = 0; i < 3; i++)
        {
            rowtally1 = rowtally1 + intboard[0][i];
            rowtally2 = rowtally2 + intboard[1][i];
            rowtally3 = rowtally3 + intboard[2][i];

            coltally1 = coltally1 + intboard[i][0];
            coltally2 = coltally2 + intboard[i][1];
            coltally3 = coltally3 + intboard[i][2];

            rowtally[i] = intboard[0][i] + intboard[1][i] + intboard[2][i];
            coltally[i] = intboard[i][0] + intboard[i][1] + intboard[i][2];
        }

        if(checkWin() == true)
        {
            if(rowtally1 == 3  || rowtally2 == 3 || rowtally3 == 3 || 
               coltally1 == 3  || coltally2 == 3 || coltally3 == 3 ||
               diagtally1 == 3 || diagtally2 == 3)
            {
                return 100;
            }else if(rowtally1 == -3  || rowtally2 == -3 || rowtally3 == -3 || 
                     coltally1 == -3  || coltally2 == -3 || coltally3 == -3 ||
                     diagtally1 == -3 || diagtally2 == -3)
            {
                return -100;
            }
        }

        if(rowtally1 == 2 || rowtally2 == 2 || rowtally3 == 2 || 
           coltally1 == 2 || coltally2 == 2 || coltally3 == 2 ||
           diagtally1 == 2 || diagtally2 == 2)
        {
            return eval + 10;
        }else if(rowtally1 == -2  || rowtally2 == -2 || rowtally3 == -2 || 
                 coltally1 == -2  || coltally2 == -2 || coltally3 == -2 ||
                diagtally1 == -2 || diagtally2 == -2)
        {
            return eval - 10;
        }

        int Odoubles = 0;
        int Xdoubles = 0;

        for(int i = 0; i < 3; i++)
        {
            if(rowtally[i] == 2)
            {
                Odoubles++;
            }else if(rowtally[i] == -2)
            {
                Xdoubles++;
            }
        }
        // eval = (3*Odoubles)-(3*Xdoubles);
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

    public void printInt()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + intboard[i][j]+ " ");
                if(j < 2)
                {
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
        for(int i = 0; i < 3 ; i++)
        {
            char[] aMatrix = gameBoard[i];
            int   aLength = aMatrix.length;
            clone.gameBoard[i] = new char[aLength];
            System.arraycopy(aMatrix, 0, clone.gameBoard[i], 0, aLength);
        }

        for(int i = 0; i < 3 ; i++)
        {
            int[] aMatrix = intboard[i];
            int   aLength = aMatrix.length;
            clone.intboard[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, clone.intboard[i], 0, aLength);
        }
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