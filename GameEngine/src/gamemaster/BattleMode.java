package gamemaster;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import pokemon.Pikachu;
import pokemon.Pokemon;

import entity.Entity;

import maps.Background;
import maps.TileMap;

/**
 * Generic battle, might become abstract later on
 * needs turn mechanics. Animations only change x coord!
 * @author User
 *
 */
public class BattleMode extends GameState {

	private Background bg;
	private ArrayList<Pokemon> enemies;
	private ArrayList<Pokemon> friendlies;
	private Font font;
	private int currentChoice = 0;
	private int action = 0;
	private int chosen = 0;
	private boolean inAction = false;
	private boolean inBattle = false;
	private ArrayList<String> options = new ArrayList<String>();
	private ArrayList<String> fnames = new ArrayList<String>();
	private ArrayList<String> enames = new ArrayList<String>();

	/**
	 * Set up everything, not dependent on number of
	 * entities on map
	 * @param gm
	 * @param friendlies
	 * @param enemies
	 */
	public BattleMode(GameMaster gm, ArrayList<Pokemon> friendlies,
			ArrayList<Pokemon> enemies) {

		this.gm = gm;
		this.bg = new Background("Resources/Backgrounds/asd.jpg", 0.0);
		this.enemies = enemies;
		this.friendlies = friendlies;
		font = new Font("Arial", font.PLAIN, 12);
		setUpMonsters();

	}

	/**
	 * set up the entities
	 * set the available options to the names of the player's monsters
	 */
	private void setUpMonsters() {

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setPosition(10, 10 + i*100);
			enames.add(enemies.get(i).getName());
		}

		for (int i = 0; i < friendlies.size(); i++) {
			friendlies.get(i).setPosition(300, 10 + i*100);
			fnames.add(friendlies.get(i).getName());
		}

		options = fnames;

	}

	/**
	 *  testing player movement and actions
	 */
	public void update() {

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setPosition(-5, i*70  + 10);
			enemies.get(i).update();
		}

		for (int i = 0; i < friendlies.size(); i++) {
			
			if (!friendlies.get(i).inAction())
				friendlies.get(i).setPosition(300, 10 + i*70);
			
			friendlies.get(i).update();
		}

	}

	/**
	 * draw the background;
	 * draw each enemy and player monster
	 * then draw the available options to the player
	 * in the corresponding position
	 */
	public void draw(Graphics2D g) {

		bg.drawStatic(g);

		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).draw(g);

		for (int i = 0; i < friendlies.size(); i++)
			friendlies.get(i).draw(g);

		/**
		 * draw  a gray rectangle, later we should change it to a dialog box
		 */
		g.setColor(Color.GRAY);
		g.fillRect(0, 180, 400, 200);

		g.setColor(Color.WHITE);

		/**
		 * if the player does not attack
		 * draw on the right side of the screen
		 */
		int x = 250;

		/**
		 * otherwise draw them on the left
		 */
		if (inBattle)
			x = 0;

		/**
		 * draw the highlited option in red. the rest in white
		 */
		for (int i = 0; i < options.size(); i++) {
			if (currentChoice == i) {
				g.setColor(Color.RED);
				g.drawString(options.get(i), x, 200 + i * 15);
				g.setColor(Color.WHITE);
				continue;
			}
			g.drawString(options.get(i), x, 200 + i * 15);


		}

	}

	/**
	 * read current key
	 */
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_ENTER)
			select();

		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice < 0)
				currentChoice = options.size() - 1;
		}

		if (k == KeyEvent.VK_DOWN)
			currentChoice = (currentChoice + 1) % options.size();

	}

	/**
	 * if enter is pressed
	 */
	private void select() {

		/**
		 * if player selected a monster, the next iteration draw its attacks
		 * remember the monster in currentChoice
		 */
		if (!inBattle && !inAction) {

			chosen = currentChoice;
			currentChoice = 0;
			inAction = true;
			inBattle = false;
			options = friendlies.get(currentChoice).getAttacks();

		}

		/**
		 * if attack is selected, give player options 
		 * to chose an enemy to attack
		 */
		else if (inAction) {

			action = currentChoice;
			currentChoice = 0;
			inBattle = true;
			inAction = false;
			options = enames;
		}

		/**
		 * if an enemy to attack is selected
		 * reset all, and initialize attack animation
		 */
		else if (inBattle) {

			inAction = false;
			inBattle = false;
			inAction = false;
			options = fnames;
			friendlies.get(chosen).attack(action);
		}

	}

	@Override
	public void keyReleased(int k) {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}
