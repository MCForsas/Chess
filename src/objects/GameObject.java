package objects;

import java.awt.Graphics;

public abstract class GameObject {

	protected int x, y;

	/*
	 * Constructor for game objects
	 * @param {float} x x cooridinate of the object
	 * @param {float} y y coordinate of the object
	 * @param {ID} id id of the object
	 * @param {BufferedImage} sprite object's sprite
	 */
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Tick
	 */
	public abstract void tick();

	/*
	 * Just draw object's sprite by default
	 * @param {Graphics} g
	 */
	public void render(Graphics g) {};

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
