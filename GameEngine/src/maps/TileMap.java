package maps;

import game.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

/**
 * The tile map class.
 * @author User
 *
 */
public class TileMap {

	private double x, y;
	private int xmin, ymin, xmax, ymax;

	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;

	private int rowOffset, colOffset, numRowsToDraw, numColsToDraw;

	/**
	 * set the  tile size in the map
	 * draw 2 more tiles than needed
	 * @param tileSize
	 */
	public TileMap(int tileSize) {
		
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH /  tileSize + 2;

	}

	/**
	 * Read the tile image and save it to tileSet
	 * then load the two rows of tiles in the 2d array
	 * @param s- the location from which to load the tiles
	 */
	public void loadTiles(String s) {
		
		try {

			tileSet = ImageIO.read(new File(s));
			/**
			 * how many tiles are in the file, based on the provided size
			 */
			numTilesAcross = tileSet.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			/**
			 * every tile on the first row is passable
			 * every tile on the second row is not 
			 */
			BufferedImage subImage;
			for (int col = 0; col < numTilesAcross; col++) {

				subImage = tileSet.getSubimage(col * tileSize, 0, tileSize,
						tileSize);

				tiles[0][col] = new Tile(subImage, Tile.NORMAL);

				subImage = tileSet.getSubimage(col * tileSize, tileSize,
						tileSize, tileSize);

				tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param s - the location of the map file
	 */
	public void loadMap(String s) {
		try {

			/**
			 * create a scanner to read from the file
			 */
			InputStream in = new FileInputStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			/**
			 * read the first twoo lines which are the nomber of columns and the number of rows
			 */
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());

			/**
			 * create an int array that would later be used to populate the 
			 * map, by beign a reference to tiles
			 * set the height and width of the map
			 */
			map = new int[numRows][numCols];
			height = numRows * tileSize;
			width = numCols * tileSize;

			/**
			 * set the absolute corners
			 */
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;

			/**
			 * read a line from 
			 * the map file
			 */
			for (int row = 0; row < numRows; row++) {

				String line = br.readLine();
				String tokens[] = line.split("[ ]+");

				/**
				 * save the value of each number in its absolute map position
				 */
				for (int col = 0; col < numCols; col++)
					map[row][col] = Integer.parseInt(tokens[col]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * getters
	 *
	 */

	public int getTileSize() {
		return tileSize;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * return the type of a particular 
	 * tile, used in map object's calculate corners </br>
	 * <b>is set for specific tiles loaded</b>
	 * @param row - the row of the tile
	 * @param col - the column of the tile
	 * @return - 0 if tile is passable, 1 if it is blocked
	 */
	public int getType(int row, int col) {
		/**
		 * get the type of the tile from the map representation
		 */
		int rc = map[row][col];
		
		/**
		 * access that number
		 */
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	/**
	 * set which parts of the map should be drawn
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x);
		this.y += (y - this.y);
		
		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;
	}

	/**
	 * block anything that tries to go
	 * out of bounds
	 */
	private void fixBounds() {
		
		if (x < xmin)
			x = xmin;

		if (x > xmax)
			x = xmax;

		if (y < ymin)
			y = ymin;

		if (y > ymax)
			y = ymax;
	}

	public void draw(Graphics2D g) {
		
		/**
		 * find the first row you should be drawing then
		 *  draw each column for that row however
		 *   times is specified by the numRowsToDraw and numColsToDraw variables
		 */
		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
			
			if (row >= numRows)
				break;
			
			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
				if (col >= numCols)
					break;
				
				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize,
						(int) y + row * tileSize, null);

			}

		}
	}


}
