import java.util.*;

public class GameTreeNode extends GameBoard{
    // private List<GameTreeNode> children;
    private GameBoard gameBoard;

    private int MAX_DEPTH;

    public int nodeNum;

    private int depth;
    public int index;
    public int eval;

    public int alpha;
    public int beta;

    public GameTreeNode head;
    public ArrayList<GameTreeNode> children;
    public GameTreeNode previous;

    public int[] move = new int[2];

    public GameTreeNode()
    {
        MAX_DEPTH = 3;
        nodeNum = 0;
    }

    public GameTreeNode(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        MAX_DEPTH = 3;
        nodeNum = 0;
    }

    public GameTreeNode(GameBoard gameBoard, int MAX_DEPTH)
    {
        GameTreeNode head = new GameTreeNode(gameBoard);
        // children = new ArrayList<GameTreeNode>();
        this.head = head;
        children = new ArrayList<GameTreeNode>();
        // children.add(head);
        System.out.println("first constructor");
        this.MAX_DEPTH = MAX_DEPTH;
        this.gameBoard = gameBoard;
    }

    public GameTreeNode(GameBoard gameBoard, int index, int depth, int eval, int MAX_DEPTH)
    {
        GameTreeNode head = new GameTreeNode(gameBoard);
        this.head = head;
        children = new ArrayList<GameTreeNode>();
        // children.add(head);
        this.gameBoard = gameBoard;
        this.index = index;
        this.depth = depth;
        this.eval = eval;
        this.MAX_DEPTH = MAX_DEPTH;
        alpha = Integer.MAX_VALUE;
        beta = Integer.MIN_VALUE;
        
        // System.out.println("second constructor");
    }
    
