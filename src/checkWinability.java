// this class is used by Check not

// if it is no longer possible to win the game, game over 
public class checkWinability {
	
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

	static boolean check(int [][] positions) {
				
		int [][] value = duplicate(positions);
		
		// for rows
				for (int row = 5; row >= 0; row --){
					for (int col = 0; col < 4; col ++){ // don't check last three, as that would result in out of bounds
						if (value[col][row] == value[col + 1][row] || 0 == value[col + 1][row] ){
							if (value[col][row] == value[col + 2][row] || 0 == value[col + 2][row] ){
								if (value[col][row] == value[col + 3][row] || 0 == value[col + 3][row] ){
									return true;
								}
							}
						}
					}
				}
				
				// for cols		
				for (int col = 0; col < 7; col ++){
					for (int row = 2; row >= 0; row --){ // don't check last three, as that would result in out of bounds
						if (value[col][row] == value[col][row + 1] || 0 == value[col][row + 1] ){
							if (value[col][row] == value[col][row + 2] || 0 == value[col][row + 2] ){
								if (value[col][row] == value[col][row + 3] || 0 == value[col][row + 3] ){
									return true;
								}
							}
						}
					}
				}

				// for left diagonal
				for (int col = 6; col > 2; col --){// don't check last 3, as that would cause overflow
					for (int row = 5; row > 2; row --){ // don't check last three, as that would result in out of bounds
						if (value[col][row] == value[col - 1][row- 1] || 0 == value[col- 1][row- 1] ){
							if (value[col][row] == value[col - 2][row - 2] || 0 == value[col - 2][row - 2] ){
								if (value[col][row] == value[col -3][row-3] || 0 == value[col - 3][row-3] ){
									return true;
								}
							}
						}
					}
				}		

				// for right diagonal
				for (int col = 0; col < 4; col ++){//dont check last 3, as that would cause overflow
					for (int row = 5; row > 2; row --){ // don't check last three, as that would result in out of bounds
						if (value[col][row] == value[col + 1][row- 1] || 0 == value[col+ 1][row- 1] ){
							if (value[col][row] == value[col + 2][row - 2] || 0 == value[col + 2][row - 2] ){
								if (value[col][row] == value[col +3][row-3] || 0 == value[col + 3][row-3] ){
									return true;
								}
							}
						}
					}
				}
		
		return false;
	}

}
