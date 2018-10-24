import java.util.ArrayList;

import javax.swing.JOptionPane;
import processing.core.*;


// RunMe is the controller
public class TURNTHELIGHTOFF extends PApplet {
	String a;
	int c;
	TurnOffTheLights game; // this is the model
	Display display; // this is the view
	int counter;
	int resets;
	int click;
	int loc;
	ArrayList<Location> Replays = new ArrayList<Location>();

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
		game = new TurnOffTheLights(11, 11, c);
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
		key();
		resetBox();
		textSize(28);
		fill(0,0,0);
		text("Number of Clicks: " + counter, 100,500);
		textSize(10);
		text("Modified by Adrian Javorski 2018", 475, 545);
		textSize(13);
		text("Click the reset to reset!", 425, 100);
		text("# of resets: " + resets, 425,125);
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
		locationStore(loc);
		if(game.isGameOver() != true) {
			game.move(loc.getRow(), loc.getCol());
			counter++;
		}
		if (game.isGameOver()) {
			System.out.println("You win!");
		}
		if(click == 1) {
			game.resetFour();
			resets++;
			counter = 0;
		}
	}
	public void key() {
		if(keyPressed) {
			if(key == 't' || key == 'T') {
				locationReplay();
			}
		}
	}
	public void locationStore(Location a) {
		for(int d = 0;d < 4;d++) {
			Replays.add(game.initial.get(d));
			loc++;
		}
		Replays.add(a);
		Location t = Replays.get(loc);
		System.out.println("Location: "+t.getRow()+", "+t.getCol()+" is recorded!"+"Index: "+loc);
		loc++;
		}
	public void locationReplay() {
//		game.clearGrid();
		for(int d = 0; d < Replays.size(); d++) {
			Location t = Replays.get(d);
			if(game.getGrid()[t.getRow()][t.getCol()] == 0) {
				game.getGrid()[t.getRow()][t.getCol()] = 1;
				break;
			}
			if(game.getGrid()[t.getRow()][t.getCol()] == 1) {
				game.getGrid()[t.getRow()][t.getCol()] = 0;
				break;
			}
			
		}
	}
	public void resetBox() {
		textSize(13);
		fill(0,0,255);
		text("Reset!",425, 75);
		if(mouseX >= 425 && mouseX <= 465 && mouseY >= 63 && mouseY <= 76) {
			fill(255,0,0,75);
			rect(425,63, 40, 13);
			click = 1;
		}
		else{
			click = 0;
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "TURNTHELIGHTOFF" });
	}
}