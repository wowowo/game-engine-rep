package game;

import java.awt.Canvas;

import javax.swing.JFrame;

/**
 * This basically create a JFrame(a container window) and adds the game panel,
 * the thing that actually does all the work
 * 
 * @author User
 * 
 */
public class Game {

	/**
	 * MAIN METHOD that starts everything
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame window = new JFrame("RPG");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		System.out.println("working at at last");

	}

}
