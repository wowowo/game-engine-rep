package maps;

import java.awt.image.BufferedImage;

/**
 * The tile class sets the image for every tile
 * and has information whether the tile is accessible
 * @author User
 *
 */
public class Tile {
	private BufferedImage image;
	private int type;

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;

	/**
	 * 
	 * @param image - the image on that particular tile
	 * @param type - whether the tile is accessible 
	 * 
	 */
	public Tile(BufferedImage image, int type) {

		this.image = image;
		this.type = type;

	}

	/**
	 * 
	 * @return the image associated with the tile
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 
	 * @return the type of the tile
	 */
	public int getType() {
		return type;
	}

}
