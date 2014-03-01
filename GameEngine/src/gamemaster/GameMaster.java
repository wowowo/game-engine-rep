package gamemaster;

import java.util.ArrayList;

import pokemon.Pikachu;
import pokemon.Pokemon;

/**
 * The class that is responsible for managing the game screens. It basically
 * controls which state the game is in and what is on the screen at the moment.
 * 
 * @author User
 * 
 */
public class GameMaster {

	/**
	 * this is like an array, but you can delete an add stuff to it
	 */
	private ArrayList<GameState> gameStates;
	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int LEVELONE = 1;


	/**
	 * Constructor that initializes variables and adds all the possible states
	 */
	public GameMaster() {

		gameStates = new ArrayList<GameState>();

		currentState = 0;
		gameStates.add(new InitialMenu(this));
		gameStates.add(new TestLevel(this));
		
		ArrayList<Pokemon> a = new ArrayList<>();
		ArrayList<Pokemon> b = new ArrayList<>();

		Pikachu p = new Pikachu();
		Pikachu pp = new Pikachu();
		a.add(p);
		a.add(pp);
		
		
		Pikachu p1 = new Pikachu();
		p1.setEnemy();
		Pikachu p2 = new Pikachu();
		p2.setEnemy();
		b.add(p1);
		b.add(p2);
		gameStates.add(new BattleMode(this, a, b));

	}

	/**
	 * This can be used so that the game enters battle states instantly im not
	 * sure if it should be added to the states list
	 * 
	 * @param state
	 */
	public void addNewState(GameState state) {
		gameStates.add(state);
		state.init();
	}

	/**
	 * Change the state state in this instance is an integer, usefull for menus
	 * and so on
	 * 
	 * @param state
	 */
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();

	}

	/**
	 * call the update method on the current state
	 */
	public void update() {
		gameStates.get(currentState).update();

	}

	/**
	 * call the draw method on the current state
	 * 
	 * @param g
	 */
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	/**
	 * pass the pressed key to the current state
	 * 
	 * @param k
	 */
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);

	}

	/**
	 * pass the released key to the current state
	 * 
	 * @param k
	 */
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);

	}

}
