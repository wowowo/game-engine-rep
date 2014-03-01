package game;

import javax.swing.JFrame;

/**
 * The design is based on Killer Game Programming with Java by Andrew Davison. Copyright 2005 O’Reilly Media, Inc., 0-596-00730-2.
 * the resources are from a youtube game video
 * This basically create a JFrame(container window) and adds the game panel,
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
		
	}

}
