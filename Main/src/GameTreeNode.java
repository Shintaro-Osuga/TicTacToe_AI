import java.util.*;

public class GameTreeNode extends GameBoard{
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private int MAX_DEPTH;
    private int nodeNum;
    private int depth;


    public GameTreeNode()
    {
        MAX_DEPTH = 0;
        nodeNum = 0;
    }

    public GameTreeNode(GameBoard gameBoard, int MAX_DEPTH, int depth, int nodeNum, int minimaxValue)
    {
        this.depth = depth;
        this.nodeNum = nodeNum;
        this.minimaxValue = minimaxValue;
        this.MAX_DEPTH = MAX_DEPTH;
        this.gameBoard = gameBoard;
        // this.children = new Arraylist<GameTreeNode>();
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }

    public int getNodeNum()
    {
        return nodeNum;
    }

    public int getMiniMaxValue()
    {
        return minimaxValue;
    }

    /**
     * Expands game tree to the given depth limit
     */
    public void expandChildren(int depthlimit, int nodenum)
    {
        GameBoard gameBoard = board;

        int[][] Spaces = board.OpenSpaces();
        int player = Spaces[0].length %2 + 1;

        // if(depthlimit != 0)
        // {
        //     for(int i = 0; i < Spaces[0].length; i++)
        //     {
        //         if(gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]))
        //         {
        //             children.add(i + nodenum, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]));
        //             expandChildren(depthlimit-1, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]), i+nodenum);
        //         }
        //     }
        // }

        for(int i=0; i< Spaces.length; i++)
        {
            GameBoard temp = gameBoard.tryPlacePiece(player, Spaces[i][0], Spaces[i][1]);
            GameTreeNode child = new GameTreeNode(temp, MAX_DEPTH, depthlimit, nodenum+i, temp.evaluate);
            children.add(child);
            if(depthlimit < MAX_DEPTH)
            {
                expandChildren(depthlimit-1, nodenum+i);
            }
        }
    }


    /**
     * Runs MINIMAX on the game tree rooted at this node
     * max is true if the MAX result is desired
     * max is false if the MIN result is desired
     * Returns the child node that maximizes or minimizes the result
     * index is Openspaces.length + current i
     */
    public GameTreeNode runMiniMax(boolean max, GameBoard gameBoard, int nodenum, int depth, int index, int[] pruner)
    {
        this.gameBoard = gameBoard;
        int eval = gameBoard.evaluate();
        if(eval == 10 || eval == -10)
        {
            return eval;
        }

        GameTreeNode maxboard = new GameTreeNode();
        // if(max)
        // {
        //     int best = Integer.MIN_VALUE;

        //     if(nodenum == children.size())
        //     {
        //         return children.get(nodenum).getGameBoard().evaluate();
        //     }else
        //     {
        //         for(int i = nodenum; i < gameBoard.OpenSpaces().length; i++)
        //         {
        //             int current = runMiniMax(max, gameBoard, i).getGameBoard().evaluate();
        //             if(current > best)
        //             {
        //                 maxboard = children.get(i);
        //                 best = current;
        //                 nodeNum = nodenum;
        //             }
        //         }
    
        //     }

        //     return maxboard;
        // }else{
        //     int best = Integer.MAX_VALUE;

        //     if(nodenum == children.size())
        //     {
        //         return children.get(nodenum).getGameBoard().evaluate();
        //     }else{
        //         for(int i = nodenum; i < gameBoard.OpenSpaces().length; i++)
        //         {
        //             int current = runMiniMax(max, gameBoard, i).getGameBoard().evaluate();
        //             if(runMiniMax(max, gameBoard, i).getGameBoard().evaluate() < best)
        //             {
        //                 maxboard = children.get(i);
        //                 best = current;
        //                 nodeNum = nodenum;
        //             }
        //         }
        //     }
        //     return maxboard;
        // }
    
        int[][] Spaces = gameBoard.OpenSpaces();
        if(max)
        {
            int bestval = Integer.MIN_VALUE;

            if(depth == 0)
            {
                for(int i = 0; i < Spaces.length; i++)
                {
                    if(children.get(index).minimaxValue > bestval)
                    {
                        bestval = children.get(index).minimaxValue;

                     }
                }
            }
            for(int i =0; i < Spaces.length; i++)
            {
                if(depth == 0)
                {
                    if(children.get(index).minimaxValue > bestval)
                    {
                        best = children.get(index).minimaxValue;
                    }
                }else{
                    runMiniMax(max, children.get(index).getGameBoard(), nodenum+i, depth-1, index+gameBoard.OpenSpaces());
                }
            }
        }else{
            int bestval = Integer.MAX_VALUE;

            for(int i =0; i < Spaces.length; i++)
            {
                if(depth == 0)
                {
                    if(children.get(index).getGameBoard.evaluate() < bestval)
                    {
                        best = children.get(index).getGameBoard().evaluate();
                    }
                }else{
                    runMiniMax(max, children.get(index).getGameBoard(), nodenum+i, depth-1, index+gameBoard.OpenSpaces());
                }
            }
        }

    }

    public int[] minimax(boolean max, GameBoard gameBoard, int nodenum, int depth, int index, int[] pruner)
    {
        int eval = gameBoard.evaluate();
        if(eval == 10 || eval == -10)
        {
            return eval;
        }

        GameTreeNode maxboard = new GameTreeNode();
        int[][] Spaces = gameBoard.OpenSpaces();

        for(int i = 0; i < Spaces.length; i++)
        {
            if(depth != 0)
            {
                minimax(max, children.get(index+i), i, depth-1, index+Spaces.length, pruner);
            }

            
        }
    }



    public int[] getOptimalMove(GameBoard board, int turn, int MAX_DEPTH, boolean max)
    {
        this.MAX_DEPTH = MAX_DEPTH;
        expandChildren(MAX_DEPTH, 0);

        GameTreeNode node = runMiniMax(max, board, 0);

        int index = nodeNum;
        int[][] spaces = board.OpenSpaces();
        int[] OptimalMove = new int[]{spaces[index][0], spaces[index][1]};
        return OptimalMove;
    }


}
