package gamemaster;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import npcs.Rabbit;

import maps.Background;
import maps.Tile;
import maps.TileMap;

import entity.NPC;
import entity.Player;
import game.GamePanel;

/**
 * A level to test everything
 * @author User
 *
 */
public class LevelOneState extends GameState {

	private TileMap tileMap;
	private maps.Background bg;
	private Player player;	
	private Rabbit rabbit;
	
	
	/**
	 * variables for saves
	 */
	private double tilemapX, tilemapY, playerX, playerY, backgrX, backgrY;
	
	
	/**
	 * Constructor
	 * @param gm - this level's game master
	 */
	public LevelOneState(GameMaster gm){
		this.gm = gm;
		init();
	}
	
	/**
	 * initialize everytihng that is going to be in this level
	 * can later be used as load function
	 */
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("Resources/Tilesets/grasstileset.gif");
		tileMap.loadMap("Resources/Maps/example.map");
		tileMap.setPosition(0, 0);
		bg = new Background("Resources/Backgrounds/asd.jpg", 0.1);
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		rabbit = new Rabbit(tileMap);
		rabbit.setPosition(400, 400);

	}
	
	public void save() {
		
		tilemapX = tileMap.getX();
		tilemapY = tileMap.getY();
		
		playerX = player.getX();
		playerY = player.getY();
		
		backgrX = bg.getX();
		backgrY = bg.getY();

	}
	
	public void load() {
		
		tileMap.setPosition(tilemapX, tilemapY);
		player.setPosition(playerX, playerY);
		bg.setPosition(backgrX, backgrY);
		
	}


	/**
	 * update everytihng in this state
	 */
	public void update() {
		
		player.update();
				
		tileMap.setPosition(GamePanel.WIDTH/2 - player.getX(), GamePanel.HEIGHT/2 - player.getY());
				
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		rabbit.update();

		if(player.checkInteraction(rabbit))
			gm.setState(2);
		
	}


	/**
	 * draw everything in this state
	 */
	public void draw(Graphics2D g) {
		
		bg.drawStatic(g);
		
		tileMap.draw(g);
		
		rabbit.draw(g);
		
		player.draw(g);
		
	}

	/**
	 * set the boolean in the player class for the move made
	 */
	public void keyPressed(int k) {
		
		if (k == KeyEvent.VK_SPACE) player.setRunning(true);
		if( k == KeyEvent.VK_LEFT) player.setLeft(true);
		if( k == KeyEvent.VK_RIGHT) player.setRight(true);
		if( k == KeyEvent.VK_UP) player.setUp(true);
		if( k == KeyEvent.VK_DOWN) player.setDown(true);

	}

	
	public void keyReleased(int k) {
		if( k == KeyEvent.VK_SPACE) player.setRunning(false);
		if( k == KeyEvent.VK_LEFT) player.setLeft(false);
		if( k == KeyEvent.VK_RIGHT) player.setRight(false);
		if( k == KeyEvent.VK_UP) player.setUp(false);
		if( k == KeyEvent.VK_DOWN) player.setDown(false);

	}

}