    public GameTreeNode(GameBoard gameBoard, int depth, int index, int eval)
    {
        GameTreeNode head = new GameTreeNode(gameBoard);
        this.head = head;
        children = new ArrayList<GameTreeNode>();
        children.add(head);
        this.gameBoard = gameBoard;
        this.index = index;
        this.depth = depth;
        this.eval = eval;
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



        public void setMove(int row, int col)
        {
            move[0] = row;
            move[1] = col;
        }

    /* Expand Children */

    public void expandChildren(int depth)
    {
        GameBoard board = gameBoard.clone();

        int[][] Spaces = board.OpenSpaces();
        // int player = Spaces[0].length %2 + 1;
        int player = (depth+1)%2;

        
        for(int i=0; i < Spaces.length; i++)
        {
            GameBoard temp = board.clone();
            temp.tryPlacePiece(player, Spaces[i][0], Spaces[i][1]);
            GameTreeNode node = new GameTreeNode(temp, i, depth, temp.evaluate(), MAX_DEPTH);
            // node.children.add(node);
            node.setMove(Spaces[i][0], Spaces[i][1]);
            children.add(node);
            // if(node.move[0] == 0 && node.move[1] == 0)
            // {
                // }
                // System.out.println("row: " + node.move[0] + " col: " + node.move[1]);
            // if(node.eval >= 10)
            // {
            //     System.out.println("eval: " + node.eval);
            //     System.out.println("depth: " + depth + ": iter " + i);
            // }
            // node.gameBoard.print();
            // node.gameBoard.printInt();
            if(depth < MAX_DEPTH)
            {
                children.get(i).expandChildren(depth+1);
            }
            // if(depth == 3 & i == 0)
            // {
            //     node.gameBoard.print();
            // }
            
        }
    }

    public GameTreeNode runMiniMax2(boolean max)
    {
        if(eval == 10 || eval == -10)
        {
            return head;
        }

        int[][] Spaces = gameBoard.OpenSpaces();
        GameTreeNode bestNode = new GameTreeNode();

        if(max)
        {
            int best = Integer.MIN_VALUE;

            if(depth == 3)
            {
                if(eval > best)
                {
                    best = eval;
                    bestNode = head;
                }
            }else{
                for(int i = 0; i < Spaces.length; i++)
                {
                    if(children.get(i).depth < MAX_DEPTH)
                    {
                        bestNode = children.get(i).runMiniMax(false);
                        if(bestNode.eval > best)
                        {
                            best = bestNode.eval;
                        }
                    }else{
                        if(children.get(i).eval > best)
                        {
                            best = children.get(i).eval;
                            bestNode = children.get(i);
                        }
                    }
                }
            }
            bestNode.alpha = best;
            return bestNode;
        }

        if(!max)
        {
            int best = Integer.MAX_VALUE;
            if(depth == 3)
            {
                if(eval < best)
                {
                    best = eval;
                    bestNode = head;
                }
            }else{
                for(int i =0; i < Spaces.length; i++)
                {
                    if(children.get(i).depth < MAX_DEPTH)
                    {
                        bestNode = children.get(i).runMiniMax(true);
                        if(bestNode.eval < best)
                        {
                            best = bestNode.eval;
                        }
                    }else{
                        if(children.get(i).eval < best)
                        {
                            best = children.get(i).eval;
                            bestNode = children.get(i);
                        }
                    }
                }
            }
            bestNode.beta = best;
            return bestNode;
        }

        return bestNode;
    }

    public GameTreeNode runMiniMax(boolean max)
    {
        if(eval >= 99 || eval <= -101)
        {
            return head;
        }

        int[][] Spaces = gameBoard.OpenSpaces();
        GameTreeNode bestNode = new GameTreeNode();

        if(max)
        {
            int best = Integer.MIN_VALUE;
            
            for(int i = 0; i < Spaces.length; i++)
            {
                if(children.get(i).eval < best)
                {
                    bestNode = children.get(i);
                    best = children.get(i).eval;
                    break;
                }else if(depth+1 == MAX_DEPTH)
                {
                    if(children.get(i).eval > best)
                    {
                        best = children.get(i).eval;
                        bestNode = children.get(i);
                    }
                }else{
                    GameTreeNode temp = children.get(i).runMiniMax2(!max);
                    if(temp.eval > best)
                    {
                        best = temp.eval;
                        bestNode = temp;
                    }
                }
            }
            bestNode.alpha = best;
            return bestNode;
            
        }else{
            int best = Integer.MAX_VALUE;
            
            for(int i = 0; i< Spaces.length; i++)
            {
                if(children.get(i).eval > best)
                {
                    bestNode = children.get(i);
                    best = children.get(i).eval;
                    break;
                }else if(depth+1 == MAX_DEPTH)
                {
                    if(children.get(i).eval < best)
                    {
                        best = children.get(i).eval;
                        bestNode = children.get(i);
                    }
                }else{
                    GameTreeNode temp = children.get(i).runMiniMax2(max);
                    if(temp.eval < best)
                    {
                        best = temp.eval;
                        bestNode = temp;
                    }
                }
            }
            bestNode.beta = best;
            return bestNode;
        }
    }

        /**
     * Expands game tree to the given depth limit
     */
    // public void expandChildren(int depthlimit, int nodenum)
    // {
    //     GameBoard board = gameBoard;

    //     int[][] Spaces = board.OpenSpaces();
    //     int player = Spaces[0].length %2 + 1;

    //     // if(depthlimit != 0)
    //     // {
    //     //     for(int i = 0; i < Spaces[0].length; i++)
    //     //     {
    //     //         if(gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]))
    //     //         {
    //     //             children.add(i + nodenum, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]));
    //     //             expandChildren(depthlimit-1, gameBoard.clone().tryPlacePiece(player, Spaces[i][0], Spaces[i][1]), i+nodenum);
    //     //         }
    //     //     }
    //     // }

    //     for(int i=0; i< Spaces.length; i++)
    //     {
    //         GameBoard temp = gameBoard.tryPlacePiece(player, Spaces[i][0], Spaces[i][1]);
    //         GameTreeNode child = new GameTreeNode(temp, MAX_DEPTH, depthlimit, nodenum+i, temp.evaluate);
    //         children.add(child);
    //         if(depthlimit < MAX_DEPTH)
    //         {
    //             expandChildren(depthlimit-1, nodenum+i);
    //         }
    //     }
    // }


    /**
     * Runs MINIMAX on the game tree rooted at this node
     * max is true if the MAX result is desired
     * max is false if the MIN result is desired
     * Returns the child node that maximizes or minimizes the result
     * index is Openspaces.length + current i
     */
    // public GameTreeNode runMiniMax(boolean max, GameBoard gameBoard, int nodenum, int depth, int index)
    // {
    //     this.gameBoard = gameBoard;
    //     int eval = gameBoard.evaluate();
    //     if(eval == 10 || eval == -10)
    //     {
    //         return eval;
    //     }

    //     GameTreeNode maxboard = new GameTreeNode();
    
    //     int[][] Spaces = gameBoard.OpenSpaces();
    //     if(max)
    //     {
    //         int bestval = Integer.MIN_VALUE;

    //         if(depth == 0)
    //         {
    //             for(int i = 0; i < Spaces.length; i++)
    //             {
    //                 if(children.get(index).minimaxValue > bestval)
    //                 {
    //                     bestval = children.get(index).minimaxValue;

    //                  }
    //             }
    //         }
    //         for(int i =0; i < Spaces.length; i++)
    //         {
    //             if(depth == 0)
    //             {
    //                 if(children.get(index).minimaxValue > bestval)
    //                 {
    //                     best = children.get(index).minimaxValue;
    //                 }
    //             }else{
    //                 runMiniMax(max, children.get(index).getGameBoard(), nodenum+i, depth-1, index+gameBoard.OpenSpaces().length);
    //             }
    //         }
    //     }else{
    //         int bestval = Integer.MAX_VALUE;

    //         for(int i =0; i < Spaces.length; i++)
    //         {
    //             if(depth == 0)
    //             {
    //                 if(children.get(index).getGameBoard.evaluate() < bestval)
    //                 {
    //                     best = children.get(index).getGameBoard().evaluate();
    //                 }
    //             }else{
    //                 runMiniMax(max, children.get(index).getGameBoard(), nodenum+i, depth-1, index+gameBoard.OpenSpaces().length);
    //             }
    //         }
    //     }

    // }

    // public int[] minimax(boolean max, GameBoard gameBoard, int nodenum, int depth, int index, int[] pruner)
    // {
    //     int eval = gameBoard.evaluate();
    //     if(eval == 10 || eval == -10)
    //     {
    //         return eval;
    //     }

    //     GameTreeNode maxboard = new GameTreeNode();
    //     int[][] Spaces = gameBoard.OpenSpaces();

    //     for(int i = 0; i < Spaces.length; i++)
    //     {
    //         if(depth != 0)
    //         {
    //             minimax(max, children.get(index+i), i, depth-1, index+Spaces.length, pruner);
    //         }

            
    //     }
    // }



    // public int[] getOptimalMove(GameBoard board, int turn, int MAX_DEPTH, boolean max)
    // {
    //     this.MAX_DEPTH = MAX_DEPTH;
    //     expandChildren(MAX_DEPTH, 0);

    //     GameTreeNode node = runMiniMax(max, board, 0, 0, 0);

    //     int index = nodeNum;
    //     int[][] spaces = board.OpenSpaces();
    //     int[] OptimalMove = new int[]{spaces[index][0], spaces[index][1]};
    //     return OptimalMove;
    // }

}
