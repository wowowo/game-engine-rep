package npcs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Animation;
import entity.NPC;

import maps.TileMap;

public class Rabbit extends NPC {
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 11};
	
	private static final int WALKING = 1;

	public Rabbit(TileMap tm) {
		
		super(tm);
		
		width = 100;
		height = 134;
		cwidth = 100;
		cheight = 120;
		enemy = true;

		moveSpeed = 1.6;
		
		facingRight = true;
		
		try {

		
		sprites = new ArrayList<BufferedImage[]>();
		BufferedImage[] bi = new BufferedImage[numFrames[0]];
		bi[0] = ImageIO.read(new File("Resources/test/theRabbits/Rabbit/Rabbit.png"));
		sprites.add(bi);

		bi =  new BufferedImage[numFrames[1]];
		String source = "Resources/test/theRabbits/Rabbit/walk/";
		for(int i = 0; i < 11; i++) 
			
			if(i < 9)
				bi[i] = ImageIO.read(new File(source + "00" + (i+1) + ".png"));
		
			else bi[i] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
		
		sprites.add(bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		left = true;
		animation = new Animation();
		currentAction = WALKING;
		animation.setFrames(sprites.get(WALKING));
		animation.setDelay(70);

	}
	
	public void update() {
		
		updatePosition();
		
		if(left && dx==0){
			left = false;
			right = true;
			facingRight = true;
		}
		
		else if(right && dx==0){
			right = false;
			left = true;
			facingRight = false;
		}
		
		animation.update();

	}

	/**
	 * need reworking the not on screen
	 * dont use it
	 * 
	 */
	public void draw(Graphics2D g) {
		
		
	//	if(notOnScreen()) return;
			setMapPosition();
			super.draw(g);
	}

}
