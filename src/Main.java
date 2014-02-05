import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	
	//Pentago Game with AI
	//
	//Goal of the game is to make 5 marbles with your color in a row to win.
	//The game board is 6x6, but is split into 4 quadrants. For each turn,
	//you place a marble in a slot then rotate one of the quadrants any direction
	//you choose.
	//
	//The game is set up to be a human vs. an AI, but each student has their AI
	//compete against the other's by putting in the enemy AI as your "human" input
	//to see whose AI is "smartest".
	
	
	//depth of minimax GameTree, more depth level = harder AI
	private final static int depth = 2;
	
	static GameBoard board;
	static int currentPlayer = 0;
	
	//parse game move input
	private static void parseInput(String input)
	{
		String[] array = input.split(" ");
		try{
			if(board.isValidMove(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
			{
				board.makeMove(currentPlayer, Integer.parseInt(array[0]), Integer.parseInt(array[1]));
				//change current player
				if(currentPlayer == 1)
					currentPlayer = 2;
				else
					currentPlayer = 1;
				//check if winner before rotating
				if(board.determineWinner() == 0)
					board.rotate(array[2]);
				else if(board.determineWinner() == 1)
					System.out.println("Human wins. I need to work on my AI.");
				else
					System.out.println("AI wins!");
			}
			else
			{
				System.out.println("Invalid move attempted.");
				startGame();	//reset turn
			}
				
		}catch(Exception e)
		{
			System.out.println("Could not interpret input, or game is full.");
		}
	}
	
	private static void startGame()
	{		
		//Create scanner to obtain user input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        
        GameTree currentTree = new GameTree(board);        
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
				System.out.println("Computing AI move...");
				AI computer = new AI(depth);
				
				//timestamp, used to calculate run time
				long startTime = System.currentTimeMillis();	
				//computes move with best minimax utility given the depth, returns the int of the final maximum utility at head node of GameTree
				int aiInput = computer.intelligentMove(depth, currentTree, false);
				long endTime = System.currentTimeMillis();
				
				System.out.println("AI chooses the following move: " + computer.getBoard().getLastMove() + "\nwith minimax utility of: " + 
						aiInput + "\nwhich took " + (endTime-startTime) + "ms to compute.");
				parseInput(computer.getBoard().getLastMove());
				board.printBoard();
			}
			
		}
		//game is over
		if(board.determineWinner() == 1)
			System.out.println("Human wins. I need to work on my AI.");
		else
			System.out.println("AI wins!");
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
	
	public static void main(String[] args) 
	{
		board = new GameBoard();
        
//		//test utility, utility is used for minimax algorithm and represents how "good" a board is
//		board.makeMove(2, 1, 1);
//		board.makeMove(2, 1, 2);
//		board.makeMove(2, 1, 3);
//		board.makeMove(2, 3, 1);
//		board.makeMove(2, 4, 1);
//		board.makeMove(2, 6, 2);
//		board.makeMove(2, 6, 3);
//		board.makeMove(2, 6, 4);
//		System.out.println("Utility test for: ");
//		board.printBoard();
//		System.out.println("Utility is " + board.getUtility(2));
//		//////////////
		
		determineStarting();
		
		System.out.println("Game started!\n");
		board.printBoard();
		
        startGame();
	}
}
