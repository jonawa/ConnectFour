import java.io.IOException;

// this class is used by ConnectFour
// uses PlaceDisk

// strictly game theory that allows the user to restore the game they saved
public class Restore {
	
	// calls class Memory to retrieve storing arrays
	public Restore(){
		
	}
	public static void getArrays() throws IOException{
		Memory gameMemory = new Memory();
		PlaceDisk restoreDisk = new PlaceDisk();
		
		//gets array from memory and sends it to place disk to update positions
		int[][] newArray = gameMemory.loadGame();
		restoreDisk.updateArray(newArray);
	}
	// calls class PlaceDisk for all the disks recorded in the arrays

}
