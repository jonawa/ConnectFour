import java.awt.Point;
import java.awt.event.MouseEvent;

// this class is used by class Check 
// uses classes PlaceDisk and Memory

// this class handles one move
	// displaying it on game board
	// adding to array for memory needed for gravity 

public class Move {
	static int xPos;
	static int yPos;
	static currentPlayer Player = new currentPlayer();
	static PlaceDisk showDisk = new PlaceDisk();
	
	// call PlaceDisk to display the move 
	
	public static void LegalMoveMade(int x,int y) {
		// get x and y coordinates
		xPos = x;
		yPos = y;

		/*// if user clicks blue icon, set turn to blue
		if (xPos > 20 && xPos < 120 && yPos > 90 && yPos < 190) {
			Player.setPlayer("Blue");
		}
		// if user clicks red icon, set turn to red
		if (xPos > 875 && xPos < 975 && yPos > 90 && yPos < 190) {
			Player.setPlayer("Red");
		}*/
		
		
		// make sure circles are in bounds of the board
		if (xPos > 100 && xPos < 840 && yPos > 30 && yPos < 690) {
			PlaceDisk.place(new Point(xPos, yPos), Player.getPlayer());
		}
	}
	// call Memory to store the  move 
	// call PlaceDisk to display the move 
	
	// call Memory to store the  move 
}
