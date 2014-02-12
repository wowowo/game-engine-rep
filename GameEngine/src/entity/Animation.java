package entity;

import java.awt.image.BufferedImage;

/**
 * The class responsible for managing the animations
 * @author User
 *
 */
public class Animation {
	private BufferedImage[] frames;
	private int currentFrame;

	private long startTime;
	private long delay;

	private boolean playedOnce;

	public Animation() {
		playedOnce = false;
	}

	/**
	 * receive the animation array and record the start time
	 * by default start fromt he first frame
	 * @param frames - the array with the frames that make up the animation
	 */
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}

	/**
	 * set how fast the animation should play
	 * @param d - long
	 */
	public void setDelay(long d) {
		delay = d;
	}

	/**
	 * set the next frame to be played
	 * @param i - Integer
	 */
	public void setFrame(int i) {
		currentFrame = i;
	}

	/**
	 * if enough time has passed from the last frame change
	 * change frames again, otherwise skip
	 */
	public void update() {
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;

		if (elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}

		/**
		 * if the animation has finished
		 * start over and record that it has played once
		 */
		if (currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}
	}

	/**
	 * 
	 * @return the frame number
	 */
	public int getFrame() {
		return currentFrame;
	}

	/**
	 * 
	 * @return the next image in the animation array
	 * to be displayed by the map object class
	 */
	public BufferedImage getImage() {
		return frames[currentFrame];
	}

	/**
	 * 
	 * @return whether the animation has been played
	 */
	public boolean hasPlayedOnce() {
		return playedOnce;
	}

}
