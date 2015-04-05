/*
 Class connectFour
 Coded by: Maya Ramamurthy, Randa Mohsen, Manaar Hyder, Danielle Shrewd, and James Lee
 Due: March 18, 2015
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

	int x = 20;
	int size = 20;
	static PlaceDisk place = new PlaceDisk();
	static currentPlayer player = new currentPlayer();

	// AI variables
	static checkWin check;
	boolean AIGame = false;
	static connectFourAI AI;
	int playerCol, AICol;
	boolean playerFirst = false;

	// 2P game variables
	int playerTurn;

	static int[][] winPos = new int[4][2];
	int total = 0;

	String progress = "";
	Color colour = Color.BLACK;
	int xPos, yPos;
	static JLabel status;

	// at the beginning, sets the program to start mode so that the counters are
	// not drawn
	static boolean start = false;
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
	static int[][] positions = new int[6][7];
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
			Button startButtonAI = new Button("New Game (AI)");
			startButtonAI.setBounds(20, 290, 100, 25);

			Button startButton2P = new Button("New Game (2P)");
			startButton2P.setBounds(20, 330, 100, 25);

			Button endButton = new Button("End Game");
			endButton.setBounds(20, 450, 100, 25);

			Button saveButton = new Button("Save Game");
			saveButton.setBounds(20, 370, 100, 25);

			Button loadButton = new Button("Load Game");
			loadButton.setBounds(20, 410, 100, 25);

			// add an action listener, if this button is pressed, start a new
			// game
			startButtonAI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					progress = "Game in Progress";

					// set all positions to 0 (empty)
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 7; j++) {
							positions[i][j] = 0;
							repaint();
						}
					}

					start = true;
					AIGame = true;
					winPos = new int[4][4];
					total = 0;
					error = false;
					colour = Color.black;

					// randomly decide which player
					// goes first
					int rand = 1 + (int) (Math.random() * 2);
					if (rand == 1) {
						playerFirst = true;
						// player is red, AI is blue
						playerCol = 1;
						AICol = 2;
						progress += ": You are Player Red";
						AI = new connectFourAI(playerCol, AICol);
					} else if (rand == 2) {
						playerCol = 2;
						AICol = 1;
						progress += ": You are Player Blue";
						AI = new connectFourAI(playerCol, AICol);
						positions = PlaceDisk.AIPlace(AI.bestMove(positions),
								AICol, positions);
					}
				}
			});

			// start a new 2 player game
			startButton2P.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					progress = "Game in Progress";

					// set all positions to 0 (empty)
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 7; j++) {
							positions[i][j] = 0;
							repaint();
						}
					}

					start = true;
					winPos = new int[4][4];
					total = 0;
					error = false;
					colour = Color.black;
					AIGame = false;

					// randomly decide which player
					// goes first
					int rand = 1 + (int) (Math.random() * 2);
					if (rand == 1) {
						playerTurn = 1;
					} else if (rand == 2) {
						playerTurn = 2;
					}
					playerTurn = player.getPlayer();
				}

			});

			// when end turn is pressed
			endButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
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
						for (int i = 0; i < 6; i++) {
							for (int j = 0; j < 7; j++) {
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
			add(startButtonAI);
			add(startButton2P);
			add(saveButton);
			add(loadButton);
			add(endButton);
		}

		// the main function for drawing graphics
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g; // create 2d object
			g2d.setStroke(new BasicStroke(2)); // set thickness of line

			// clears the screen (overwrites the other circles)
			g2d.clearRect(0, 0, 1000, 750);

			showWin show = new showWin();

			g2d.setColor(colour);

			g2d.setFont(new Font("Ariel", Font.BOLD, size));
			g2d.drawString(progress, 400, x);

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
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					if (positions[i][j] == 1) {
						g2d.setColor(Color.RED);
						g2d.fillOval(140 + j * DISC_RADIUS, 90 + i
								* DISC_RADIUS, DISC_RADIUS, DISC_RADIUS);
					} else if (positions[i][j] == 2) {
						g2d.setColor(Color.BLUE);
						g2d.fillOval(140 + j * DISC_RADIUS, 90 + i
								* DISC_RADIUS, DISC_RADIUS, DISC_RADIUS);
					} else if (positions[i][j] == -1) {
						g2d.setColor(Color.YELLOW);
						g2d.fillOval(140 + j * DISC_RADIUS, 90 + i
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

			repaint();

		}

	}

	// get x and y coordinates for where user has clicked and prints to console
	public void mouseClicked(MouseEvent e) {
		// get x and y coordinates
		xPos = e.getX();
		yPos = e.getY();
		if (start == true) {
			// make sure circles are in bounds of the board
			if (xPos > 100 && xPos < 840 && yPos > 30 && yPos < 690) {
				if (positions[0][(xPos - 140) / DISC_RADIUS] == 0) {
					if (AIGame) {
						if (playerFirst == false) {
							// check for AI win and update if so
							Check.Update(positions);
							total = Check.returnTotal();
							winPos = Check.returnPos();
							if (total == 1 | total == 2) {
								showWin show = new showWin();
								progress = show.show(positions, total);
								colour = show.getColour();
							} else if (total == -1) {
								progress = "GAME OVER";
								colour = Color.magenta;
							}
						}

						else if (playerFirst == true)
							positions = PlaceDisk.place(new Point(xPos, yPos),
									playerCol, positions);
					} else {
						positions = PlaceDisk.place(new Point(xPos, yPos),
								player.getPlayer(), positions);
					}

					Check.Update(positions);
					total = Check.returnTotal();
					winPos = Check.returnPos();
					if (total == 1 | total == 2) {
						showWin show = new showWin();
						progress = show.show(positions, total);
						colour = show.getColour();
					} else if (total == -1) {
						progress = "GAME OVER";
						colour = Color.magenta;
					}

					if (AIGame) {
						Point best = AI.bestMove(positions);

						System.out.println("best move: " +best.x + " "
								+ best.y);

						positions = PlaceDisk.AIPlace(best,
								AICol, positions);

					}
				}
			}
		}
	}

	// the main program, runs the game
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.pack();
		game.setVisible(true);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}