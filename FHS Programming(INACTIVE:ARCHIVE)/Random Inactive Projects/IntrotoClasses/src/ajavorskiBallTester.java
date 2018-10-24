import processing.core.PApplet;
import processing.core.PImage;
public class ajavorskiBallTester extends PApplet{
	int boundaryMaxX,boundaryMaxY,slot,colorA,red,blue,green;
	AjavorskiBall[] a;
	AjavorskiBall b;
	PImage dik;
	public void setup() {
		size(1000, 900);
		a = new AjavorskiBall[10000];
		b = new AjavorskiBall(50,50,40);
		slot = 0;
		dik = loadImage("/Users/adrian/Desktop/finals.png");
	}

	public void draw() {
		background(0,0,0);
		if(mousePressed == true) {
			int speed = (int)(Math.random()*11+1);
			if(((int)(Math.random()*2)) == 1) {
				speed = -speed;
			}
			if(slot < a.length) {
			AjavorskiBall d = new AjavorskiBall(mouseX,mouseY,100);
			d.setspeedX(speed);
			d.setspeedY(speed);
			a[slot] = d;
			slot = slot + 1;
			}else if(slot >= a.length) {
				slot = 0;
			}
		}
		for(int i = 0;i< slot;i++) {
			red = (int)(Math.random()*255+1);
			green = (int)(Math.random()*255+1);
			blue = (int)(Math.random()*255+1);
			colorA = color(red,green,blue);
			AjavorskiBall currentBall;
			currentBall = a[i];
			currentBall.move();
			currentBall.isHittingWall();
			currentBall.gravity();
//			currentBall.changeColor(colorA,this);
			currentBall.draw(this,dik);
			if(i > slot) {
				i = 0;
			}
		}
	}
}
