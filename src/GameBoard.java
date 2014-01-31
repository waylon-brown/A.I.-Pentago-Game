
public class GameBoard {
	boolean[][] slots;
	
	public GameBoard()
	{
		blankBoard();
	}
	
	public void printBoard()
	{
		for(int i = 0; i < slots.length; i++)
		{
			String lineString = "";
			for(int j = 0; j < slots[i].length; j++)
			{
				if(slots[i][j])
					lineString += "1 ";
				else 
					lineString += "0 ";
			}
			System.out.println(lineString);
		}
	}
	
	private void blankBoard()
	{
		for(int i = 0; i < slots.length; i++)
		{
			for(int j = 0; j < slots[i].length; j++)
				slots[i][j] = false;
		}
	}
}
