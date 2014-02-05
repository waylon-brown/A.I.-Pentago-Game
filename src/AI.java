import java.util.ArrayList;


public class AI {
	private GameBoard maxBoard;	//current child board of parent node with best move
	private int max = 0;		//keeps track of maximum utility given each branch from parent node
	private int depth = 0;
	
	public AI(int d)
	{
		depth = d;
		max = 0;
	}
	
	public GameBoard getBoard()
	{
		return maxBoard;
	}
	
	//using the minimax recursive algorithm to determine the next ideal move
	public int intelligentMove(int depth, GameTree currentTree, boolean maximizingPlayer)
	{
		int bestVal = 0;
		
		//leaf node of tree
		if(depth == 0)
		{
			//System.out.println("Reached leaf node with utility of " + currentTree.getBoard().getUtility() + ". Board:");
			//currentTree.getBoard().printBoard();
			return currentTree.getBoard().getUtility();
		}
		
		if(maximizingPlayer)
		{
			bestVal = Integer.MIN_VALUE;
			currentTree.populateNodeChildren();
			ArrayList<GameTree> childTrees = currentTree.getChildTrees();
			//for each child tree of current tree node
			for(int i = 0; i < childTrees.size(); i++)
			{
				//get max utility value
				int thisVal = intelligentMove(depth - 1, childTrees.get(i), false);
				if(bestVal < thisVal)
					bestVal = thisVal;
			}
			//if depth of one below parent is reached through recursion and the board has better utility than the previous best, make new board next move
			if(bestVal > max && depth == this.depth - 1)
			{
				max = bestVal;
				maxBoard = currentTree.getBoard();
			}
			return bestVal;
		}
		else	//minimizing player
		{
			bestVal = Integer.MAX_VALUE;
			currentTree.populateNodeChildren();
			ArrayList<GameTree> childTrees = currentTree.getChildTrees();
			for(int i = 0; i < childTrees.size(); i++)
			{
				int thisVal = intelligentMove(depth - 1, childTrees.get(i), true);
				if(bestVal > thisVal)
					bestVal = thisVal;
			}
			return bestVal;
		}
	}
	
	//used for testing the game before implementing AI
	public static String randomMove()
	{
		int min = 0;
		int max = 5;
		int row = min + (int)(Math.random() * ((max - min) + 1));
		int col = min + (int)(Math.random() * ((max - min) + 1));
		int rotation = 1 + (int)(Math.random() * ((8 - 1) + 1));
		String returnString = row + " " + col + " ";
		switch(rotation)
		{
		case 1:
			returnString += "A";
			break;
		case 2:
			returnString += "B";
			break;
		case 3:
			returnString += "C";
			break;
		case 4:
			returnString += "D";
			break;
		case 5:
			returnString += "a";
			break;
		case 6:
			returnString += "b";
			break;
		case 7:
			returnString += "c";
			break;
		default:
			returnString += "d";
			
		}
		
		return returnString;
	}
}
