import processing.core.PApplet;
import processing.core.PImage;

public class AjavorskiBall {
int cordinateX,cordinateY,speedX,speedY,sizeD;
int colorA;

public AjavorskiBall(int X,int Y,int D) {
	this.cordinateX = X;
	this.cordinateY = Y;
	this.sizeD = D;
	this.speedX = 0;
	this.speedY = 0;
}
public void move() {
	this.cordinateX = this.cordinateX + this.speedX;
	this.cordinateY = this.cordinateY + this.speedY;
}
public void setspeedX(int s){
	this.speedX = s;
}
public void setspeedY(int s) {
	this.speedY = s;
}
public void isHittingWall() {
	if(this.cordinateX <= 0) {
		this.cordinateX = 0;
		this.speedX = -this.speedX;
	}else if(this.cordinateX >= 1000) {
		this.cordinateX = 1000;
		this.speedX = -this.speedX;
	
	}else if(this.cordinateY <= 0) {
		this.cordinateY = 0;
		this.speedY = -this.speedY;
	}else if(this.cordinateY >= 1000) {
		this.cordinateY = 1000;
		this.speedY = -this.speedY;
	}
}
public void draw(PApplet window,PImage d) {
	window.ellipse(cordinateX,cordinateY,sizeD,sizeD);
}
public void gravity() {
//	this.speedY = this.speedY + 1;
}
public void changeColor(int c,PApplet window) {
	window.fill(c);	
}
}
