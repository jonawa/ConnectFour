import java.awt.Color;


// this class is used by class Check
// strictly GUI 

public class showWin {
	// if a game is won, a dot is displayed on the center of each of the four winning discs    
	private Color colour;
	
	public String show (int [][] positions, int total){
		ConnectFour x = new ConnectFour ();

		if (total == 1 | total == 2) {
			//x.winPos = check.getPos();
			if (total == 1) {
				x.colour = Color.red;
				x.progress = "Red Wins!";

			} else if (total == 2) {
				x.colour = Color.blue;
				x.progress = "Blue Wins!";

			}
		} else if (total == 0) {
			x.colour = Color.MAGENTA;
			x.progress = "It's a Draw";

		} else {
			x.colour = Color.black;
			x.progress = "Game in process";
		}
		setColour(x.colour);
		return x.progress;
	}
	
	private void setColour (Color col){
		colour = col;
	}
	
	public Color getColour (){
		return colour;
	}
	
}
