public class GameTreeNode{
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;


    /**
     * Expands game tree to the given depth limit
     */
    public void expandChildren(int depthlimit)
    {

    }


    /**
     * Runs MINIMAX on the game tree rooted at this node
     * max is true if the MAX result is desired
     * max is false if the MIN result is desired
     * Returns the child node that the maximizes or minimizes the result
     */
    public GameTreeNode runMiniMax(boolean max){

    }

}
