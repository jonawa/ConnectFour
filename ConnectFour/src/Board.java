import java.awt.Polygon;

// this class is used by ConnectFour 
// deals strictly with GUI 
// displays the game board

public class Board {
	
	////////////////// these next two parts are a repeat from ConnectFour
	////////////////// so can someone fully move the GUI (only for the board display) from ConnectFour to here?
	
	// creates a polygon for the blue counter holder
	static int xPoly1[] = { 25, 18, 122, 116, 70 }; // x-coordinates
	static int yPoly1[] = { 160, 270, 270, 160, 230 }; // y-coordinates
	static Polygon poly1 = new Polygon(xPoly1, yPoly1, xPoly1.length); // create a polygon type

	// creates a polygon for the red counter holder
	static int xPoly2[] = { 879, 872, 978, 971, 924 }; // x-coordinates
	static int yPoly2[] = { 160, 270, 270, 160, 230 }; // y-coordinates
	static Polygon poly2 = new Polygon(xPoly2, yPoly2, xPoly2.length); // create a polygon type
	

}