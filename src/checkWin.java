
public class checkWin {
	
	static int total = 0;
	
	public static int [][] duplicate (int[][]positions){
		int [][] value = new int [7][6];
		for (int i = 0; i < 7; i ++){
			for (int j = 0; j < 6; j ++){
				if (positions[i][j] == 1){
					value [i][j] = 1; // if red
				}
				else if (positions[i][j] == 2){
					value [i][j] = -1; // if blue
				}
				else { value [i][j] = 0;}
			}
		}
		return value;
	}
	public static int checkWin (int [][] positions){
		// places the array into a duplicate one
		int [][] value = duplicate(positions); // gets what is in each position
		for (int i = 0; i < 7; i ++){
			for (int j = 0; j < 6; j ++){
				//System.out.println (value[i][j]);
			}
		}
		
		// for rows
		
		// for cols		
		for (int col = 0; col < 6; col ++){
			for (int row = 2; row >= 0; row --){ // don't check last three, as that would result in out of bounds
				total = value[row][col] + value[row + 1][col] + value[row + 2][col] +value[row + 3][col]; // adds up the total
				System.out.println(total);
				if (total == 4) {return 1;} 		// if red win
				else if (total == -4) { return 2;}	// if blue win
			}
		}

		// for left diagonal
		
		// for right diagonal
		
		return 0; // if draw game
	}
}
