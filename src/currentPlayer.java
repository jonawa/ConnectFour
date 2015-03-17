import java.awt.Graphics;
import java.awt.Graphics2D;

// represents player who's turn it is 
public class currentPlayer {
	
	// players name is either Blue or Red
	
	// initialize it with None 
	static String currentPlayer = "None";
	
	// used to change the player's name to Red or Blue throughout the game 
	public static void setPlayer(String string) {
		currentPlayer = string;
	}
	//returns whether blue or red is the current player
	public String getPlayer(){
		if (currentPlayer == "None"){
			Random();
		}
		else if (currentPlayer.equals("Blue")){
			currentPlayer = "Red";
		}
		else if (currentPlayer.equals("Red")){
			currentPlayer = "Blue";
		}
		return currentPlayer;
	}
	
	// randomly deciding which player plays first
	static void Random() {

		// randomly choosing a number between 1 or 2
		int rand = 1 + (int) (Math.random() * 2);

		// if the number is 1 then blue is player 1 and red is player 2
		if (rand == 1) {
			setPlayer("Blue");

		}
		// if the number is 2 then red is player 1 and blue is player 2
		else if (rand == 2) {
			setPlayer("Red");

		}
	}
	
}
