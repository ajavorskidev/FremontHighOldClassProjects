import processing.core.PApplet;
public class Star {
	private int id;
	private float xPos,yPos;
	public Star(int id) {
		this.id = id;
		xPos = (float)(Math.random()*2000+800);
		yPos = (float)(Math.random()*300+1);
	}
	public void starY() {
		yPos = (float)(Math.random()*300+1);
	}
	public void moveStar() {
		if(id == 1) {
			xPos -= 2;
		}
		if(id == 2) {
			xPos -= 4;
		}
		if(id == 3) {
			xPos -= 5;
		}
	}
	public void drawStar(PApplet t) {
		t.ellipse(xPos, yPos, 2, 2);
	}
	public void resetStar() {
		if(xPos < 0) {
			xPos = (float)(Math.random()*2000 + 800);
			yPos = (float)(Math.random()*150+1);
		}
	}
}
