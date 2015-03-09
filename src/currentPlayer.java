import java.awt.Graphics;
import java.awt.Graphics2D;

// represents player who's turn it is 
public class currentPlayer {
	
	// players name is either Blue or Red
	
	// initialize it with None 
	String currentPlayer = "None";
	
	// used to change the player's name to Red or Blue throughout the game 
	public void setPlayer(String string) {
		currentPlayer = string;
	}
	//returns whether blue or red is the current player
	public String getPlayer(){
		return currentPlayer;
	}

	// my idea is that we'll use this class to control what the current player is able to do 
	
}
