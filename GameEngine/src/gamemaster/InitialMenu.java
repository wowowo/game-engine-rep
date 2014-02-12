package gamemaster;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import maps.Background;

/**
 * The default state. It has a background and the menu options;
 * 
 * @author User
 * 
 */
public class InitialMenu extends GameState {

	private String gameName = "RPG GAME";

	private Background bg;

	private String[] options = { "Start", "Load", "Quit" };
	private int currentChoice = 0;

	private Color titleColor;
	private Font titleFont;

	private Font font;

	/**
	 * The Constructor initializes the background and the text messages
	 * 
	 * @param gm
	 *            - GameMaster
	 *            
	 *            
	 */
	public InitialMenu(GameMaster gm) {

		this.gm = gm;

		try {

			bg = new Background("Resources/Backgrounds/asd.gif", 1);
			bg.setVector(0, -0.4);

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);

			font = new Font("Arial", font.PLAIN, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		init();
	}

	@Override
	public void init() {

	}

	@Override
	/**
	 * The update for this state is 
	 * simply to update the background
	 */
	public void update() {
		bg.update();
	}

	@Override
	/**
	 * draw the background
	 * draw the title
	 * for each option if it is highlighted draw with red fond, else draw with black
	 * 	
	 */
	public void draw(Graphics2D g) {

		bg.draw(g);

		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(gameName, 80, 70);

		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice)
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);

			g.drawString(options[i], 145, 140 + i * 15);
		}

	}

	/**
	 * if enter is pressed select the highlited option if up is pressed go up
	 * the menu, if on the top option, goto the bottom same with down
	 */
	public void keyPressed(int k) {
		
		if (k == KeyEvent.VK_ENTER)
			select();

		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice < 0)
				currentChoice = options.length - 1;
		}

		if (k == KeyEvent.VK_DOWN) 
			currentChoice = (currentChoice + 1) % options.length;


	}

	/**
	 * specifies what will happen when a option is highlighted
	 */
	private void select() {
		if (currentChoice == 0) {
			gm.setState(1);
		}

		if (currentChoice == 1) {
			gm.setState(2);
		}

		if (currentChoice == options.length - 1) {
			System.exit(0);
		}

	}

	/**
	 * empty implementation
	 */
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
