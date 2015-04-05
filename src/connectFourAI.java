import java.awt.Point;

public class connectFourAI {

	// 1 is red, 2 is blue
	private int AICol = -1;
	private int playerCol = -1;

	// default constructor
	public connectFourAI(int playerCol, int AICol) {
		this.AICol = AICol;
		this.playerCol = playerCol;
	}

	// set the colors of AI and Player
	public void setCol(int playerCol, int AICol) {
		this.AICol = AICol;
		this.playerCol = playerCol;
	}

	// this method finds the best move
	// for the AI to make. it goes from
	// the worst move to the best for
	// itself to make, then checks the best
	// move in order to block the player.
	// the method is ordered in the order of
	// priority, with highest priority last
	// as it will change "move" last.
	public Point bestMove(int[][] board) {

		Point best = new Point(-1, -1);

		best = blockThrees(board);
		if (!best.equals(new Point(-1, -1)) && board[best.x][best.y] == 0)
			return best;
		else
			best = new Point(-1, -1);

		best = AIThrees(board);
		if (!best.equals(new Point(-1, -1)) && board[best.x][best.y] == 0)
			return best;
		else
			best = new Point(-1, -1);

		best = blockTwos(board);
		if (!best.equals(new Point(-1, -1)) && board[best.x][best.y] == 0)
			return best;
		else
			best = new Point(-1, -1);

		best = AITwos(board);
		if (!best.equals(new Point(-1, -1)) && board[best.x][best.y] == 0)
			return best;
		else
			best = new Point(-1, -1);

		best = makePair(board);

		return best;
	}

	// makes pairs of spaces when
	// there are no better moves
	public Point makePair(int[][] board) {
		Point move = new Point(-1, -1);

		for (int row = 5; row >= 0; row--) {
			for (int col = 0; col < 7; col++) {
				// start from worst option and
				// go to best option

				if (row > 0) {
					// verticals
					if (board[row][col] == AICol && board[row - 1][col] == 0) {
						move = new Point(row - 1, col);
					}

					// diagonals
					if (col > 0)
						if (board[row][col] == AICol
								&& board[row - 1][col - 1] == 0
								&& board[row][col - 1] != 0)
							move = new Point(row - 1, col - 1);
					if (col < 6)
						if (board[row][col] == AICol
								&& board[row - 1][col + 1] == 0
								&& board[row][col + 1] != 0)
							move = new Point(row - 1, col + 1);
				}

				// horizontals, always
				// build towards empty space
				if (board[row][col] == AICol) {
					int left = 0, right = 0, index = col;
					while (index > 0 && board[row][index] == 0) {
						left++;
						index--;
					}
					index = col;
					while (index < 6 && board[row][index] == 0) {
						right++;
						index++;
					}
					if (left >= right) {
						move = new Point(row, col - 1);
					} else
						move = new Point(row, col + 1);
				}
			}
		}

		// just in case none of the methods
		// return a better case, just select
		// an arbitrary spot
		if (move.equals(new Point(-1, -1))) {
			int col = 3;
			while (board[0][col] != 0) {
				col++;
				if (col == 6)
					col = 0;
			}
			move = new Point(5, col);
		}

		return move;
	}

