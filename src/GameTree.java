import java.util.ArrayList;


public class GameTree {
	GameBoard thisBoard;
	ArrayList<GameTree> children;
	
	public GameTree(GameBoard board)
	{
		thisBoard = board;
	}
	
	//populates 'children' ArrayList with all possible next moves
	public void populateNodeChildren()
	{
		ArrayList<GameBoard> boardChildren = thisBoard.getChildren();
		children = new ArrayList<GameTree>();
		for(int i = 0; i < boardChildren.size(); i++)
		{
			children.add(new GameTree(boardChildren.get(i)));
			//thisBoard.printBoard();
			//System.out.println("Child move: " + boardChildren.get(i).getLastMove());
		}
	}
	
	public int childNumber()
	{
		return children.size();
	}
	
	////////////
	//accessors
	///////////
	
	public ArrayList<GameTree> getChildTrees()
	{
		return children;
	}
	
	public GameBoard getBoard()
	{
		return thisBoard;
	}
}
