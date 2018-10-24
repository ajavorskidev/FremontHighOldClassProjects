import processing.core.PApplet;
import processing.core.PImage;

public class TRex {
	/* Variables */
	private float xPos;
	private float yPos;
	public int counter;
	public float jumpSpeed;
	public float gravity;
	public int init, jump;
	public String left, right, currentFile;
	public PImage current;
	/* setup image */

	public TRex(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		init = 0;
		counter = 0;
		left = "t_rex_running1.png";
		right = "t_rex_running2.png";
		jump = 0;
	}

	public void drawTRex(PApplet w) {
		if (init == 0) {
			current = w.loadImage(left);
			currentFile = left;
			init = 1;
		}
		w.image(current, xPos, yPos);
		if (jump == 0) {
			if (counter == 10) {
				if (currentFile == left) {
					current = w.loadImage(right);
					currentFile = right;
					counter = 0;
				} else if (currentFile == right && counter == 10) {
					current = w.loadImage(left);
					currentFile = left;
					counter = 0;
				}
			}
			counter += 1;
		}
	}

	public void drawDeadTRex(PApplet w) {
		current = w.loadImage("t_rex_end_game.png");
		w.image(current, xPos, yPos);
	}

	public void setX(int x) {
		this.xPos = x;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public void TRexJump() {
		this.yPos -= this.jumpSpeed;
		this.jumpSpeed = this.jumpSpeed - this.gravity;
		if (this.yPos >= 150) {
			this.yPos = 150;
			this.gravity = 0;
			this.jumpSpeed = 0;
		}
		if (this.yPos < 150) {
			jump = 1;
		}
		if (this.yPos == 150) {
			jump = 0;
		}
	}

	public void setSpeed(float speed) {
		this.jumpSpeed = speed;
		this.gravity = 0.43f;
	}

	public float getYpos() {
		return yPos;
	}

	public boolean colliding(Cactus cact) {
		boolean xOverlap = isIntervalOverlapping(xPos, 80, cact.getX(), cact.getWidth());
		boolean yOverlap = isIntervalOverlapping(yPos, 88, cact.getY(), cact.getHeight());

		if (xOverlap == true && yOverlap == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isIntervalOverlapping(float x, float w, float x1, float w1) {
		if (x > x1 + w1) {
			return false;
		} else if (x1 > x + w) {
			return false;
		} else {
			return true;
		}
	}
}
