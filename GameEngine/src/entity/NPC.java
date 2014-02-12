package entity;

import maps.TileMap;

public class NPC extends Entity {

	protected boolean dead, enemy;

	public NPC(TileMap tm) {
		super(tm);
	}

	public boolean isDead() {
		return dead;
	}
	
	public boolean isEnemy() {
		return enemy;
	}

	protected void updatePosition() {
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		

	}

	private void getNextPosition() {

		if (left) {
			facingRight = false;
			dx = -moveSpeed;
		}

		else if (right) {
			facingRight = true;
			dx = moveSpeed;
		}

		else if (up)
			dy = -moveSpeed;

		else if (down)
			dy = moveSpeed;

		else {
			dy = 0;
			dx = 0;
		}

	}

}
