// import java.util.tictactoe;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.lang.Package;
import java.util.concurrent.ThreadLocalRandom;

class Game extends GameTreeNode{

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

    private static void AIcheck()
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Would like to play against an AI or a Human? AI/Human");

        String input = scan.nextLine();

        boolean exit = false;
        while(exit ==  false)
        {
            if(input.toLowerCase().equals("human"))
            {
                Play(input);
                exit = true;
            }else if(input.toLowerCase().equals("ai"))
            {
                Play(input);
                exit = true;
            }else{
                System.out.println("Please enter a valid input");
                input = scan.nextLine();
            }
        }
    }

    private  static void Play(String opponent){
        GameBoard board = new GameBoard();
        Scanner scan = new Scanner(System.in);
        int turn = 0;
        Boolean accept1 = false;
        while(board.checkWin() == false){
            //  board.print();
            System.out.println("turn: " + turn);
            System.out.println("turn$2= " + turn%2);
            if(opponent.toLowerCase().equals("ai") && turn%2 == 1)
            {
                // RandomMove(board);
                GameTreeNode TreeNode = new GameTreeNode();
                TreeNode(board.clone(), 3);
                // GameTreeNode.expandChildren(3, 0);
                // GameTreeNode.runMiniMax(false, board.clone(), 0);
                // int indice = GameTreeNode.getNodeNum();
                // int[][] spaces = board.OpenSpaces();
                board.tryPlacePiece(turn%2+1, TreeNode.getOptimalMove(board, turn, 3, true)[0], TreeNode.getOptimalMove(board, turn, 3, true)[1]);
                board.print();
            }else{
                while(accept1 == false){
                    System.out.println("Player: " + (turn%2+1));
                    System.out.println("Enter row: ");
                    String row = scan.nextLine();
                    System.out.println("Enter column: ");
                    String col = scan.nextLine();
                    if(board.tryPlacePiece(turn%2, Integer.parseInt(row), Integer.parseInt(col))){
                        board.print();
                        accept1 = true;
                    }else{
                        System.out.println("That space is invalid, please enter a new space ");
                    }
                }
            }
            turn++;
            accept1 = false;
        }
        System.out.println("Congrats Player " + ((turn-1)%2+1));
        // return board.clone();
    }

    private static void RandomMove(GameBoard board)
    {
        int row = ThreadLocalRandom.current().nextInt(0, 3);
        int col = ThreadLocalRandom.current().nextInt(0, 3);
        while(board.tryPlacePiece(1, row, col) == false)
        {
            row = ThreadLocalRandom.current().nextInt(0, 3);
            col = ThreadLocalRandom.current().nextInt(0, 3);
        }
        // board.print();
        System.out.println("Randomly moved to row: " + row);
        System.out.println("Randomly moved to col " + col);
    }
}