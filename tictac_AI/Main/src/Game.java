// import java.util.tictactoe;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.lang.Package;

class Game extends tictactoe{

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean playGame = true;

        System.out.println("Would you like to play Tic Tac Toe? yes/no");

        String ans = scan.nextLine();
        while(playGame)
        {
            if(ans.toLowerCase().equals("yes"))
            {
                AIcheck();
                System.out.println("Would you like to play again? yes/no");
                ans = scan.nextLine();
            }else if(ans.toLowerCase().equals("no"))
            {
                playGame = false;
                System.out.println("Goodbye");
            }else{
                System.out.println("Please enter a valid input");
                ans = scan.nextLine();
            }
        }
    }

    public static void AIcheck()
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Would like to play against an AI or a Human? AI/Human");

        String input = scan.nextLine();

        boolean exit = false;
        while(exit ==  false)
        {
            if(input.toLowerCase().equals("human"))
            {
                gameLogic();
                exit = true;
            }else if(input.toLowerCase().equals("ai"))
            {
                exit = true;
            }else{
                System.out.println("Please enter a valid input");
                input = scan.nextLine();
            }
        }
    }
}