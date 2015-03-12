
public class checkWin {
	
	static int total = 0;
	static int [][] winPos = new int [4][2];
	
	private static int [][] duplicate (int[][]positions){
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
	
	private static void setPos (int [][] win){
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
		for (int diagCol = 0; diagCol <2; diagCol ++){
			for (int row = 0; row <3; row ++){
				total = value[diagCol][row] + value[diagCol+1][row+1] + value[diagCol+2][row+2] + value[diagCol+3][row+3];
				if (total == 4 | total == -4){
					int [][] winPos = {{diagCol,row} , {diagCol+1,row+1} , {diagCol+2,row+2} , {diagCol+3,row+3}};
					setPos(winPos);
					if (total == 4) { return 1; } 		// if red wins
					else if (total == -4) { return 2; } // if blue wins
				}
			}
		}
		
		// two left diagonals of length 5
		int diagCol = 0;
		int row = 1;
		while (diagCol <2){
			total = value[diagCol][row] + value[diagCol+1][row+1] + value[diagCol+2][row+2] + value[diagCol+3][row+3];
			if (total == 4 | total == -4){
				int [][] winPos = {{diagCol,row} , {diagCol+1,row+1} , {diagCol+2,row+2} , {diagCol+3,row+3}};
				setPos(winPos);
				if (total == 4) { return 1; } 		// if red wins
				else if (total == -4) { return 2; } // if blue wins
			}
			diagCol++;
			row ++;
		}
		
		int diagCol2 = 2;
		int row2 = 0;
		while (diagCol2 <4){
			total = value[diagCol2][row2] + value[diagCol2+1][row2+1] + value[diagCol2+2][row2+2] + value[diagCol2+3][row2+3];
			if (total == 4 | total == -4){
				int [][] winPos = {{diagCol2,row2} , {diagCol2+1,row2+1} , {diagCol2+2,row2+2} , {diagCol2+3,row2+3}};
				setPos(winPos);
				if (total == 4) { return 1; } 		// if red wins
				else if (total == -4) { return 2; } // if blue wins
			}
			diagCol2++;
			row2 ++;
		}
		
		// two diagonals of length 4 
		int diagCol3 = 0;
		int row3 = 2;
		while (diagCol3 <4){
			total = value[diagCol2][row2] + value[diagCol2+1][row2+1] + value[diagCol2+2][row2+2] + value[diagCol2+3][row2+3];
			if (total == 4 | total == -4){
				int [][] winPos = {{diagCol2,row2} , {diagCol2+1,row2+1} , {diagCol2+2,row2+2} , {diagCol2+3,row2+3}};
				setPos(winPos);
				if (total == 4) { return 1; } 		// if red wins
				else if (total == -4) { return 2; } // if blue wins
			}
			diagCol3 += 3;
			row3 += 2;
		}
		
		// for right diagonal
		for (int col = 0; col < 4; col ++){//dont check last 3, as that would cause overflow
			for (row = 5; row >= 2; row --){ // don't check last three, as that would result in out of bounds
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
