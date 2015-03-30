import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


// this class is used by class Check
// strictly GUI 

public class showWin extends ConnectFour{
	// if a game is won, a dot is displayed on the center of each of the four winning discs    
	private static Color colour;
	ConnectFour x = new ConnectFour ();
		
	public String show (int [][] positions, int total){

		if (total == 1 | total == 2) {
			//x.winPos = check.getPos();
			if (total == 1) {
				x.colour = Color.red;
				x.progress = "RED WINS!";

			} else if (total == 2) {
				x.colour = Color.blue;
				x.progress = "BLUE WINS!";

			}
		} else if (total == 0) {
			x.colour = Color.MAGENTA;
			x.progress = "It's a Draw";

		} else {
			x.colour = Color.black;
			x.progress = "Game in Progess";
		}
		setColour(x.colour);
		return x.progress;
	}
	
	static void setColour (Color col){
		colour = col;
	}
	
	public Color getColour (){
		return colour;
	}
}