import java.io.IOException;

// this class is used by ConnectFour
// uses PlaceDisk and Memory

// strictly game theory that allows the user to restore the game they saved
public class Restore {
public Restore(){
		
	}
	public static void getArrays() throws IOException{
		Memory gameMemory = new Memory();
		PlaceDisk restoreDisk = new PlaceDisk();
		
		//gets array from memory and sends it to place disk to update positions
		int[][] newArray = gameMemory.loadGame();
	}
}