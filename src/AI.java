
public class AI {
	GameBoard board;
	
	public AI(GameBoard board)
	{
		this.board = board;
	}
	
	//used for testing the game before implementing AI
	public String randomMove()
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
