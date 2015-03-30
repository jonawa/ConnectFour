import java.awt.Point;

// this class is used by Move or Restore 

// strictly handles the GUI of placing a disk on the board
public class PlaceDisk {
	
	// the radius of each disc
	private static final int DISC_RADIUS = 100;
	
	// must keep track of positions of the discs
	static int[][] positions = new int[7][6];
	
	public static void updateArray(int[][] restore){
		positions = restore;
	}

	public static void place(Point p, String player) {
		if (player == "Red")
			positions[(p.x - 140) / DISC_RADIUS][(p.y - 90) / DISC_RADIUS] 
					= 1;
		else if (player == "Blue")
			positions[(p.x - 140) / DISC_RADIUS][(p.y - 90) / DISC_RADIUS] 
					= 2;

	}
}