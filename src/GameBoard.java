import com.sun.xml.internal.ws.util.StringUtils;


public class GameBoard {
	int[][] slots;
	
	public GameBoard()
	{
		slots = new int[6][6];
		blankBoard();
	}
	
	public void makeMove(int player, int row, int col)
	{
		//use +1 since input is from 1-6, not 0-5
		slots[row - 1][col - 1] = player;
	}
	
	public void rotate(String key)
	{
		//rotate counter-clockwise
		if(Character.isUpperCase(key.charAt(0)))
		{
			if(key.equals("A"))
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
			else if (key.equals("B"))
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
			else if (key.equals("C"))
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
			else if (key.equals("D"))
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
		}
		else	//rotate clockwise
		{
			if(key.equals("a"))
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
			else if (key.equals("b"))
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
			else if (key.equals("c"))
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
			else if (key.equals("d"))
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
		}
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
