/*
 Class connectFour
 Coded by: Maya Ramamurthy, Randa Mohsen, Manaar Hyder, Danielle Shrewd, and James Lee
 Due: Feb. 26, 2015
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConnectFour extends JFrame implements MouseListener {

	// initialize current player
	static checkWin check;
	static currentPlayer Player = new currentPlayer();

	static int[][] winPos = new int[4][2];
	int total = 0;

	String progress = "";
	Color colour = Color.BLACK;
	int xPos, yPos;
	static JLabel status;


	// at the beginning, sets the program to start mode so that the counters are
	// not drawn
	static boolean start = false;
	int redCount = 0;
	int blueCount = 0;
	boolean error = false;
	// creates a polygon for the blue counter holder
	int xPoly1[] = { 25, 18, 122, 116, 70 }; // x-coordinates
	int yPoly1[] = { 160, 270, 270, 160, 230 }; // y-coordinates
	Polygon poly1 = new Polygon(xPoly1, yPoly1, xPoly1.length); // create a
																// polygon type

	// creates a polygon for the red counter holder
	int xPoly2[] = { 879, 872, 978, 971, 924 }; // x-coordinates
	int yPoly2[] = { 160, 270, 270, 160, 230 }; // y-coordinates
	Polygon poly2 = new Polygon(xPoly2, yPoly2, xPoly2.length); // create a
																// polygon type

	// must keep track of positions of the discs
	int[][] positions = new int[7][6];
	Memory saveGame;

	// the radius of each disc
	private static final int DISC_RADIUS = 100;

	// the constructor for the game
	public ConnectFour() {
		super("Connect Four");
		Container contentPane = getContentPane();
		contentPane.add(new DrawingPanel(), BorderLayout.CENTER);
		contentPane.addMouseListener(this);

		try {
			saveGame = new Memory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// draws the images using the graphics object
	private class DrawingPanel extends JPanel {
		public DrawingPanel() {
			// set the basic parameters of the drawing panel
			setPreferredSize(new Dimension(1000, 700));
			setOpaque(true);
			setResizable(false);
			setBackground(Color.white);
			setLayout(null);
			setFocusable(true);
			requestFocusInWindow();

			// create the start button
			Button startButton = new Button("New Game");
			startButton.setBounds(20, 290, 100, 25);

			// when user is done placing circles
			Button endButton = new Button("Done Turn");
			endButton.setBounds(20, 330, 100, 25);

			Button saveButton = new Button("Save Game");
			saveButton.setBounds(20, 370, 100, 25);

			Button loadButton = new Button("Load Game");
			loadButton.setBounds(20, 410, 100, 25);

			// add an action listener, if this button is pressed, start a new
			// game
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redCount = 0;
					blueCount = 0;
					progress = "Game in Progress";

					// set all positions to 0 (empty)
					for (int i = 0; i < 7; i++) {
						for (int j = 0; j < 6; j++) {
							positions[i][j] = 0;
							repaint();
						}
				}

					if (start == false) {
						start = true;
						currentPlayer.Random();
					}
					for (int i = 0; i < 4; i++) {
						winPos[i] = null;
					}
					total = 0;
					error = false;
				}

			});

			// when end turn is pressed
			endButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// compare red turns with blue turns
					for (int i = 0; i < 7; i++) {
						for (int j = 0; j < 6; j++) {
							// check to see if each tile is supported by one
							// below it
							if (positions[i][j] != 0 && j != 5) {
								for (int down = j; down < 6; down++) {
									if (positions[i][down] == 0) {
										// mark with error
										positions[i][down] = positions[i][j];
										positions[i][j] = 0;
										// error = true;
									}
								}
							}

							// record counts of each color tile
							if (positions[i][j] == 1) {
								redCount++;
							} else if (positions[i][j] == 2) {
								blueCount++;
							}
						}
					}
					// System.out.println (blueCount + " " + redCount);
					if (error)
						JOptionPane
								.showMessageDialog(null,
										"ERROR: Tiles unsupported by any discs underneath! (Marked by yellow)");

					// if red has more turns than blue.. show invalid move popup
					/*
					 * else if (redCount > blueCount) {
					 * JOptionPane.showMessageDialog(null,
					 * "ERROR: Red had more turns than Blue!"); } // if blue has
					 * more turns than red.. show invalid move popup else if
					 * (blueCount > redCount) {
					 * JOptionPane.showMessageDialog(null,
					 * "ERROR: Blue had more turns than Red!"); }
					 */
					// no errors, check for win;
					else { // gets the information for new status changes
						showWin show = new showWin();
						checkWin check = new checkWin();
						total = check.checkWin(positions);
						if (total == 1 | total == 2){winPos = check.getPos();}
						progress = show.show(positions, total);
						colour = show.getColour();
					}
				}
			});

			// save the current game state into a text file
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						saveGame.saveGame(positions);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			// load a game from a text file
			loadButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int[][] temp;
						temp = saveGame.loadGame();
						for (int i = 0; i < 7; i++) {
							for (int j = 0; j < 6; j++) {
								positions[i][j] = temp[i][j];
							}
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			// add the button to the panel
			add(startButton);
			add(endButton);
			add(saveButton);
			add(loadButton);
		}

		// the main function for drawing graphics
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g; // create 2d object
			g2d.setStroke(new BasicStroke(2)); // set thickness of line

			// clears the screen (overwrites the other circles)
			g2d.clearRect(0, 0, 1000, 750);
			

			g2d.setColor(colour);
			g2d.setFont(new Font("Ariel", Font.BOLD, 20));
			g2d.drawString(progress, 400, 20);
			
			g2d.setColor(Color.BLACK);

			// each box is 100 x 100
			// draws the vertical lines
			for (int i = 0; i < 8; i++) {
				g2d.drawLine(140 + i * DISC_RADIUS, 30, 140 + i * DISC_RADIUS,
						690);
			}
			// draws the horizontal lines
			for (int i = 0; i < 7; i++) {
				g2d.drawLine(140, 90 + i * DISC_RADIUS, 840, 90 + i
						* DISC_RADIUS);
			}
			// draws the circles
			for (int i = 0; i < 6; i++) { // new vertical row of circles
				for (int j = 0; j < 7; j++) { // new horizontal row of circles
					g2d.drawOval(140 + j * DISC_RADIUS, 90 + i * DISC_RADIUS,
							DISC_RADIUS, DISC_RADIUS);
				}
			}

			// if at the start of the game, will not draw the counters
			if (start == true) {
				// draws blue counter
				g2d.drawOval(20, 90, DISC_RADIUS, DISC_RADIUS);
				g2d.setColor(Color.BLUE); // sets colour to blue
				g2d.fillOval(20, 90, DISC_RADIUS, DISC_RADIUS); // draws the
																// circle

				// draws red counter
				g2d.setColor(Color.BLACK);
				g2d.drawOval(875, 90, DISC_RADIUS, DISC_RADIUS);
				g2d.setColor(Color.RED); // sets the colour to red
				g2d.fillOval(875, 90, DISC_RADIUS, DISC_RADIUS); // draws the
																	// circle
			}

			// draws blue counter stand;
			g2d.setStroke(new BasicStroke(4)); // sets the line width
			g2d.setColor(Color.BLACK); // changes the outline colour to black
			g2d.drawPolygon(poly1); // draw the outline
			g2d.setColor(Color.GRAY); // changes the fill colour to grey
			g2d.fillPolygon(poly1); // draws the filling

			// draws red counter stand;
			g2d.setColor(Color.BLACK); // changes the outline colour to black
			g2d.drawPolygon(poly2); // draws the outline
			g2d.setColor(Color.GRAY); // changes the fill colour to grey
			g2d.fillPolygon(poly2); // draws the filling

			// print on the screen who's turn it is

			// redraw the whole board (inefficient i know)
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (positions[i][j] == 1) {
						g2d.setColor(Color.RED);
						g2d.fillOval(140 + i * DISC_RADIUS, 90 + j
								* DISC_RADIUS, DISC_RADIUS, DISC_RADIUS);
					} else if (positions[i][j] == 2) {
						g2d.setColor(Color.BLUE);
						g2d.fillOval(140 + i * DISC_RADIUS, 90 + j
								* DISC_RADIUS, DISC_RADIUS, DISC_RADIUS);
					} else if (positions[i][j] == -1) {
						g2d.setColor(Color.YELLOW);
						g2d.fillOval(140 + i * DISC_RADIUS, 90 + j
								* DISC_RADIUS, DISC_RADIUS, DISC_RADIUS);
					}
				}
			}

			g2d.setColor(Color.black);
			if (total == 1 | total == 2) {
				for (int i = 0; i < 4; i++) {
					g2d.fillOval(180 + winPos[i][1] * DISC_RADIUS, 130
							+ winPos[i][0] * DISC_RADIUS, 25, 25);
				}
			}
		//	showWin show = new showWin();
		//	show.drawDots(positions);

			repaint();

		}

	}

	// places and draws a disc at the place the player clicked
	private void placeDisc(Point p, String player) {
		if (player == "Red") {
			positions[(p.x - 140) / DISC_RADIUS][(p.y - 90) / DISC_RADIUS] = 1;
		} else if (player == "Blue") {
			positions[(p.x - 140) / DISC_RADIUS][(p.y - 90) / DISC_RADIUS] = 2;
		}
	}

	// function to make round x and y coordinates so circles "snap" in place
	// long roundUp(long n, long m) {
	// return n >= 0 ? (n / m) * m : ((n - m + 1) / m) * m;
	// }

	// get x and y coordinates for where user has clicked and prints to console
	public void mouseClicked(MouseEvent e) {
		// get x and y coordinates
		xPos = e.getX();
		yPos = e.getY();

		// if user clicks blue icon, set turn to blue
		if (xPos > 20 && xPos < 120 && yPos > 90 && yPos < 190) {
			Player.setPlayer("Blue");
		}
		// if user clicks red icon, set turn to red
		if (xPos > 875 && xPos < 975 && yPos > 90 && yPos < 190) {
			Player.setPlayer("Red");
		}

		// make sure circles are in bounds of the board
		if (xPos > 100 && xPos < 840 && yPos > 30 && yPos < 690) {
			placeDisc(new Point(xPos, yPos), Player.getPlayer());
		}
	}

	// the main program, runs the game
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		JPanel panel = new JPanel();
		// game.add(panel);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.pack();
		game.setVisible(true);
	}

	// @Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}