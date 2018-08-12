package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import engine.ID;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velocityX, velocityY;
	protected BufferedImage sprite;

	/*
	 * Constructor for game objects
	 * @param {float} x x cooridinate of the object
	 * @param {float} y y coordinate of the object
	 * @param {ID} id id of the object
	 * @param {BufferedImage} sprite object's sprite
	 */
	public GameObject(float x, float y, ID id, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.sprite = sprite;
	}

	/*
	 * Tick
	 */
	public abstract void tick();

	/*
	 * Just draw object's sprite by default
	 * @param {Graphics} g
	 */
	public void render(Graphics g) {
		if (this.sprite != null) {
			g.drawImage(sprite, (int) (x - sprite.getWidth() / 2), (int) (y - sprite.getHeight() / 2), null);
		}
	}

	/*
	 * Get keyboard input
	 */
	protected void keyboardInput() {

	}

	/*
	 * get collision mask's bounds
	 */
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

}
