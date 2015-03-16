// this class is used by Check not

// if it is no longer possible to win the game, game over 
public class checkWinability {
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

	private boolean checkWinability(int [][] positions) {
		
		int [][] value = duplicate(positions);
		
		for (int col = 0; col < 7; col ++){
			for(int row = 0; row <6; row ++){				
				if (col<4){
					if (row<3){
						if (value[col+1][row+1] == value[col][row] || value[col+1][row+1] == 0) {
							if (value[col+2][row+2] == value[col][row] || value[col+2][row+2] == 0) {
								if (value[col+3][row+3] == value[col][row] || value[col+3][row+3] == 0) {
									return true; // return true if not game over
								}
							}
						}
					}
					if (row>2){
						if (value[col+1][row-1] == value[col][row] || value[col+1][row-1] == 0) {
							if (value[col+2][row-2] == value[col][row] || value[col+2][row-2] == 0) {
								if (value[col+3][row-3] == value[col][row] || value[col+3][row-3] == 0) {
									return true; // return true if not game over
								}
							}
						}
					}
				}
				else {
					if (value[col][row+1] == value[col][row] || value[col][row+1] == 0) {
						if (value[col][row+2] == value[col][row] || value[col][row+2] == 0) {
							if (value[col][row+3] == value[col][row] || value[col][row+3] == 0) {
								return true; // return true if not game over
							}
						}
					}
				}
			}
		}
		return false; 
	}
}
