package gamemaster;

/**
 * Super class that makes specifies the interface for all the game states
 * 
 * @author User
 * 
 */
public abstract class GameState {

	/**
	 * reference to the game master
	 */
	protected GameMaster gm;

	/**
	 * Initialize all the state's objects such as
	 * enemies, players and tiles.
	 */
	public abstract void init();

	/**
	 * carry out the technical updates
	 */
	public abstract void update();

	/**
	 * draw to the buffer
	 * @param g
	 */
	public abstract void draw(java.awt.Graphics2D g);

	/**
	 * which key is pressed 
	 * @param k - numerical value of key pressed
	 */
	public abstract void keyPressed(int k);

	/**
	 * which key is released
	 * @param k - numerical value of key released
	 */
	public abstract void keyReleased(int k);

}
