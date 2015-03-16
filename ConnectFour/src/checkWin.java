// this class is used by Check
// check if the current board has four disks in a row od the same colour
public class checkWin {
	
	static int total = 0;
	static int [][] winPos = new int [4][2];
	
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
	
	public static int [][] getPos (){
		return winPos;
	}
	
	public static void setPos (int [][] win){
		winPos = win;
	}
	
	public static int checkWin (int [][] positions){
		// places the array into a duplicate one
		int [][] value = duplicate(positions); // gets what is in each position
		
		// for rows
		for (int row = 5; row >= 0; row --){
			for (int col = 0; col < 3; col ++){ // don't check last three, as that would result in out of bounds
				total = value[row][col+1] + value[row][col] + value[row][col+2] +value[row][col +3]; // adds up the total
				if (total == 4 | total == -4){
					int [] [] winPos = {{col, row}, {col+1, row},{col+2, row},{col+3, row}};
					setPos(winPos);
					if (total == 4) {return 1;} 		// if red win
					else if (total == -4) { return 2;}	// if blue win
				}
			}
		}
		
		// for cols		
		for (int col = 0; col < 6; col ++){
			for (int row = 2; row >= 0; row --){ // don't check last three, as that would result in out of bounds
				total = value[row][col] + value[row + 1][col] + value[row + 2][col] +value[row + 3][col]; // adds up the total
				if (total == 4 | total == -4){
					int [] [] winPos = {{col, row}, {col, row + 1},{col, row + 2},{col, row+3}};
					setPos(winPos);
					if (total == 4) {return 1;} 		// if red win
					else if (total == -4) { return 2;}	// if blue win
				}
			}
		}

		// for left diagonal
		for (int col = 5; col > 2; col --){// don't check last 3, as that would cause overflow
			for (int row = 5; row > 2; row --){ // don't check last three, as that would result in out of bounds
				total = value[row][col] + value[row - 1][col - 1] + value[row - 2][col - 2] +value[row - 3][col - 3]; // adds up the total
				if (total == 4 | total == -4){
					int [] [] winPos = {{col, row}, {col - 1, row - 1},{col - 2, row - 2},{col - 3, row - 3}};
					setPos(winPos);
					if (total == 4) {return 1;} 		// if red win
					else if (total == -4) { return 2;}	// if blue win
				}
			}
		}		

		// for right diagonal
		for (int col = 0; col < 4; col ++){//dont check last 3, as that would cause overflow
			for (int row = 5; row > 2; row --){ // don't check last three, as that would result in out of bounds
				total = value[row][col] + value[row - 1][col + 1] + value[row - 2][col + 2] +value[row - 3][col + 3]; // adds up the total
				if (total == 4 | total == -4){
					int [] [] winPos = {{col, row}, {col + 1, row - 1},{col + 2, row - 2},{col + 3, row - 3}};
					setPos(winPos);
					if (total == 4) {return 1;} 		// if red win
					else if (total == -4) { return 2;}	// if blue win
				}
			}
		}
		
		return 0; // if draw game
	}
}
