import processing.core.PApplet;
public class Test extends PApplet{
	private int width = 600;
	private int height = 600;
	int c;
	public void setup() {
		size(width,height);
	}
	public void draw() {
		background(0);
		int pink = color(255, 0, 0);
//		loadPixels();
//		for (int i = 0; i < (width*height/2)-width/2; i++) {
//		  pixels[i] = pink;
//		}
//		updatePixels();
		loadPixels();
		for(int i = 0; i < (width*height);i++) {
//			int loc = i + 4 * width;
			pixels[i] = pink;
		}
		updatePixels();

	}
}
