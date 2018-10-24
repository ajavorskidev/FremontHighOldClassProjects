import java.util.ArrayList;

// TurnOffTheLights is the model
public class TurnOffTheLights {
	private boolean gameOver; // flag to record if the game is over
	private int playerTurn; // whose turn it is
	private int winner; // who the winner is (0 if no winner)
	private int cols, rows; // # of rows and cols in game
	private int[][] grid; // the grid that stores the pieces
	private int[][] replay;
	private int diff;
	ArrayList<Location> initial = new ArrayList<Location>();
	// The constructor initializes the game
	public TurnOffTheLights(int r, int c, int d) {
		difficulty(d);
		// Create the board
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];  // 0 for light off, 1 for light on

		// Initialize starting positions
		if(diff == 1) {
			grid[0][0] = 1;
			grid[0][grid[0].length - 1] = 1;
			grid[grid.length - 1][0] = 1;
			grid[grid.length - 1][grid[0].length - 1] = 1;
		}
		else if(diff > 1) {
			do {
				
				grid[(int)(Math.random()*(grid.length - 1))][(int)(Math.random()*(grid[0].length -1))] = 1;
				grid[(int)(Math.random()*(grid.length - 1))][(int)(Math.random()*(grid[0].length - 1))] = 1;
				grid[(int)(Math.random()*(grid.length - 1))][(int)(Math.random()*(grid[0].length - 1))] = 1;
				grid[(int)(Math.random()*(grid.length - 1))][(int)(Math.random()*(grid[0].length - 1))] = 1;
			}while(fourStart() != true);
		}
		for(int row = 0;row < grid.length;row++) {
			for(int col = 0;col < grid[0].length;col++) {
				if(grid[row][col] == 1) {
					Location t = new Location(row,col);
					initial.add(t);
				}
			}
		}
		// Extension idea: start with some random lights on
		gameOver = false;
	}
		// Set that the game is not over

	/*
	 * Return true if r, c is a valid move for the game.
	 */
	public boolean isValidMove(int r, int c) {
		if (isInGrid(r, c) == false) // if outside grid, not valid
			return false;

		return true; // otherwise it's valid
	}

	/*
	 * Return true if the location at row, col is in the bounds of the grid.
	 * Return false otherwise.
	 */
	public boolean isInGrid(int row, int col) {
		if (row > grid.length -1 || row < 0 || col > grid[0].length - 1 || col < 0) {
			return false;
		}
		return true;
	}

	/*
	 * Return true if the location l is in the bounds of the grid. Note: this
	 * method calls the other isInGrid to do the work.
	 */
	public boolean isInGrid(Location l) {
		return isInGrid(l.getRow(), l.getCol());
	}
	// makes the move
	// returns false if no move was made, true if the move was successful.
	public boolean move(int r, int c) {
		if (isValidMove(r, c) == false)
			return false; // if not valid, exit
		if (gameOver == true)
			return false; // if game is over, exit
		if(diff <= 2) {
			if (isValidMove(r,c) && gameOver == false) {
				if(grid[r][c] == 0) {
					grid[r][c] = 1;
				}
				else if(grid[r][c] == 1) {
					grid[r][c] = 0;
				}
				moveCreate(r,c);
				return true;
			}
		}
		if(diff > 2) {
			if (isValidMove(r, c) && gameOver == false && grid[r][c] != 0) {
				grid[r][c] = 0;
				moveCreate(r,c);
				return true; // this means the move was successfully made
			}
		}
		return false;
	}
	private void moveCreate(int r,int c) {
		if(isInGrid(r - 1, c)) {
			if(grid[r-1][c] ==1) {
				grid[r-1][c] = 0;
			}
			else {
				grid[r-1][c] = 1;
			}
		}
		if(isInGrid(r + 1, c)) {
			if(grid[r+1][c] == 1) {
				grid[r+1][c] = 0;
			}
			else {
				grid[r+1][c] = 1;
			}
		}
		if(isInGrid(r,c-1)) {
			if(grid[r][c-1] == 1) {
				grid[r][c-1] = 0;
			}
			else {
				grid[r][c-1] = 1;
			}
		}
		if(isInGrid(r,c+1)) {
			if(grid[r][c+1] == 1) {
				grid[r][c+1] = 0;
			}
			else {
				grid[r][c+1] = 1;
			}
		}

	// check for the winner
	gameOver = checkForWinner();

	}

	/*
	 * Return true if the game is over. False otherwise.
	 */
	private boolean checkForWinner() {
		// Loop through all locations. If you see a light on, return false because
		// the game isn't over.
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if(grid[row][col] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int[][] getGrid() {
		return grid;
	}
	public void difficulty(int c) {
		diff = c;
	}
	public boolean fourStart() {
		int count = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if(grid[row][col] == 1) {
					count++;
				}
			}
		}
		if(count == 4) {
			return true;
		}
		return false;
	}
	public void resetFour() {
		for(int r = 0; r < grid.length;r++) {
			for(int c = 0; c < grid[0].length;c++) {
				grid[r][c] = 0;
			}
		}
		for(int d = 0; d < 4;d++) {
		Location t = initial.get(d);
		grid[t.getRow()][t.getCol()] = 1;
		}
		
	}
	public void clearGrid() {
		for(int r = 0; r < grid.length;r++) {
			for(int c = 0; c < grid[0].length;c++) {
				grid[r][c] = 0;
			}
		}
	}
}