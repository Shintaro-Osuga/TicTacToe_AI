import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.lang.Package;

// package util;


public class tictactoe{
    // public static void main(String[] args) throws Exception {
    //     Scanner scan = new Scanner(System.in);
    //     boolean playGame = true;

    //     System.out.println("Would you like to play Tic Tac Toe? yes/no");

    //     String ans = scan.nextLine();
    //     while(playGame)
    //     {
    //         if(ans.toLowerCase().equals("yes"))
    //         {
    //             AIcheck();
    //             System.out.println("Would you like to play again? yes/no");
    //             ans = scan.nextLine();
    //         }else if(ans.toLowerCase().equals("no"))
    //         {
    //             playGame = false;
    //             System.out.println("Goodbye");
    //         }else{
    //             System.out.println("Please enter a valid input");
    //             ans = scan.nextLine();
    //         }
    //     }
    // }

    // public static void AIcheck()
    // {
    //     Scanner scan = new Scanner(System.in);
        
    //     System.out.println("Would like to play against an AI or a Human? AI/Human");

    //     String input = scan.nextLine();

    //     boolean exit = false;
    //     while(exit ==  false)
    //     {
    //         if(input.toLowerCase().equals("human"))
    //         {
    //             gameLogic();
    //             exit = true;
    //         }else if(input.toLowerCase().equals("ai"))
    //         {
    //             exit = true;
    //         }else{
    //             System.out.println("Please enter a valid input");
    //             input = scan.nextLine();
    //         }
    //     }
    // }

    public static void gameLogic()
    {
        int turnCounter = 0;
        boolean gameEnd = false;
        int[][] A = {{0,0,0},{0,0,0},{0,0,0}};
        String[][] B = {{"+","+","+"},{"+","+","+"},{"+","+","+"}};
        // drawboard(A);
        
        System.out.println("-------------");
        drawboard2(B);

        while(gameEnd == false){
            int[] pos = input(A, turnCounter);
            updateboard(A, pos, changeTurn(turnCounter));
            updateboard2(B, pos, changeTurn(turnCounter));
            // drawboard(A);
            System.out.println("-------------");
            drawboard2(B);
            turnCounter+=1;
            int win = checkWin(A);
            if(win == -1)
            {
                System.out.println("Player 1 wins!");
                gameEnd = true;
            }else if(win == 1)
            {
                System.out.println("Player 2 wins!");
                gameEnd = true;
            }else if(turnCounter == 9)
            {
                System.out.println("Draw!");
                gameEnd = true;
            }
        }
    }

    public static int changeTurn(int turnCounter){
        if(turnCounter%2 == 0)
        {
            return -1;
        }else{return 1;}
    }

    public static void drawboard(int[][] A){
        for(int i = 0; i<3;i++)
        {
            System.out.println("-------------");
            System.out.print("| " + A[i][0] + " ");
            System.out.print("| " + A[i][1] + " |");
            System.out.print(" " + A[i][2] + " |");
            System.out.print("\n");
            System.out.println("-------------");
        }
    }

    public static void drawboard2(String[][] B){
        for(int i = 0; i<3;i++)
        {
            System.out.print("| " + B[i][0] + " ");
            System.out.print("| " + B[i][1] + " |");
            System.out.print(" " + B[i][2] + " |");
            System.out.print("\n");
            System.out.println("-------------");
        }
    }

    public static int[] input(int[][] A, int turnCounter){
        Scanner scan = new Scanner(System.in);
        boolean exit = true;

        while(true)
        {
            if(turnCounter%2 == 0)
            {
                System.out.println("Player 1:");
            }else if(turnCounter%2 != 0){
                System.out.println("Player 2:");
            }
            System.out.println("What position do you want to play?");

            String input = scan.nextLine();

            int[] pos = parseString(input);

            if(pos[0] != 3 || pos[1] != 3)
            {
                if(checkAvailable(A, pos))
                {
                    return pos;
                }//else{System.out.print("That location is already played");}
            }
            System.out.println("Please enter a valid location:");
        }
    }

    public static boolean checkAvailable(int[][] A, int[] pos)
    {
        if(pos[0] >= 3 || pos[0] < 0 || pos[1] >= 3 || pos[1] < 0)
        {
            return false;
        }
        if(A[pos[0]][pos[1]] == 0)
        {
            return true;
        }else{return false;}
    }

    public static int[] parseString(String s){
        Character front = s.charAt(0);
        Character back = s.charAt(s.length()-1);
        Character middle = s.charAt(2);

        if(front.equals('{') && back.equals('}') && middle.equals(','))
        {
            int row = Integer.parseInt(String.valueOf(s.charAt(1)));
            int col = Integer.parseInt(String.valueOf(s.charAt(3)));
            int[] M = {row, col};
            return M;
        }else{
            int[] M = {3,3};
            return M;
        }
    }

    public static int[][] updateboard(int[][] A, int[] pos, int player){
        A[pos[0]][pos[1]] = player;
        return A;
    }

    public static String[][] updateboard2(String[][] B, int[] pos, int player)
    {
        if(player == -1)
        {
            B[pos[0]][pos[1]] = "O";
        }else if(player == 1)
        {
            B[pos[0]][pos[1]] = "X";
        }
        return B;
    }

    public static int checkWin(int[][] A)
    {
        if(A[0][0] + A[0][1] + A[0][2] == 3 
        || A[1][0] + A[1][1] + A[1][2] == 3 
        || A[2][0] + A[2][1] + A[2][2] == 3 

        || A[0][0] + A[1][0] + A[2][0] == 3 
        || A[0][1] + A[1][1] + A[2][1] == 3 
        || A[0][2] + A[1][2] + A[2][2] == 3 
        
        || A[0][0] + A[1][1] + A[2][2] == 3 
        || A[2][2] + A[1][1] + A[2][0] == 3)
        {
            return 1;
        }

        if(A[0][0] + A[0][1] + A[0][2] == -3
        || A[1][0] + A[1][1] + A[1][2] == -3
        || A[2][0] + A[2][1] + A[2][2] == -3

        || A[0][0] + A[1][0] + A[2][0] == -3
        || A[0][1] + A[1][1] + A[2][1] == -3
        || A[0][2] + A[1][2] + A[2][2] == -3

        || A[2][2] + A[1][1] + A[2][0] == -3
        || A[0][0] + A[1][1] + A[2][2] == -3)
        {
            return -1;
        }

        return 0;
    }
}
