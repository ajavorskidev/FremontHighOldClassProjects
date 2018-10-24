import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Game extends PApplet {
	Cactus cactus1, cactus2, cactus3, cactus4, cloud, cloud1;
	Ground ground1, ground2;
	TRex stan;
	PFont text;
	PImage ground;

	public int jumped = 0;
	public int dead = 0;
	public int counter = 0;
	public int bkgrnd = color(150);
	public int floor = 0;
	public int floor2 = 2403;

	ArrayList<Star> starField = new ArrayList<Star>();

	public void setup() {
		size(800, 400);

		text = createFont("Times New Roman", 32);
		textFont(text);
		textAlign(CENTER, CENTER);
		ground = loadImage("ground.png");
		cactus1 = new Cactus(this, "cactus1.png", (int) (Math.random() * 100 + 900), 150, 1);
		cactus2 = new Cactus(this, "cactus2.png", (int) (Math.random() * 100 + 1400), 173, 2);
		cactus3 = new Cactus(this, "cactus3.png", (int) (Math.random() * 75 + 1950), 173, 3);
		cactus4 = new Cactus(this, "cactus4.png", (int) (Math.random() * 30 + 2400), 173, 4);
		cloud = new Cactus(this, "cloud.png", (int) (Math.random() * 30 + 1200), 40, 0);
		cloud1 = new Cactus(this, "cloud.png", (int) (Math.random() * 30 + 1300), 40, 0);
		stan = new TRex(50, 150);
	}

	public void draw() {
		background(bkgrnd);
		// line(0, 200, 800, 200); // draw ground
		image(ground, floor, 200);
		image(ground, floor2, 200);
		cactus1.draw();
		cactus2.draw();
		cactus3.draw();
		cactus4.draw();

		cloud.drawCloud(this);
		cloud1.drawCloud(this);

		if (dead == 0) {
			cactus1.moveLeft();
			cactus2.moveLeft();
			cactus3.moveLeft();
			cactus4.moveLeft();

			cloud.moveLeft();
			cloud1.moveLeft();
			bkgrnd = color(150);
			starField();

			floorGen();

		}
		if (cactus2.getX() < 800 && cactus3.getX() < 800 && cactus4.getX() < 800) {
			cactus1.setPosition((float) (Math.random() * 100 + 900));
		}
		if (cactus1.getX() < 1350 && cactus3.getX() < 1350 && cactus4.getX() < 1350) {
			cactus2.setPosition((float) (Math.random() * 100 + 1400));
		}
		if (cactus1.getX() < 1850 && cactus2.getX() < 1850 && cactus4.getX() < 1850) {
			cactus3.setPosition((float) (Math.random() * 75 + 1950));
		}
		if (cactus1.getX() < 2000 && cactus2.getX() < 2000 && cactus3.getX() < 2000) {
			cactus4.setPosition((float) (Math.random() * 30 + 2400));
		}
		cloud.setPosition((float) (Math.random() * 30 + 1200));
		cloud1.setPosition((float) (Math.random() * 30 + 1300));

		if (dead == 0) {

			stan.TRexJump();
		}
		stan.drawTRex(this);

		onGround();
		collisions();
		retryButton();
		scoreCounter();
	}

	public void keyPressed() {
		if (jumped == 0) {
			stan.setSpeed(12);
			jumped = 1 ;
		}
	}

	public void onGround() {
		if (stan.getYpos() == 150) {
			jumped = 0;
		}
	}

	public void scoreCounter() {
		if (dead == 0) {
			counter++;
			fill(0, 0, 0);
			text("Score:" + counter, 700, 50);
		}
	}

	public void collisions() {
		if (stan.colliding(cactus1)) {
			dead = 1;
		}
		if (stan.colliding(cactus2)) {
			dead = 1;
		}
		if (stan.colliding(cactus3)) {
			dead = 1;
		}
		if (stan.colliding(cactus4)) {
			dead = 1;
		}
		if (dead == 1) {
			stan.drawDeadTRex(this);
			fill(0, 0, 0);
			text("Score:" + counter, 400, 160);
			text("GAME OVER", 400, 200);
			text("RETRY?", 400, 250);
		}
	}

	public void retryButton() {
		if (dead == 1) {
			// fill(255,0,0);
			// rect(103,163,5,5);
			if (mouseX >= 340 && mouseX <= 460 && mouseY >= 240 && mouseY <= 272) {
				fill(255, 0, 0, 40);
				rect(340, 240, 120, 32);
				if (mousePressed) {
					dead = 0;
					counter = 0;

					stan.setX(50);
					stan.setY(150);
					stan.setSpeed(0);

					cactus1.intialX();
					cactus2.intialX();
					cactus3.intialX();
					cactus4.intialX();

					floor = 0;
					floor2 = 2403;
				}
			}
		}
	}

	public void starField() {
		for (int i = 0; i < 150; i++) {
			Star curStar;
			if (i <= 50) {
				 curStar = new Star(1);
				starField.add(curStar);
			}
			if (i <= 100 && i > 50) {
				 curStar = new Star(2);
				starField.add(curStar);
			}
			if (i <= 150 && i > 100) {
				 curStar = new Star(3);
				starField.add(curStar);
			}
		}
		for (int x = 0; x < 150; x++) {
			Star temp = starField.get(x);
//			temp.starY();
			temp.drawStar(this);
			temp.moveStar();
			temp.resetStar();
		}
	}
	
	public void floorGen() {
		floor -= 5;
		floor2 -= 5;
		if(floor2+2402 <= 0) {
			floor2 = 2403;
		}
		if(floor+2402 <= 0 ) {
			floor = 2403;
		}
	}
}