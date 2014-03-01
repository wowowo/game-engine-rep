package pokemon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import entity.Animation;

/**
 * generic pokemon superclass
 * @author User
 *
 */
public abstract class Pokemon {

	protected double x, y;

	protected String name;
	protected int width = 80, height = 80;
	protected boolean enemy = false;
	protected boolean inAction;
	protected boolean inTurn;
	

	protected ArrayList<BufferedImage[]> sprites = new ArrayList<BufferedImage[]>();
	protected Animation animation;
	protected int currentAction;
	protected ArrayList<Attack> attacks = new ArrayList<Attack>();


	/**
	 * constructor
	 * 
	 * @param tm
	 */
	public Pokemon() {
	}
	
	public void setEnemy() {
		enemy = true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	/**
	 * set position relative to window
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update();

	public void draw(Graphics2D g) {

		if (enemy)
			g.drawImage(animation.getImage(), (int) (x), (int) (y), width, height, null);

		else
			g.drawImage(animation.getImage(), (int) x, (int) y, -width, height, null);
	}

	public ArrayList<String> getAttacks() {
		
		ArrayList<String> a = new ArrayList<>();
		
		for(Attack e: attacks)
			a.add(e.getName());
		
		return a;
	}
	
	public void startAction() {
		
	}
	
	public boolean inAction() {
		return inAction;
	}
	
	public abstract void attack(int a) ;

}
