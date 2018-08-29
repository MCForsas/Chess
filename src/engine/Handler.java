package engine;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.GameObject;

/*
 * Handles the game objects: ticks them, renders, adds or removes them
 * @author MCForsas 2018
 */

public class Handler{

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	/*
	 * Return game object list
	 */
	public LinkedList<GameObject> getObject() {
		return this.object;
	}

	/*
	 * Tick game objects
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			this.object.get(i).tick();;
		}
	}

	/*
	 * Render game objects
	 * @param Graphics g graphics object, used for rendering
	 */
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/*
	 * Add game object to list
	 * @param GameObject object 
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/*
	 * Remove game object from list
	 * @param GameObject object 
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	/*
	 * Remove all game objects
	 */
	public void clear() {
		this.object.clear();
	}
}
