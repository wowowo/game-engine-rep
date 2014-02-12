package maps;

import game.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * A background object, a kind of a map that can be used 
 * when in inventory, menus and so on
 * @author User
 *
 */
public class Background {

	private BufferedImage image;
	private int width;
	private int height;

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

	/**
	 * The constructor loads the background image to 
	 * to the image reference
	 * @param s -  the location of the background image
	 * @param ms - how will the background move
	 */
	public Background(String s, double ms) {

		try {
			
			image = ImageIO.read(new File(s));
			width = image.getWidth();
			height = image.getHeight();
			moveScale = ms;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	/**
	 * Set a specific part of the image to be drawn
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		
		this.x = (x * moveScale);
		this.y = (y * moveScale);
		
		fixPosition();
		
	}

	/**
	 * Set the direction in which the background will move
	 * @param dx - minus means move left, plus - move right
	 * @param dy - minus means move up, plus - move down
	 */
	public void setVector(double dx, double dy) {
		
		this.dx = dx;
		this.dy = dy;
		
	}

	/**
	 * Move the background as specified by the setVector method
	 */
	public void update() {
		
		x += dx;
		y += dy;
		fixPosition();
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	/**
	 * CHECK
	 */
	private void fixPosition() {
		
		while (x <= -width)
			x += width;
		
		while (x >= width)
			x -= width;
		
		while (y <= -height)
			y += height;
		
		while (y >= height)
			y -= height;
	}

	/**
	 * Draw the on screen part of the image to the buffer
	 * if that part doesn't cover the whole screen draw the 
	 * left, right, top or bottom part so that the whole screen is covered
	 * @param g - the buffer on which to draw
	 */
	public void draw(Graphics2D g) {

		g.drawImage(image, (int) x, (int) y, null);

		if (x < 0) 
			g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
		
		else if (x > 0) 
			g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
		
		else if(y < 0)
			g.drawImage(image, (int) x, (int) y + GamePanel.WIDTH, null);
		
		else if(y > 0)
			g.drawImage(image, (int) x, (int) y - GamePanel.WIDTH, null);

		
	}
	
	public void drawStatic(Graphics2D g) {
		
		g.drawImage(image, (int) x, (int) y, null);

	}

}
