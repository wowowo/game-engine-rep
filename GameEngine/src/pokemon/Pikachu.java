package pokemon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Animation;

public class Pikachu extends Pokemon {

	/**
	 * load animations and attacks
	 */
	public Pikachu() {
		
		setName("Pikachu");
		animation = new Animation();
		BufferedImage[] bi = new BufferedImage[1];
		
		attacks.add(new Attack("Slide"));
		attacks.add(new Attack("Punch"));
		
		
		try {
			bi =  new BufferedImage[11];
			String source = "Resources/test/theRabbits/Rabbit/walk/";
			for(int i = 0; i < 11; i++) 
				
				if(i < 9)
					bi[i] = ImageIO.read(new File(source + "00" + (i+1) + ".png"));
			
				else bi[i] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
			
			sprites.add(bi);
			
			bi =  new BufferedImage[16];
			source = "Resources/test/theRabbits/Rabbit/run_jump/";
			for(int i = 0; i < 9; i++) 
				bi[i] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
			 
			source = "Resources/test/theRabbits/Rabbit/duck/";
			for(int i = 0; i < 7; i++) 
				bi[i+9] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
			
			attacks.get(0).setAnimation(bi);
			
			bi =  new BufferedImage[6];
			source = "Resources/test/theRabbits/Rabbit/run_carry/";
			for(int i = 0; i < 6; i++) 
				bi[i] = ImageIO.read(new File(source + "0" + (i+1) + ".png"));
					
			BufferedImage[] a = new BufferedImage[16];
			for(int i = 0; i < 16; i++)
				if(i < 6)
					a[i] = bi[i];
				else if(i < 12) 
					a[i] = bi[i-6];
				else a[i] = bi[i-12];

			attacks.get(1).setAnimation(a);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation.setFrames(sprites.get(0));
		animation.setDelay(70);
	}

	@Override
	public void update() {
		

		/**
		 * if the monster is in action move to the left
		 * if the animation has ended start the idle animation
		 */
		if(inAction && animation.hasPlayedOnce()) {
			inAction = false;
			animation.setFrames(sprites.get(0));
			animation.setDelay(70);
		}
		
		else if(inAction) {
			setPosition(x - 2.8, y);
		}
			
		animation.update();
	}



	public String getName() {
		return name;
	}

	/**
	 * start the attack animation
	 */
	public void attack(int a) {

		inAction = true;
		animation.setFrames(attacks.get(a).getAnimation());
		animation.setDelay(70);
		
	}

	
}
