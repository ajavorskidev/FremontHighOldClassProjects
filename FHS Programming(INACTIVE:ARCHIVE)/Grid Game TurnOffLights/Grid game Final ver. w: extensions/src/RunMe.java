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
		if(a != null) {
		c = Integer.parseInt(a);
		}
		else if(a == null) {
			do {
				a = JOptionPane.showInputDialog("Don't play with me bro, choose a number right now or you will die");
			}while(a == null);
			c = Integer.parseInt(a);
		}
		// Create a game object
		if(c <= 3) {
			game = new TurnOffTheLights(5, 5, c);
		}
		if(c > 3) {
		game = new TurnOffTheLights(10, 10, c);
		}
		// Create the display
		// parameters: (10,10) is upper left of display
		// (300, 300) is the width and height
		display = new Display(this, 10, 10, 400, 400);

		display.setColor(1, 0xFF3399FF); // SET COLORS FOR PLAYER 1 & 2   (lights out uses 0 and 1)
		display.setColor(2, 0xFF888888);

		// You can use images instead if you'd like.
		//display.setImage(1, "c:/data/ball.jpg");
		// display.setImage(2, "c:/data/cone.jpg");

		display.initializeWithGame(game);
		counter = 0;
	}

	@Override
	public void draw() {
		background(200);

		display.drawGrid(game.getGrid()); // display the game
		textSize(28);
		fill(0,0,0);
		text("Number of Clicks: " + counter, 100,500);
		textSize(13);
		if(c == 1) {
			text("Difficulty: Easiest", 450,50);
		}
		else if(c == 2) {
			text("Difficulty: Easy", 450,50);
		}
		else if(c == 3) {
			text("Difficulty: Medium", 450,50);
		}
		else if(c == 4) {
			text("Difficulty: Hard", 450,50);
		}
		else if(c == 5) {
			text("Difficulty: Hardest", 450,50);
		}
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