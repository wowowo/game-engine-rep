package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import maps.TileMap;

/**
 * the Player class
 * @author User
 *
 */
public class Player extends Entity {

	/**
	 * the sprites list holds the image arrays for the animations
	 * the numFrames is also the length of those arays and how many
	 * frames are there in a certain animation its also used to calculate
	 * the number of animations
	 */
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 11, 6};
	public boolean running = false;

	/**
	 * the first array in the list is the animation when the player is idle
	 * the second is when he is walking left and right
	 */
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int RUNNING = 2;
	private static final int INBATTLE = 3;


	/**
	 * the constructor
	 * @param tm - which the map the player is going to be drawn on
	 */
	public Player(TileMap tm) {
		
		super(tm);
		
		/**
		 * the length and width of the character model
		 * cwidth and cheight are the width and height used to 
		 * calculate collisions
		 */
		width = 100;
		height = 134;
		cwidth = 100;
		cheight = 120;
		

		moveSpeed = 1.6;
		
		/**
		 * this is used to flip the animation to the left
		 */
		facingRight = true;

		/**
		 * this populates the animation arrays
		 */
		try {
			/**
			 * read the the image that contains the animations
			 */

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
			
			bi =  new BufferedImage[numFrames[2]];
			source = "Resources/test/theRabbits/Rabbit/run/";
			for(int i = 0; i < numFrames[2]; i++) 
					bi[i] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
						
			sprites.add(bi);
			

			
			
			/**
			 * for each thing specified in numFrames create a new array of images
			 * corresponding to one animation
			 */
//			for (int i = 0; i < numFrames.length; i++) {
//				
//				BufferedImage[] bi = new BufferedImage[numFrames[i]];
//				/**
//				 * get as many images as specified in number of frames
//				 */
//				for (int j = 0; j < numFrames[i]; j++) 
//						bi[j] = spriteSheet.getSubimage(j * width, i * height,
//								width, height);
//
//				/**
//				 * add the populated array to the list of animation arrays
//				 */
//				sprites.add(bi);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * set the default animation to idle
		 * 
		 */
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(800000);

	}



	public void update() {

		/**
		 * calculate the next position and check for collisions
		 */
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);

		/**
		 * if walking and the walking animation hasnt started
		 * change to start the walking animation
		 */
		 if (left || right || up || down) {
			 if(running) {
				 if (currentAction != RUNNING) {
						currentAction = RUNNING;
						animation.setFrames(sprites.get(RUNNING));
						animation.setDelay(70);
						width = 30;
				 }
			 }
			else if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(70);
				width = 30;
			}
		}

		 /**
		  * if no button is pressed start the idle animation
		  */
		else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		 /**
		  * get the next frame from current animation
		  */
		animation.update();

	}

	/**
	 * move the character left,right up or down
	 */
	private void getNextPosition() {

		if (left) {
			facingRight = false;
			if(running) 
			dx = -moveSpeed - 2.7;
			else dx = -moveSpeed;
		}

		else if (right) {
			facingRight = true;
			if(running) 
				dx = moveSpeed + 2.7;
			else dx = moveSpeed;
		}
		
		else if(up) {
			if(running) 
				dy = -moveSpeed - 2.7;
			else dy = -moveSpeed;
		}
			
		else if(down){
			if(running) 
				dy = moveSpeed + 2.7;
			else dy = moveSpeed;
		}
		
		else { 
			dy = 0;
			dx = 0;
		}
		
	}

	/**
	 * move the map
	 * call the super draw
	 */
	public void draw(Graphics2D g) {

		setMapPosition();

		if (facingRight)
			g.drawImage(animation.getImage(), 
					(int) (x + xmap - width / 2),
					(int) (y + ymap - height / 2), null);
		
		else
			g.drawImage(animation.getImage(),
					(int) ((x + xmap - width / 2) + width),
					(int) (y + ymap - height / 2), -3 * width, height, null);

	}


	private void interact() {
		
		System.out.println("interacting");
	}

	public void setRunning(boolean b) {
		
		running = b;
		
	}
	
	public boolean checkInteraction(Entity e) {
	
		if(this.intersect(e))
			return true;
		return false;
		
	}

	

}
