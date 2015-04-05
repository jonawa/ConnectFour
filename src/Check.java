import java.awt.Color;
import java.io.FileNotFoundException;

// this class is used by ConnectFour
// uses checkWinability, checkWin, showWin and Move

// after every user interaction do a check
public class Check {
	
	// call on Move to check legality of the click and proceed with making a move 
	
	// if the move is made 
	
		// call on checkWin to check if the move cause a win 
		
			// if yes then do not continue 
			
			// if no then call on checkWinability to check if the move causes a game over
	
	static int [][] pos;
	static int total;
	static Color colour;
	static String progress;
	
	static void Update(int[][]positions){
		ConnectFour game = new ConnectFour();
		
		boolean winable = checkWinability.check(positions);
		if (winable == false ) { 
			game.start = false;
			game.progress = "GAME OVER";
			total = -1;
		}
		else {
			showWin show = new showWin();
			checkWin check = new checkWin();
			total = check.checkWin(positions);
			if (total == 1 | total == 2){
				game.winPos = check.getPos(); 
				game.start = false;
				game.progress = show.show(positions, total);
				total = check.checkWin(positions);
				
				if (total == 1 | total == 2) {
					pos = check.getPos();
					game.start = false;
				}
				colour = show.getColour();
				progress = show.show(positions, total);
				show.setColour(Color.MAGENTA);
				game.colour = show.getColour();
			}
			
		}
		
	}
	
	public static int [][] returnPos (){
		return pos;
	}
	
	public static int returnTotal(){
		return total;
	}
}