	// blocks scenarios where there are 3
	// filled spaces out of 4 in a row
	public Point blockThrees(int[][] board) {
		Point move = new Point(-1, -1);
		Point temp = new Point(-1, -1);

		// check horizontals
		// count from the bottom row upwards
		for (int row = 5; row >= 0; row--){
			for (int col = 0; col < 4; col++){
				int count = 0;
				for (int index = 0; index < 4; index++){
					if(board[row][col+index] == playerCol){
						count++;
					}
					else if (board[row][col+index] == AICol){
						count--;
					}
					else if (board[row][col+index] == 0){
						temp = new Point(row, col+index);
					}
				}
				System.out.println(count);
				if (count == 3){
					return new Point(temp.x, temp.y);
				}
				count = 0;
			}
		}
		
		// check for 3 in a row verticals
		for (int col = 0; col < 7; col++) {
			// only need to check bottom 3 rows
			for (int row = 5; row >= 3; row--) {
				if (board[row][col] == playerCol
						&& board[row - 1][col] == playerCol
						&& board[row - 2][col] == playerCol
						&& board[row - 3][col] == 0) {
					return new Point(row - 3, col);
				}
			}
		}

		// check for 3/4 diagonals
		for (int row = 5; row >= 3; row--) {
			for (int col = 0; col < 7; col++) {
				// only need to check bottom 3 rows
				// need to check the diagonals PLUS the
				// ones directly below, so it is a valid move
				// to the left
				if (col <= 3) {
					int count = 0;
					for (int i = 0; i < 4; i++) {
						if (board[row - i][col + i] == playerCol) {
							count++;
						} else if (board[row - i][col + i] == 0) {
							if (row != 5) {
								if (board[row - i + 1][col + i] != 0
										&& board[row - i][col + i] == 0)
									temp = new Point(row - i, col + i);
							} else if (board[row - i][col + i] == 0)
								temp = new Point(row - i, col + i);
						}
					}
					if (count == 3 && !move.equals(new Point(-1, -1))
							&& board[temp.x][temp.y] == 0) {
						return new Point(temp.x, temp.y);
					} else if (count == 3 && board[row - 3][col + 3] == 0) {
						return new Point(row - 3, col + 3);
					}
					count = 0;
				}
				if (col >= 3) {
					int count = 0;
					for (int i = 0; i < 4; i++) {
						if (board[row - i][col - i] == playerCol) {
							count++;
						} else if (board[row - i][col - i] == 0) {
							if (row != 5) {
								if (board[row - i + 1][col - i] != 0
										&& board[row - i][col - i] == 0)	
									temp = new Point(row - i, col - i);
							} else if (board[row - i][col - i] == 0)
								temp = new Point(row - i, col - i);
						}
					}
					if (count == 3 && !move.equals(new Point(-1, -1))
							&& board[temp.x][temp.y] == 0) {
						return new Point(temp.x, temp.y);
					} else if (count == 3 && board[row - 3][col - 3] == 0) {
						return new Point(row - 3, col - 3);
					}
					count = 0;
				}
			}
		}

		return move;
	}

	// places the winning tile where there
	// are 3 filled spaces out of 4 in a row
	public Point AIThrees(int[][] board) {
		Point move = new Point(-1, -1);
		Point temp = new Point(-1, -1);

		// check horizontals
		// count from the bottom row upwards
		for (int row = 5; row >= 0; row--){
			for (int col = 0; col < 4; col++){
				int count = 0;
				for (int index = 0; index < 4; index++){
					if(board[row][col+index] == AICol){
						count++;
					}
					else if (board[row][col] == playerCol){
						count--;
					}
					else if (board[row][col+index] == 0){
						temp = new Point(row, col+index);
					}
				}
				if (count == 3){
					return new Point(temp.x, temp.y);
				}
				count = 0;
			}
		}

		// check for 3 in a row verticals
		for (int col = 0; col < 7; col++) {
			// only need to check bottom 3 rows
			for (int row = 5; row >= 3; row--) {
				if (board[row][col] == AICol && board[row - 1][col] == AICol
						&& board[row - 2][col] == AICol
						&& board[row - 3][col] == 0) {
					return new Point(row - 3, col);
				}
			}
		}

		// check for 3/4 diagonals
		for (int row = 5; row >= 3; row--) {
			for (int col = 0; col < 7; col++) {
				// only need to check bottom 3 rows
				// need to check the diagonals PLUS the
				// ones directly below, so it is a valid move
				if (col <= 3) {
					int count = 0;
					for (int i = 0; i < 4; i++) {
						if (board[row - i][col + i] == AICol) {
							count++;
						} else if (board[row - i][col + i] == 0) {
							if (row != 5) {
								if (board[row - i + 1][col + i] != 0
										&& board[row - i][col + i] == 0)
									temp = new Point(row - i, col + i);
							} else if (board[row - i][col + i] == 0)
								temp = new Point(row - i, col + i);
						}
					}
					if (count == 3 && !move.equals(new Point(-1, -1))
							&& board[temp.x][temp.y] == 0) {
						return new Point(temp.x, temp.y);
					} else if (count == 3 && board[row - 3][col + 3] == 0) {
						return new Point(row - 3, col + 3);
					}
					count = 0;
				}
				if (col >= 3) {
					int count = 0;
					for (int i = 0; i < 4; i++) {
						if (board[row - i][col - i] == AICol) {
							count++;
						} else if (board[row - i][col - i] == 0) {
							if (row != 5) {
								if (board[row - i + 1][col - i] != 0
										&& board[row - i][col - i] == 0)
									temp = new Point(row - i, col - i);
							} else if (board[row - i][col - i] == 0)
								temp = new Point(row - i, col - i);
						}
					}
					if (count == 3 && !move.equals(new Point(-1, -1))
							&& board[temp.x][temp.y] == 0) {
						return new Point(temp.x, temp.y);
					} else if (count == 3 && board[row - 3][col - 3] == 0) {
						return new Point(row - 3, col - 3);
					}
					count = 0;
				}
			}
		}

		return move;
	}

