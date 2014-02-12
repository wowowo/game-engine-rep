package entity;

import game.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import maps.Tile;
import maps.TileMap;

/**
 * THe entity class, which NPCs and player will extend
 * @author User
 *
 */
public abstract class Entity {
	
	/**
	 * reference to the map this will be drawn on
	 */
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap, ymap;

	protected double x, y, dx, dy;

	
	protected int width, height, cwidth, cheight;

	protected int currRow, currCol;

	protected double xdest, ydest, xTemp, yTemp;

	protected boolean topLeft, topRight, bottomLeft, bottomRight;

	protected Animation animation;
	protected int currentAction;
	protected boolean facingRight;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	protected double moveSpeed;

	/**
	 * constructor
	 * @param tm
	 */
	public Entity(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	/**
	 * check to see if it intersects with another entity
	 * @param o
	 * @return
	 */
	public boolean intersect(Entity o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	/**
	 * 
	 * @return a rectangle made of the coordinates and the collision stats
	 */
	public Rectangle getRectangle() {
		return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth,
				cheight);
	}


	/**
	 * checks for collision
	 */
	public void checkTileMapCollision() {
		
		/**
		 * get the row and column
		 */
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;

		/**
		 * get the future coords
		 */
		xdest = x + dx;
		ydest = y + dy;

		xTemp = x;
		yTemp = y;

		/**
		 * check if there blocked tiles on the y coordinate
		 */
		calculateCorners(x, ydest);

		/**
		 * if there is a block top left or top right
		 * dont move, else move
		 */
		if (dy < 0) {
			if (topRight || topLeft) 
				dy = 0;

			yTemp += dy;
		}

		if (dy > 0) {
			
			if (bottomRight || bottomLeft) 
				dy = 0;

			yTemp += dy;
		}

		/**
		 * check if there blocked tiles on the y coordinate
		 */
		calculateCorners(xdest, y);
		
		if (dx < 0) {
			
			if (topLeft || bottomLeft) 
				dx = 0;

			xTemp += dx;
		}

		if (dx > 0) {
			
			if (topRight || bottomRight) 
				dx = 0;
			
			xTemp += dx;
			
		}


	}
	
	/**
	 * check if the adjacent tiles are blocked
	 * @param x
	 * @param y
	 */
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;
		
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);

		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;

	}

	/**
	 * 
	 * getters for coordinates, collision measures and
	 * width and height
	 * 
	 */
	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCWidth() {
		return cwidth;
	}

	public int getCHeight() {
		return cheight;
	}

	/**
	 * set position relative to window
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * get the current map coords
	 */
	public void setMapPosition() {
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}

	/**
	 * 
	 * Setters for the movement
	 */
	
	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setUp(boolean b) {
		up = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	/**
	 * 
	 * @return whether the entity is on the screen
	 */
	public boolean notOnScreen() {
		return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH
				|| y + ymap + height < 0
				|| y + ymap - height > GamePanel.HEIGHT;
	}

	/**
	 * get the current frame of the animation and draw it 
	 * @param g - the buffer on which to draw 
	 */
	public void draw(Graphics2D g) {
		
		if (facingRight)
			g.drawImage(animation.getImage(), 
					(int) (x + xmap - width / 2),
					(int) (y + ymap - height / 2), null);
		
		else
			g.drawImage(animation.getImage(),
					(int) ((x + xmap - width / 2) + width),
					(int) (y + ymap - height / 2), -width, height, null);
	}

	public void print() {
		System.out.println( xmap + "    " + ymap);
	}
}
