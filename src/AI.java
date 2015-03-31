
public class AI {
	static checkWin check = new checkWin();
	private static int [][] bestMove = {{-1,-1}};
	boolean isDraw = false;
	static int player;
	static int [][] board = new int [7][6];
	
	
	public static void setPlayer(int p){ // sets the computer to the generated player
		player = p;
	}
	

	
	public static int[][] randMove (int [][] b){
		for (int i = 0; i < 7; i ++){
			for (int j = 0; j < 6; j ++){
				if (b [i][j] ==0){
				//	System.out.println(player);
					b[i][j] = player;
					board = b;
					return board;
				}
			}
		}
		return board;
	}
	
	public static int [][] returnBoard(){
		return board;
	}
	public int [][] returnBestMove (int [][] board){
		// for each column
			for (int i = 0; i < 7; i++) {
				//for each row
				for (int j = 0; j < 6; j++) {
					if (i == bestMove[0][0] && j == bestMove[0][1]){
						board[i][j]=player;
					}
				}
			}
		return board;
	}
	
	public static void setBest (int [][] moves){
		bestMove = moves;
	}
	
	
	
	public boolean isDraw(int [][]board) {
		for(int row = 0;row < 5;row++) {
			for(int col = 0;col < 6;col++) {
				if(board[col][row] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int minimax(int depth,int alpha,int beta, int [][] board) {
		 return (maxMove(depth,alpha,beta,board));
	}
	
	public int maxMove(int depth,int alpha,int beta, int [][] board) {
		int max = -500025;
		int [][] b = board;
		int m = check.checkWin(board);
		//int m = testForWinner();
		if(m != 0) { // if someone already won
			return m;
		}
		if(depth >= 6 || isDraw(board)) { // if out of bounds or if draw game
			//return analysis();
			return m;
		}
		
		int [][] lMoves = findAllLegalMoves(board);
		
		for(int move = 0;move < 6;move++) {
			if(lMoves[move][0] == -1 || lMoves[move][1] == -1) {
				continue;
			} else {
				if (b[lMoves[move][0]][lMoves[move][1]] == 0){
				b[lMoves[move][0]][lMoves[move][1]] = 2;
				int temp = minMove(depth + 1,alpha,beta,board);
				b[lMoves[move][0]][lMoves[move][1]] = 0;
				if(temp > max) {
					max = temp;
						if(depth == 0) {
							bestMove[0][0] = lMoves[move][0];
							bestMove[0][1] = lMoves[move][1];
							setBest(bestMove);
						}
				}
				if(temp > alpha) {
				   alpha = temp; 
				}
				if(alpha >= beta) {
				   return alpha;
				}
				}
			}
		}
		return max;
	}
	
	public int minMove(int depth,int alpha,int beta, int [][]board) {
		int min = 500025;
		int b [][] = board;
		int m = check.checkWin(board);
		if(m != 0) {
			return m;
		}
		/*if(depth >= 6 || isDraw(board)) {
			return analysis();
		}
		*/
		int [][] lMoves = findAllLegalMoves(board);
		
		for(int move = 0;move < 6;move++) {
			if(lMoves[move][0] == -1 || lMoves[move][1] == -1) {
				continue;
			} else {
				if (b[lMoves[move][0]][lMoves[move][1]] == 0){
				b[lMoves[move][0]][lMoves[move][1]] = 1;
				int temp = maxMove(depth + 1,alpha,beta,board);
				b[lMoves[move][0]][lMoves[move][1]] = 0;
				if(temp < min) {
					min = temp;
					if(depth == 0) {
						bestMove[0][0] = lMoves[move][0];
						bestMove[0][1] = lMoves[move][1];
						setBest(bestMove);
					}
				}
				if(temp < beta) {
				   beta = temp;
				}
				if(alpha >= beta) {
				   return beta;
			    }
				}
			}
		}
		return min;
	}
	public int [][] findAllLegalMoves(int [][]board) {
		int [][] legalMove = new int [7][6];
		
		for (int i = 0; i <7; i ++){
			for (int j = 0; j < 6; j ++){
				legalMove [i][j] = -1;
			}
		}
		
		for(int c = 0;c < 7;c++) {
			for(int r = 5;r >= 0;r--) {
			//	System.out.println(r + " " + c);
				if(board[c][r] == 0) {
					legalMove[c][0] = r;
					legalMove[c][1] = c;
					break;
				}
			}
		}
		return legalMove;
	}
	
	// fill this in
	public int analysis() {
		int whoWon = 0;
		return whoWon;
	}
}
