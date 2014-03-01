package pokemon;

import java.awt.image.BufferedImage;

/**
 * generic class will be abstract in future
 * @author User
 *
 */
public class Attack {
	
	private String name = " asd";
	BufferedImage[] bi;
	
	public Attack(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAnimation(BufferedImage[] bi) {
	
		this.bi = bi;
		
	}
	
	public BufferedImage[] getAnimation() {
		return bi;
	}

}
