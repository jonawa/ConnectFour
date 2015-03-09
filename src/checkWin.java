
public class checkWin {
	
	static int total = 0;
	
	public static int [][] duplicate (int[][]positions, int [][]value){
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
		int [][] value = new int [7][6];
		value = duplicate(positions,value); // gets what is in each position
		
		// for rows
		
		// for cols
		for (int col = 0; col < 7; col ++){
			for (int row = 0; row < 3; row ++){ // don't check last three, as that would result in out of bounds
				total = value[row][col] + value[row + 1][col] + value[row + 2][col] +value[row + 3][col];
				if (total == 4) {return 1;}
				else if (total == -4) { return 2;}
			}
		}
		
		// for left diagonal
		
		// for right diagonal
		
		return 0; // if draw game
	}
}
