import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


// this class is used by class Check
// strictly GUI 

public class showWin extends ConnectFour{
	// if a game is won, a dot is displayed on the center of each of the four winning discs    
	private Color colour;
	ConnectFour x = new ConnectFour ();
	
	public String show (int [][] positions, int total){

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
		System.out.println(total);
		setColour(x.colour);
		return x.progress;
	}
	
	private void setColour (Color col){
		colour = col;
	}
	
	public Color getColour (){
		return colour;
	}
	

	
	 protected void drawDots(Graphics g, int [] positions) {
		 
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g; // create 2d objectà
		int DISC_RADIUS = 100;
		g2d.setColor(Color.black);
		if (x.total == 1 | x.total == 2) {
			for (int i = 0; i < 4; i++) {
				g2d.fillOval(180 + winPos[i][1] * DISC_RADIUS, 130
						+ winPos[i][0] * DISC_RADIUS, 25, 25);
			}
		}
		 
	}

}
