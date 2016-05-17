package bap;

public class HelpClass {

	
	public static void printBoard(int[][] board){
		//TODO print out the board (Jonathan)
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < board.length;i++){
			for(int j = 0; j < board[1].length; j++){
				 sb.append(board[i][j]);
				
			}
			sb.append("\n");
		}
		
		 System.out.println(sb.toString());
	}
}


