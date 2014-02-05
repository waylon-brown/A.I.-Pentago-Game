import java.util.ArrayList;

import com.sun.xml.internal.ws.util.StringUtils;


public class GameBoard {
	private int[][] slots;
	private String lastMove = "";	//move that defines this board compared to the parent of this board in the game tree
	
	public GameBoard()
	{
		slots = new int[6][6];
		blankBoard();
	}
	
	//used for copying game board moves
	public GameBoard(int[][] moves)
	{
		slots = new int[6][6];
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 6; j++)
				slots[i][j] = moves[i][j];
		}
	}
	
	public void setLastMove(String move)
	{
		lastMove = move;
	}

	public void makeMove(int player, int row, int col)
	{
		//use -1 since input is from 1-6, not 0-5
		slots[row - 1][col - 1] = player;
	}
	
	public boolean isValidMove(int row, int col)
	{
		return (slots[row - 1][col - 1] == 0);
	}
	
	//rotate matrix clockwise
	static int[][] rotateClockwise(int[][] mat) {
	    final int m = mat.length;
	    final int n = mat[0].length;
	    int[][] ret = new int[n][m];
	    for (int r = 0; r < m; r++) {
	        for (int c = 0; c < n; c++) {
	            ret[c][m-1-r] = mat[r][c];
	        }
	    }
	    return ret;
	}
		
	//rotate matrix counter-clockwise
	static int[][] rotateCounterClockwise(int[][] mat) {
	    return rotateClockwise(rotateClockwise(rotateClockwise(mat)));
	}
	
	//used for minimax algorithm to determine how "good" a gameboard is
	public int getUtility()
	{
		int utility = 0;
		int streak = 0;			//streak is used to add additional points for more than 2 in a row
								//2 in a row = 1 pt, 3 in a row = 2 pt, etc.
		
		//count horizontal doubles
		for(int i = 0; i < 6; i++)	//go down row
		{
			for(int j = 0; j < 5; j++)	//count doubles
			{
				if(slots[i][j] == 2 && slots[i][j+1] == 2)
				{
					utility += streak + 1;
					streak++;
				}
				else
					streak = 0;
			}
		}
		
		//count vertical doubles
		for(int i = 0; i < 6; i++)	//go down columns
		{
			for(int j = 0; j < 5; j++)	//count doubles
			{
				if(slots[j][i] == 2 && slots[j+1][i] == 2)
				{
					utility += streak + 1;
					streak++;
				}
				else
					streak = 0;
			}
		}
		return utility;
	}
	
	//gets all children to this board, i.e. all next possible moves in the game
	public ArrayList<GameBoard> getChildren()
	{
		ArrayList<GameBoard> returnList = new ArrayList<GameBoard>();
		
		////////////////////////////////////////////////////////////
		//do this for each orientation of each quadrant of the board
		////////////////////////////////////////////////////////////
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotateA();
					tempBoard.setLastMove(i + " " + j + " " + "A");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotateB();
					tempBoard.setLastMove(i + " " + j + " " + "B");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotateC();
					tempBoard.setLastMove(i + " " + j + " " + "C");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotateD();
					tempBoard.setLastMove(i + " " + j + " " + "D");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotatea();
					tempBoard.setLastMove(i + " " + j + " " + "a");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotateb();
					tempBoard.setLastMove(i + " " + j + " " + "b");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotatec();
					tempBoard.setLastMove(i + " " + j + " " + "c");
					returnList.add(tempBoard);
				}
			}
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 1; j < 7; j++)
			{
				//if valid move, add a board with this move to the list
				if(isValidMove(i, j))
				{
					GameBoard tempBoard = new GameBoard(slots);
					tempBoard.makeMove(2, i, j);	//use 2 as it is for the AI's move
					tempBoard.rotated();
					tempBoard.setLastMove(i + " " + j + " " + "d");
					returnList.add(tempBoard);
				}
			}
		}
		
		return returnList;
	}
	
	public void printBoard()
	{
		System.out.println("Current board:\n");
		for(int i = 0; i < slots.length; i++)
		{
			String lineString = "";
			for(int j = 0; j < slots[i].length; j++)
			{
				if(slots[i][j] == 1)
					lineString += "W ";
				else if(slots[i][j] == 2)
					lineString += "B ";
				else
					lineString += "0 ";
				if(j == 2)	//adds vertical middle split to board
					lineString += "| ";
			}
			System.out.println(lineString);
			if(i == 2)	//adds horizontal middle split to board
				System.out.println("-------------");
		}
	}
	
	private void blankBoard()
	{
		for(int i = 0; i < slots.length; i++)
		{
			for(int j = 0; j < slots[i].length; j++)
				slots[i][j] = 0;
		}
	}
	
	public String getLastMove()
	{
		return lastMove;
	}
	
	public void rotate(String key)
	{
		//rotate counter-clockwise
		if(Character.isUpperCase(key.charAt(0)))
		{
			if(key.equals("A"))
			{
				rotateA();
			}
			else if (key.equals("B"))
			{
				rotateB();
			}
			else if (key.equals("C"))
			{
				rotateC();
			}
			else if (key.equals("D"))
			{
				rotateD();
			}
		}
		else	//rotate clockwise
		{
			if(key.equals("a"))
			{
				rotatea();
			}
			else if (key.equals("b"))
			{
				rotateb();
			}
			else if (key.equals("c"))
			{
				rotatec();
			}
			else if (key.equals("d"))
			{
				rotated();
			}
		}
	}
	
	public void rotateA()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i][j];
			}
		}
		//rotate quadrant
		quadrant = rotateCounterClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i][j] = quadrant[i][j];
			}
		}
	}
	
	public void rotateB()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i][j+3];
			}
		}
		//rotate quadrant
		quadrant = rotateCounterClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i][j+3] = quadrant[i][j];
			}
		}
	}
	
	public void rotateC()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i+3][j];
			}
		}
		//rotate quadrant
		quadrant = rotateCounterClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i+3][j] = quadrant[i][j];
			}
		}
	}
	
	public void rotateD()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i+3][j+3];
			}
		}
		//rotate quadrant
		quadrant = rotateCounterClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i+3][j+3] = quadrant[i][j];
			}
		}
	}
	
	public void rotatea()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i][j];
			}
		}
		//rotate quadrant
		quadrant = rotateClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i][j] = quadrant[i][j];
			}
		}
	}
	
	public void rotateb()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i][j+3];
			}
		}
		//rotate quadrant
		quadrant = rotateClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i][j+3] = quadrant[i][j];
			}
		}
	}
	
	public void rotatec()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i+3][j];
			}
		}
		//rotate quadrant
		quadrant = rotateClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i+3][j] = quadrant[i][j];
			}
		}
	}
	
	public void rotated()
	{
		int[][] quadrant = new int[3][3];
		//set quadrant to correct values
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				quadrant[i][j] = slots[i+3][j+3];
			}
		}
		//rotate quadrant
		quadrant = rotateClockwise(quadrant);
		//put quadrant back in gameboard
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				slots[i+3][j+3] = quadrant[i][j];
			}
		}
	}
	
	//returning 0 = no winner so far
	//returning 1 = first player (white) wins
	//returning 2 = second player (black) wins
	public int determineWinner()
	{
		//trying to think of a more elegant way to do this...
		
		//first 5 of each row
		for(int i = 0; i < 6; i++)
		{
			if(slots[i][0] == 1 && slots[i][1] == 1 && slots[i][2] == 1 && slots[i][3] == 1 && slots[i][4] == 1)
				return 1;
			if(slots[i][0] == 2 && slots[i][1] == 2 && slots[i][2] == 2 && slots[i][3] == 2 && slots[i][4] == 2)
				return 2;
		}
		
		//second 5 of each row (example row: 0 W W W W W)
		for(int i = 0; i < 6; i++)
		{
			if(slots[i][1] == 1 && slots[i][2] == 1 && slots[i][3] == 1 && slots[i][4] == 1 && slots[i][5] == 1)
				return 1;
			if(slots[i][1] == 2 && slots[i][2] == 2 && slots[i][3] == 2 && slots[i][4] == 2 && slots[i][5] == 2)
				return 2;
		}
		
		//first 5 of each column
		for(int i = 0; i < 6; i++)
		{
			if(slots[0][i] == 1 && slots[1][i] == 1 && slots[2][i] == 1 && slots[3][i] == 1 && slots[4][i] == 1)
				return 1;
			if(slots[0][i] == 2 && slots[1][i] == 2 && slots[2][i] == 2 && slots[3][i] == 2 && slots[4][i] == 2)
				return 2;
		}
				
		//second 5 of each column
		for(int i = 0; i < 6; i++)
		{
			if(slots[1][i] == 1 && slots[2][i] == 1 && slots[3][i] == 1 && slots[4][i] == 1 && slots[5][i] == 1)
				return 1;
			if(slots[1][i] == 2 && slots[2][i] == 2 && slots[3][i] == 2 && slots[4][i] == 2 && slots[5][i] == 2)
				return 2;
		}
		
		/////////////
		//diagonals//
		/////////////

//		0 W 0 | 0 0 0
//		0 0 W | 0 0 0 
//		0 0 0 | W 0 0 
//		-------------
//		0 0 0 | 0 W 0 
//		0 0 0 | 0 0 W 
//		0 0 0 | 0 0 0 
		if(slots[0][1] == 1 && slots[1][2] == 1 && slots[2][3] == 1 && slots[3][4] == 1 && slots[4][5] == 1)
			return 1;
		if(slots[0][1] == 2 && slots[1][2] == 2 && slots[2][3] == 2 && slots[3][4] == 2 && slots[4][5] == 2)
			return 2;
		
//		0 0 0 | 0 0 0
//		W 0 0 | 0 0 0 
//		0 W 0 | 0 0 0 
//		-------------
//		0 0 W | 0 0 0 
//		0 0 0 | W 0 0 
//		0 0 0 | 0 W 0 
		if(slots[1][0] == 1 && slots[2][1] == 1 && slots[3][2] == 1 && slots[4][3] == 1 && slots[5][4] == 1)
			return 1;
		if(slots[1][0] == 2 && slots[2][1] == 2 && slots[3][2] == 2 && slots[4][3] == 2 && slots[5][4] == 2)
			return 2;
		
//		0 0 0 | 0 W 0
//		0 0 0 | W 0 0 
//		0 0 W | 0 0 0 
//		-------------
//		0 W 0 | 0 0 0 
//		W 0 0 | 0 0 0 
//		0 0 0 | 0 0 0 
		if(slots[0][4] == 1 && slots[1][3] == 1 && slots[2][2] == 1 && slots[3][1] == 1 && slots[4][0] == 1)
			return 1;
		if(slots[0][4] == 2 && slots[1][3] == 2 && slots[2][2] == 2 && slots[3][1] == 2 && slots[4][0] == 2)
			return 2;
		
//		0 0 0 | 0 0 0
//		0 0 0 | 0 0 W 
//		0 0 0 | 0 W 0 
//		-------------
//		0 0 0 | W 0 0 
//		0 0 W | 0 0 0 
//		0 w 0 | 0 0 0 
		if(slots[1][5] == 1 && slots[2][4] == 1 && slots[3][3] == 1 && slots[4][2] == 1 && slots[5][1] == 1)
			return 1;
		if(slots[1][5] == 2 && slots[2][4] == 2 && slots[3][3] == 2 && slots[4][2] == 2 && slots[5][1] == 2)
			return 2;
		
//		W 0 0 | 0 0 0
//		0 W 0 | 0 0 0 
//		0 0 W | 0 0 0 
//		-------------
//		0 0 0 | w 0 0 
//		0 0 0 | 0 W 0 
//		0 0 0 | 0 0 0 
		if(slots[0][0] == 1 && slots[1][1] == 1 && slots[2][2] == 1 && slots[3][3] == 1 && slots[4][4] == 1)
			return 1;
		if(slots[0][0] == 2 && slots[1][1] == 2 && slots[2][2] == 2 && slots[3][3] == 2 && slots[4][4] == 2)
			return 2;
		
//		0 0 0 | 0 0 0
//		0 W 0 | 0 0 0 
//		0 0 W | 0 0 0 
//		-------------
//		0 0 0 | w 0 0 
//		0 0 0 | 0 W 0 
//		0 0 0 | 0 0 W 
		if(slots[1][1] == 1 && slots[2][2] == 1 && slots[3][3] == 1 && slots[4][4] == 1 && slots[5][5] == 1)
			return 1;
		if(slots[1][1] == 2 && slots[2][2] == 2 && slots[3][3] == 2 && slots[4][4] == 2 && slots[5][5] == 2)
			return 2;
		
//		0 0 0 | 0 0 W
//		0 0 0 | 0 W 0 
//		0 0 0 | W 0 0 
//		-------------
//		0 0 w | 0 0 0 
//		0 w 0 | 0 0 0 
//		0 0 0 | 0 0 0 
		if(slots[0][5] == 1 && slots[1][4] == 1 && slots[2][3] == 1 && slots[3][2] == 1 && slots[4][1] == 1)
			return 1;
		if(slots[0][5] == 2 && slots[1][4] == 2 && slots[2][3] == 2 && slots[3][2] == 2 && slots[4][1] == 2)
			return 2;
		
//		0 0 0 | 0 0 0
//		0 0 0 | 0 W 0 
//		0 0 0 | W 0 0 
//		-------------
//		0 0 w | 0 0 0 
//		0 w 0 | 0 0 0 
//		w 0 0 | 0 0 0 
		if(slots[1][4] == 1 && slots[2][3] == 1 && slots[3][2] == 1 && slots[4][1] == 1 && slots[5][0] == 1)
			return 1;
		if(slots[1][4] == 2 && slots[2][3] == 2 && slots[3][2] == 2 && slots[4][1] == 2 && slots[5][0] == 2)
			return 2;
		
		
		//no winner found
		return 0;
	}
}
