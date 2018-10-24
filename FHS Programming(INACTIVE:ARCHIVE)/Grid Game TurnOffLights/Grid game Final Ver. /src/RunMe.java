import javax.swing.JOptionPane;

import processing.core.*;


// RunMe is the controller
public class RunMe extends PApplet {
	String a;
	int c;
	TurnOffTheLights game; // this is the model
	Display display; // this is the view
	int counter;

	public void setup() {
		size(640, 550); // set the size of the screen.
		a = JOptionPane.showInputDialog("Please insert a difficulty number from 1 - 5 with 5 being the hardest");
		c = Integer.parseInt(a);
		// Create a game object
		if(c <= 3) {
			game = new TurnOffTheLights(5, 5);
		}
		if(c > 3) {
		game = new TurnOffTheLights(10, 10);
		}
		game.difficulty(c);
		// Create the display
		// parameters: (10,10) is upper left of display
		// (300, 300) is the width and height
		display = new Display(this, 10, 10, 400, 400);

		display.setColor(1, 0xFF3399FF); // SET COLORS FOR PLAYER 1 & 2   (lights out uses 0 and 1)
		display.setColor(2, 0xFF888888);

		// You can use images instead if you'd like.
		// display.setImage(1, "c:/data/ball.jpg");
		// display.setImage(2, "c:/data/cone.jpg");

		display.initializeWithGame(game);
		c = 0;
		counter = 0;
	}

	@Override
	public void draw() {
		background(200);

		display.drawGrid(game.getGrid()); // display the game
		textSize(28);
		fill(0,0,0);
		text("Number of Clicks: " + counter, 100,500);
	}

	public void mouseClicked() {
		Location loc = display.gridLocationAt(mouseX, mouseY);
		if(game.isGameOver() != true) {
			game.move(loc.getRow(), loc.getCol());
			counter++;
		}
		if (game.isGameOver()) {
			System.out.println("You win!");
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}
}