	// blocks scenarios where there are 2 in
	// a row, since scenarios where it looks like
	// 101 are not dangerous at all (since if
	// it was 1101 or 1011 it would have been found
	// by threes already)
	public Point blockTwos(int[][] board) {
		Point move = new Point(-1, -1);

		// only need to check the scenario
		// 0110 since 0101 or 1010 are not
		// dangerous (they cannot be because of
		// the threes check). 2110 and 0112 are also
		// not dangerous by the same logic.
		// can assume it looks like 201100,
		// 001102, or 001100 since if it
		// was 101102 it would have been picked up
		// by the threes check.
		// 201102 is not dangerous because it can
		// be blocked on the next turn. Therefore there
		// are only three scenarios to check,
		// 00110, 01010 and 01100.
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == 0 && board[row][col + 4] == 0) {
					if (board[row][col + 1] == playerCol
							&& board[row][col + 2] == playerCol
							&& board[row][col + 3] == 0)
						return new Point(row, col + 3);
					else if (board[row][col + 1] == 0
							&& board[row][col + 2] == playerCol
							&& board[row][col + 3] == playerCol)
						return new Point(row, col + 1);
					else if (board[row][col + 1] == playerCol
							&& board[row][col + 2] == 0
							&& board[row][col + 3] == playerCol)
						return new Point(row, col + 2);
				}
			}
		}

		// dont need to check vertical ones

		// check diagonal. only diagonal
		// scenarios like 00110, 01010 and 01100
		// need to be checked.
		for (int row = 5; row >= 4; row--) {
			for (int col = 0; col < 7; col++) {
				if (col < 3) {
					if (board[row][col] == 0 && board[row - 4][col + 4] == 0
							&& board[row][col + 1] != 0
							&& board[row - 1][col + 2] != 0
							&& board[row - 2][col + 3] != 0
							&& board[row - 3][col + 4] != 0
							&& (row == 4 && board[row + 1][col] != 0)) {
						if (board[row - 1][col + 1] == playerCol
								&& board[row - 2][col + 2] == playerCol
								&& board[row - 3][col + 3] == 0) {
							return new Point(row - 3, col + 3);
						} else if (board[row - 1][col + 1] == 0
								&& board[row - 2][col + 2] == playerCol
								&& board[row - 3][col + 3] == playerCol) {
							return new Point(row - 1, col + 1);
						} else if (board[row - 1][col + 1] == playerCol
								&& board[row - 2][col + 2] == 0
								&& board[row - 3][col + 3] == playerCol) {
							return new Point(row - 2, col + 2);
						}
					}
				}
				// dont need to check when col = 3
				else if (col > 3) {
					if (board[row][col] == 0 && board[row - 4][col - 4] == 0
							&& board[row][col - 1] != 0
							&& board[row - 1][col - 2] != 0
							&& board[row - 2][col - 3] != 0
							&& board[row - 3][col - 4] != 0
							&& (row == 4 && board[row - 1][col] != 0)) {
						if (board[row - 1][col - 1] == playerCol
								&& board[row - 2][col - 2] == playerCol
								&& board[row - 3][col - 3] == 0) {
							return new Point(row - 3, col - 3);
						} else if (board[row - 1][col - 1] == 0
								&& board[row - 2][col - 2] == playerCol
								&& board[row - 3][col - 3] == playerCol) {
							return new Point(row - 1, col - 1);
						} else if (board[row - 1][col - 1] == playerCol
								&& board[row - 2][col - 2] == 0
								&& board[row - 3][col - 3] == playerCol)
							return new Point(row - 2, col - 2);
					}
				}
			}
		}

		return move;
	}

	// looks for 00110 or 01100 scenarios
	// since those are the only ones worth
	// developing
	public Point AITwos(int[][] board) {
		Point move = new Point(-1, -1);

		// horizontal
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == 0 && board[row][col + 4] == 0) {
					if (board[row][col + 1] == AICol
							&& board[row][col + 2] == AICol
							&& board[row][col + 3] == 0)
						return new Point(row, col + 3);
					else if (board[row][col + 1] == 0
							&& board[row][col + 2] == AICol
							&& board[row][col + 3] == AICol)
						return new Point(row, col + 1);
					else if (board[row][col + 1] == AICol
							&& board[row][col + 2] == 0
							&& board[row][col + 3] == AICol)
						return new Point(row, col + 2);
				}
			}
		}

		// vertical
		for (int row = 0; row <= 3; row++) {
			for (int col = 0; col < 7; col++) {
				if (board[row + 1][col] == AICol
						&& board[row + 2][col] == AICol) {
					return new Point(row, col);
				}
			}
		}

		// diagonal
		for (int row = 5; row >= 4; row--) {
			for (int col = 0; col < 7; col++) {
				if (col < 3) {
					if (board[row][col] == 0 && board[row - 4][col + 4] == 0
							&& board[row][col + 1] != 0
							&& board[row - 1][col + 2] != 0
							&& board[row - 2][col + 3] != 0
							&& board[row - 3][col + 4] != 0
							&& (row == 4 && board[row + 1][col] != 0)) {
						if (board[row - 1][col + 1] == AICol
								&& board[row - 2][col + 2] == AICol
								&& board[row - 3][col + 3] == 0) {
							return new Point(row - 3, col + 3);
						} else if (board[row - 1][col + 1] == 0
								&& board[row - 2][col + 2] == AICol
								&& board[row - 3][col + 3] == AICol) {
							return new Point(row - 1, col + 1);
						} else if (board[row - 1][col + 1] == AICol
								&& board[row - 2][col + 2] == 0
								&& board[row - 3][col + 3] == AICol) {
							return new Point(row - 2, col + 2);
						}
					}
				}
				// dont need to check when col = 3
				else if (col > 3) {
					if (board[row][col] == 0 && board[row - 4][col - 4] == 0
							&& board[row][col - 1] != 0
							&& board[row - 1][col - 2] != 0
							&& board[row - 2][col - 3] != 0
							&& board[row - 3][col - 4] != 0
							&& (row == 4 && board[row - 1][col] != 0)) {
						if (board[row - 1][col - 1] == AICol
								&& board[row - 2][col - 2] == AICol
								&& board[row - 3][col - 3] == 0) {
							return new Point(row - 3, col - 3);
						} else if (board[row - 1][col - 1] == 0
								&& board[row - 2][col - 2] == AICol
								&& board[row - 3][col - 3] == AICol) {
							return new Point(row - 1, col - 1);
						} else if (board[row - 1][col - 1] == AICol
								&& board[row - 2][col - 2] == 0
								&& board[row - 3][col - 3] == AICol)
							return new Point(row - 2, col - 2);
					}
				}
			}
		}

		return move;
	}
}
