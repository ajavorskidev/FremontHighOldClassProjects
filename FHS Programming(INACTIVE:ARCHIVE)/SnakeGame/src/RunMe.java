import processing.core.*;

public class RunMe extends PApplet {
	int c;
	Snake game;
	Display display;
	PFont Font;
	int Speed;

	public void setup() {
		size(600, 600); 
		Speed = 0;
		Font = createFont("Georgia", 32);
		textFont(Font);

		// Create a game object
		game = new Snake(50, 50);

		// Create the display
		// parameters: (10,10) is upper left of display
		// (300, 300) is the width and height
		display = new Display(this, 0, 0, 600, 600);

		display.setColor(1, 0xF00FFF00); // SET COLORS FOR PLAYER 1 & 2, from internet, hexadecimal
		display.setColor(2, 0xFF0F0000);

		
		display.gameInitialization(game);
		c = 0;
	}

	@Override
	public void draw() {
		Speed++;
		background(200);
		if (Speed % 3 == 0) {
			game.move();
			game.reset();
		}
		display.drawGrid(game.getGrid()); // display the game

	}

	public void keyPressed() {
		if (key == CODED) {
			// Change these if there is only one segment?
			// make it so it doesn't leave a thing behind
			game.snakeTurning = true;
			if (keyCode == UP) {
				if (game.direction != 3)
					game.direction = 1;
			}
			if (keyCode == RIGHT) {
				if (game.direction != 4)
					game.direction = 2;
			}
			if (keyCode == DOWN) {
				if (game.direction != 1)
					game.direction = 3;
			}
			if (keyCode == LEFT) {
				if (game.direction != 2)
					game.direction = 4;
			}
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}
}


