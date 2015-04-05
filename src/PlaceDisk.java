import java.awt.Point;

// this class is used by Move or Restore 

// strictly handles the GUI of placing a disk on the board
public class PlaceDisk {

	// the radius of each disc
	private static final int DISC_RADIUS = 100;

	// 1 = red, 2 = blue
	public static int[][] place(Point p, int player, int[][] positions) {
		// move it to the lowest empty spot
		int row = -1;
		int col = (p.x - 140) / DISC_RADIUS;
		int count = row;
		while (count < 5 && positions[count + 1][col] == 0) {
			row++;
			count++;
		}

		if (player == 1)
			positions[row][col] = 1;
		else if (player == 2)
			positions[row][col] = 2;

		return positions;
	}

	public static int[][] AIPlace(Point p, int color, int[][] positions) {
		// move it to the lowest empty spot
		int row = -1;
		int col = p.y;
		int count = row;
		while (count < 5 && positions[count + 1][col] == 0) {
			row++;
			count++;
		}
		while (positions[0][col] != 0) {
			// if the column is full then randomize
			int rand = (int) (Math.random() * 7);
			col = rand;
		}

		if (color == 1)
			positions[row][col] = 1;
		else if (color == 2)
			positions[row][col] = 2;
		return positions;
	}
}