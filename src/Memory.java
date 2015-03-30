import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// this class is used by PlaceDisk and Restore
public class Memory {
	
	static int[][] savedPositions = new int[7][6];
	static Scanner in;
	FileWriter out;

	public Memory() throws IOException {
	}

	// saves the game into a text file
	public void saveGame(int[][] board) throws IOException {
		out = new FileWriter(new File("savedGame.txt"));
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				out.write(board[j][i] + "");
			}
			out.write("\n");
		}
				
		out.close();
	}

	// loads a game from a text file
	public static int[][] loadGame() throws FileNotFoundException {
		in = new Scanner(new FileReader("savedGame.txt"));
		
		int count = 0;
		while (in.hasNext()) {
			String current = in.nextLine();
			for (int j = 0; j < 7; j++) {
				savedPositions[j][count] = Integer.parseInt(current.charAt(j)
						+ "");
			}
			count++;
		}
		in.close();

		return savedPositions;
	}
}