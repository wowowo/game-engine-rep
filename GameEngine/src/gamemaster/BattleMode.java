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

	public BattleMode(GameMaster gm, ArrayList<Pokemon> friendlies,
			ArrayList<Pokemon> enemies) {

		this.gm = gm;
		this.bg = new Background("Resources/Backgrounds/asd.jpg", 0.0);
		this.enemies = enemies;
		this.friendlies = friendlies;
		font = new Font("Arial", font.PLAIN, 12);
		setUpMonsters();

	}

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

	@Override
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

	@Override
	public void draw(Graphics2D g) {

		bg.drawStatic(g);

		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).draw(g);

		for (int i = 0; i < friendlies.size(); i++)
			friendlies.get(i).draw(g);

		g.setColor(Color.GRAY);
		g.fillRect(0, 180, 400, 200);

		g.setColor(Color.WHITE);

		int x = 250;

		if (inBattle)
			x = 0;

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

	@Override
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

	private void select() {

		if (!inBattle && !inAction) {

			chosen = currentChoice;
			currentChoice = 0;
			inAction = true;
			inBattle = false;
			options = friendlies.get(currentChoice).getAttacks();

		}

		else if (inAction) {

			action = currentChoice;
			currentChoice = 0;
			inBattle = true;
			inAction = false;
			options = enames;
		}

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
