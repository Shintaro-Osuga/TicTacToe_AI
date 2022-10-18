import java.util.*;

public class GameTreeNode{
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private int MAX_DEPTH = 3;


    public GameTreeNode(GameBoard gameBoard, int MAX_DEPTH)
    {
        this.MAX_DEPTH = MAX_DEPTH;
        this.gameBoard = gameBoard;
        this.children = new Arraylist<GameTreeNode>();
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }


    /**
     * Expands game tree to the given depth limit
     */
    public void expandChildren(int depthlimit, int nodenum)
    {
        gameBoard = board;

        int[][] Spaces = board.OpenSpaces();
        int player = Spaces[0].length %2 + 1;

        if(depthlimit != 0)
        {
            for(int i = 0; i < Spaces[0].length; i++)
            {
                if(gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]))
                {
                    children.add(i + nodenum, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]));
                    expandChildren(depthlimit-1, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]), i+nodenum);
                }
            }
        }

    }


    /**
     * Runs MINIMAX on the game tree rooted at this node
     * max is true if the MAX result is desired
     * max is false if the MIN result is desired
     * Returns the child node that maximizes or minimizes the result
     */
    public GameTreeNode runMiniMax(boolean max, GameBoard gameBoard, int nodenum)
    {
        this.gameBoard = gameBoard;
        int eval = gameBoard.evaluate();
        if(eval == 10 || eval == -10)
        {
            return eval;
        }

        GameTreeNode maxboard = new GameTreeNode();
        if(max)
        {
            int best = Integer.MIN_VALUE;

            if(nodenum == children.size())
            {
                return children.get(nodenum).getGameBoard().evaluate();
            }else
            {
                for(int i = nodenum; i < gameBoard.OpenSpaces().length; i++)
                {
                    int current = runMiniMax(max, gameBoard, i).getGameBoard().evaluate();
                    if(current > best)
                    {
                        maxboard = children.get(i);
                        best = current;
                    }
                }
    
            }

            return maxboard;
        }else{
            int best = Integer.MAX_VALUE;

            if(nodenum == children.size())
            {
                return children.get(nodenum).getGameBoard().evaluate();
            }else{
                for(int i = nodenum; i < gameBoard.OpenSpaces().length; i++)
                {
                    int current = runMiniMax(max, gameBoard, i).getGameBoard().evaluate();
                    if(runMiniMax(max, gameBoard, i).getGameBoard().evaluate() < best)
                    {
                        maxboard = children.get(i);
                        best = current;
                    }
                }
            }
            return maxboard;
        }
    }


}
