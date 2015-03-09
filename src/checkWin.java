
public class checkWin {
	
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
		
		// for left diagonal
		
		// for right diagonal
		
		return 0; // if draw game
	}
}
