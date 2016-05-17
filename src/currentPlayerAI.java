import java.awt.Graphics;
import java.awt.Graphics2D;

// represents player who's turn it is 
public class currentPlayerAI {
	
	static boolean computer = false;
	static AI play = new AI();
	static int [][] board = new int [7][6];
	static String compCol, playCol;
	static int playComp, playUser, row, col;
	// players name is either Blue or Red
	
	// initialize it with None 
	private static String currentPlayer = "None";
	
	public static void reset(){
		currentPlayer = "None";
		board = new int [7][6];
	}
	
	// used to change the player's name to Red or Blue throughout the game 
	private static void setPlayer(String string) {
		currentPlayer = string;
	}
	
	public static int [][] returnBoard(){
		return board;
	}
	//returns whether blue or red is the current player
	public static String getPlayer(int [][] b){
	//	System.out.print(b[6][5]);
		if (currentPlayer == "None"){
			Random();
		}
		else if (computer == true){
		//	play.randMove(b);
			play.minimax(0,-1000000,1000000,b);
			board = play.returnBestMove(b);
			//computer = false;
			/*x = new Minimax();
			x.minFlag = true;
			x.start();
			x.interrupt();
			row = x.rowNextMove;
			col = x.colNextMove;
			x.Move(row, col, 2);
			board = x.gameBoard;*/
			currentPlayer = compCol;
		}
		else if (computer == false){
			currentPlayer = playCol;
			computer = true;
		}
		return currentPlayer;
	}
	
	// randomly deciding which player plays first
	@SuppressWarnings("static-access")
	private static void Random() {
		
	

		
	
		// randomly choosing a number between 1 or 2
		int rand = 1 + (int) (Math.random() * 2);
		rand = 1;
		System.out.println("You are player " + rand);

		// if the number is 1 then blue is player 1 and red is computer
		if (rand == 1) {
			play.setPlayer(2);
			setPlayer("Blue");
			playCol = "Blue";
			compCol = "Red";
			playUser = 1;
			playComp = 2;

		}
		// if the number is 2 then red is player 1 and blue is computer
		else if (rand == 2) {
			play.setPlayer(1);
			setPlayer("Red");
			playCol = "Red";
			compCol = "Blue";
			playUser = 2;
			playComp = 1;

		}
		
		
	}
	
	
}