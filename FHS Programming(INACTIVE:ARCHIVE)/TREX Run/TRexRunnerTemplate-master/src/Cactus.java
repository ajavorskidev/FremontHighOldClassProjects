import processing.core.PApplet;
import processing.core.PImage;

public class Cactus {
	// Fields
	PImage img;
	PApplet window;
	float x, y;
	private int id;

	// Constructor
	public Cactus(PApplet window, String filename, int x, int y, int id) {

		this.x = x;
		this.y = y;
		this.window = window;
		this.id = id;

		img = window.loadImage(filename);
	}

	public void drawCloud(PApplet w) {
		window.image(img, x, y);
	}

	// draw
	public void draw() {
		window.image(img, x, y);
	}

	public void moveLeft() {
		this.x -= 5;
	}

	public void moveRight() {
		this.x += 3;
	}

	public boolean isOff() {
		if (this.x < -20) {
			return true;
		} else {
			return false;
		}
	}

	public void setPosition(float initial) {
		if (this.isOff() == true) {
			this.x = initial;
		}
	}

	public float getX() {
		return x;
	}

	public float getWidth() {
		if (id == 1) {
			return 48;
		}
		if (id == 2) {
			return 30;
		}
		if (id == 3) {
			return 65;
		}
		if (id == 4) {
			return 101;
		} else {
			return 0;
		}
	}

	public float getY() {
		return y;
	}

	public float getHeight() {
		if (id == 1) {
			return 102;
		}
		if (id == 2) {
			return 74;
		}
		if (id == 3) {
			return 71;
		}
		if (id == 4) {
			return 70;
		} else {
			return 0;
		}
	}

	public void intialX() {
		if (id == 1) {
			this.x = (float) (Math.random() * 100 + 900);
		}
		if (id == 2) {
			this.x = (float) (Math.random() * 100 + 1400);
		}
		if (id == 3) {
			this.x = (float) (Math.random() * 75 + 1950);
		}
		if (id == 4) {
			this.x = (float) (Math.random() * 30 + 2400);
		}
	}

}