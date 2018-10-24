import processing.core.PImage;
import processing.core.PApplet;
public class Ground {
	float xPos,yPos,xSpeed;
	PImage ground;
	PApplet m;
	public Ground(PApplet m,PImage ground,float x) {
		this.m = m;
		this.ground = ground;
		xPos = x;
		yPos = 200;
	}
	public void drawGround() {
		m.image(ground, xPos, yPos);
	}
	public void move() {
		xPos += 1;
	}
	public void isOff() {
		if(this.xPos <= 2402) {
			xPos = 2402;
		}
	}

}
