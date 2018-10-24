import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Snake {
	private boolean gameOver; // flag to record if the game is over
	private int cols, rows; // # of rows and cols in game
	public int[][] grid; // the grid that stores the pieces
	ArrayList<Location> snakes = new ArrayList<Location>();
	public Location appleCoords;
	public Location newSnakeCoord;
	// 1 = Up, 2 = Right, 3 = Down, 4 = Right
	public int direction;
	Location crown;
	boolean snakeTurning;

	// The constructor initializes the game
	public Snake(int r, int c) {
		// Create the board
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];
		crown = new Location(5, 5);
		newSnakeCoord = crown;
		snakes.add(crown);
		direction = 2;
		appleCoords = new Location((int) (Math.random() * grid.length), (int) (Math.random() * grid[0].length)); 
		// ^^^^^^try not to hard code values for this since you will most likely make a mistake^^^^^^
		// Set that the game is not over
		gameOver = false;
	}

	/*
	 * Return true if r, c is a valid move for the game.
	 */

	public boolean isValidMove(int c) {
		// if they hit an arrow in the opposite/same direction they're moving
		// in--> not valid
		return true;
	}

	/*
	 * Return true if the location at row, col is in the bounds of the grid. Return
	 * false otherwise.
	 */
	public boolean isInGrid(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Return true if the location l is in the bounds of the grid. Note: this method
	 * calls the other isInGrid to do the work.
	 */
	public boolean isInGrid(Location l) {
		return isInGrid(l.getRow(), l.getCol());
	}

	// makes the move
	// returns false if no move was made, true if the move was successful.

	public boolean move() {
		// set apple location
		grid[appleCoords.getRow()][appleCoords.getCol()] = 2;
		System.out.println(appleCoords);

		// dies if hits oneself
		for (int i = 1; i < snakes.size(); i++) {
			if (crown == snakes.get(i)) {
				gameOver = true;
			}
		}

	
		if (!isInGrid(crown))
			gameOver = true;

		if (gameOver == true)
			return false; // if game is over, exit

		
		if (crown.getRow() == appleCoords.getRow() && crown.getCol() == appleCoords.getCol()) {
			resetApple();
			Location t = snakes.get(snakes.size() - 1);
			Location l = new Location(t.getRow(), t.getCol());
			/*Changed it to make it cleaner, added setNextRow/col methods to Location class to handle the change better*/
			if (direction == 1) {
				l.setNextRow(l.getRow(), 1);
			}
			if (direction == 2) {
				l.setNextCol(l.getCol(), 2);
			}
			if (direction == 3) {
				l.setNextRow(l.getRow(), 3);
			}
			if (direction == 4) {
				l.setNextCol(l.getCol(), 4);
			}
			snakes.add(l);
			newSnakeCoord = l;

		}
		System.out.println(snakes.size());
		if (snakeTurning)
			turn(direction);
		if (!snakeTurning) {
			for (int i = 0; i < snakes.size(); i++) {
				Location l = snakes.get(i);
				if (direction == 3) {
					l.setLocation(l.getRow() + 1, l.getCol());
				} else if (direction == 1) {
					l.setLocation(l.getRow() - 1, l.getCol());
				} else if (direction == 4) {
					l.setLocation(l.getRow(), l.getCol() - 1);
				} else if (direction == 2) {
					l.setLocation(l.getRow(), l.getCol() + 1);
				}
			}
		}

		// if it isn't part of the snake, then it's empty
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				for (int i = 0; i < snakes.size(); i++) {
					Location n = new Location(row, col);
					Location l = snakes.get(i);
					if (n != snakes.get(i) && grid[row][col] != 2) {
						grid[row][col] = 0;
					}if( n == l) {
						if(isInGrid(l)) {
							grid[n.getRow()][n.getCol()] = 1;
						}
					}
//					else {
//						Location l = snakes.get(i);
//						
//					}

				}
			}
		}

		return true; // this means the move was successfully made

	}

	public void turn(int newdirection) {
		int currentdirection = direction;
		int[] minorDirections = new int[snakes.size()];
		for (int i = 0; i < minorDirections.length; i++) {
			minorDirections[i] = currentdirection;
		}
		Location turningPoint = crown;
		for (int i = 0; i < snakes.size(); i++) {
			if (snakes.get(i) == turningPoint) {
				minorDirections[i] = newdirection;
			}
		}

		
			Location l = snakes.get(0);
			if (minorDirections[0] == 3) {
				l.setLocation(l.getRow() + 1, l.getCol());
			} else if (minorDirections[0] == 1) {
				l.setLocation(l.getRow() - 1, l.getCol());
			} else if (minorDirections[0] == 4) {
				l.setLocation(l.getRow(), l.getCol() - 1);
			} else if (minorDirections[0] == 2) {
				l.setLocation(l.getRow(), l.getCol() + 1);
			}
			for(int i=1; i<snakes.size(); i++) {
				Location newl = snakes.get(i);
				if (minorDirections[i-1] == 3) {
					newl.setLocation(l.getRow() + 1, l.getCol());
				} else if (minorDirections[i-1] == 1) {
					newl.setLocation(l.getRow() - 1, l.getCol());
				} else if (minorDirections[i-1] == 4) {
					newl.setLocation(l.getRow(), l.getCol() - 1);
				} else if (minorDirections[i-1] == 2) {
					newl.setLocation(l.getRow(), l.getCol() + 1);
				}

			}
			
		}
		

	

	public void resetApple() {
		grid[appleCoords.getRow()][appleCoords.getCol()] = 0;
		appleCoords.setLocation((int) (Math.random() * 51), (int) (Math.random() * 51));
	}

	/*
	 * Return true if the game is over. False otherwise.
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	public int[][] getGrid() {
		return grid;
	}
	public void reset() {
		if(!isInGrid(crown)) {
			snakes.clear();
			crown = new Location(5, 5);
			snakes.add(crown);
		}
	}
}


