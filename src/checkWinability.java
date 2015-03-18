// this class is used by Check not

// if it is no longer possible to win the game, game over 
public class checkWinability {

	public static boolean check(int[][] positions) {
		
		// using the duplicating function already in checkWin to create an array of values
		int[][] value = checkWin.duplicate(positions);

		// check for a horizontal win
		for (int row = 5; row >= 0; row--) {
			for (int col = 0; col < 4; col++) { // don't check last three, as
												// that would result in out of
												// bounds
				// for every position check that it's value is equal to zero, or the next three positions' values
				if (value[col][row] == value[col + 1][row]
						|| 0 == value[col + 1][row]) {
					if (value[col][row] == value[col + 2][row]
							|| 0 == value[col + 2][row]) {
						if (value[col][row] == value[col + 3][row]
								|| 0 == value[col + 3][row]) {
							return true;
						}
					}
				}
			}
		}

		// check for a vertical win
		for (int col = 0; col < 7; col++) {
			for (int row = 2; row >= 0; row--) { // don't check last three, as
													// that would result in out
													// of bounds
				// for every position check that it's value is equal to zero, or the next three positions' values
				if (value[col][row] == value[col][row + 1]
						|| 0 == value[col][row + 1]) {
					if (value[col][row] == value[col][row + 2]
							|| 0 == value[col][row + 2]) {
						if (value[col][row] == value[col][row + 3]
								|| 0 == value[col][row + 3]) {
							return true;
						}
					}
				}
			}
		}

		// check for left leaning diagonal win 
		for (int col = 6; col > 2; col--) {// don't check last 3, as that would
											// cause overflow
			for (int row = 5; row > 2; row--) { // don't check last three, as
												// that would result in out of
												// bounds
				// for every position check that it's value is equal to zero, or the next three positions' values
				if (value[col][row] == value[col - 1][row - 1]
						|| 0 == value[col - 1][row - 1]) {
					if (value[col][row] == value[col - 2][row - 2]
							|| 0 == value[col - 2][row - 2]) {
						if (value[col][row] == value[col - 3][row - 3]
								|| 0 == value[col - 3][row - 3]) {
							return true;
						}
					}
				}
			}
		}

		// check for right leaning diagonal wins 
		for (int col = 0; col < 4; col++) {// dont check last 3, as that would
											// cause overflow
			for (int row = 5; row > 2; row--) { // don't check last three, as
												// that would result in out of
												// bounds
				// for every position check that it's value is equal to zero, or the next three positions' values
				if (value[col][row] == value[col + 1][row - 1]
						|| 0 == value[col + 1][row - 1]) {
					if (value[col][row] == value[col + 2][row - 2]
							|| 0 == value[col + 2][row - 2]) {
						if (value[col][row] == value[col + 3][row - 3]
								|| 0 == value[col + 3][row - 3]) {
							return true;
						}
					}
				}
			}
		}
		// if it didn't pass any of the previous conditions, the board holds no more possible wins
		return false;
	}

}
