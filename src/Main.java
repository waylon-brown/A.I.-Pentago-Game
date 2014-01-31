import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	
	static GameBoard board;
	static int currentPlayer = 0;
	
	private static void parseInput(String input)
	{
		String[] array = input.split(" ");
		try{
			board.makeMove(currentPlayer, Integer.parseInt(array[0]), Integer.parseInt(array[1]));
			if(currentPlayer == 1)
				currentPlayer = 2;
			else
				currentPlayer = 1;
			//check if winner before rotating
			if(board.determineWinner() == 0)
				board.rotate(array[2]);
			else if(board.determineWinner() == 1)
				System.out.println("Player 1 wins!");
			else
				System.out.println("Player 2 wins!");
				
		}catch(Exception e)
		{
			System.out.println("Could not interpret input.");
		}
	}
	
	private static void startGame()
	{
		
		//Create scanner to obtain user input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        
        //computer player
        AI ai = new AI(board);
        
		//while no current winner
		while(board.determineWinner() == 0)
		{
			//if user is going
			if(currentPlayer == 1)
			{
				System.out.println("Enter <row> <col> <rotation>:");
				 try {
					 input = in.readLine();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
				 
				 if(input != null)
				 {
					 parseInput(input);
					 board.printBoard();
				 }
			}
			else	//if AI is going
			{
				String aiInput = ai.randomMove();
				System.out.println("AI chooses the following move: " + aiInput);
				parseInput(aiInput);
				board.printBoard();
			}
			
		}
		//game is over
		if(board.determineWinner() == 1)
			System.out.println("Player 1 wins!");
		else
			System.out.println("Player 2 wins!");
	}
	
	private static void determineStarting()
	{
		System.out.println("Are you or the AI going first? (1 = you, 2 = AI)");
		//Create scanner to obtain user input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
		//get starting player
        while(currentPlayer == 0 || currentPlayer > 2)
        {
        	try {
    			input = in.readLine();
    			currentPlayer = Integer.parseInt(input);
    		}
    		catch(Exception e)
    		{
    			System.out.println("You must enter an integer.");
    		}
        }
	}
	
	public static void main(String[] args) {
		board = new GameBoard();
        
		determineStarting();
		
		System.out.println("Game started!\n");
		board.printBoard();
		
        startGame();
        
	}
}
