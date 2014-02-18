package game;

import gamemaster.GameMaster;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * One of the main classes It is the actual canvas on which everything is drawn.
 * It listens for key input as well as updates and renders the JFRAME
 * 
 * @author User
 * 
 */

public class GamePanel extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3315030997963259802L;
	// sizes
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	private static final int SCALE = 2;

	// runnable
	private Thread animator;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	private BufferedImage image;
	private Graphics2D g;

	private GameMaster gm;

	/**
	 * Constructor Sets the size and asks for focus (makes it the selected
	 * window)
	 */
	public GamePanel() {

		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	/**
	 * Listens for keyboard input and sends the input to the GameMaster class
	 */
	public void keyPressed(KeyEvent k) {
		gm.keyPressed(k.getKeyCode());
	}

	/**
	 * Listens for keyboard input and sends the input to the GameMaster class
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		gm.keyReleased(k.getKeyCode());

	}

	/**
	 * does nothing, but has to be implemented
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * gets the graphical context and draws to the screen standard java library,
	 * not know how it works extensively
	 */
	private void drawToScreen() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * 2, HEIGHT * 2, null);
		g2.dispose();
	}

	/**
	 * just initializes all the variables before the game starts
	 */
	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		running = true;
		gm = new GameMaster();
	}

	/**
	 * caryy out all of the gameMasters updates
	 */
	private void update() {
		gm.update();
	}

	/**
	 * give the GameMaster a thing to draw on
	 */
	private void draw() {
		gm.draw(g);
	}

	/**
	 * Start the game. While the game is running update all the game logic and
	 * draw it if it carries out more than 60 drawings for one second stop
	 * drawing for the surplus time
	 */
	public void run() {
		init();

		long start, elapsed, wait;

		while (running) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * When an instances of this class is created the frame window starts to
	 * check for keyboard input, while this thing works draws the game. Then it
	 * starts a new thread with the run method in this class
	 */
	public void addNotify() {

		super.addNotify();

		if (animator == null) {
			animator = new Thread(this);
			addKeyListener(this);
			animator.start();
		}

	}

}
