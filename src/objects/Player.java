package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.ID;
import engine.InputManager;

public class Player extends GameObject {

	private int speed = 5;

	
	public Player(int x, int y, ID id, BufferedImage sprite) {
		super(x, y, id, sprite);
	}

	public void tick() {
		x += velocityX;
		y += velocityY;

		keyboardInput();
	}

	public void keyboardInput() {
		int horizontalSign = InputManager.getKeyPressedInt("D") - InputManager.getKeyPressedInt("A");
		int verticalSign = InputManager.getKeyPressedInt("S") - InputManager.getKeyPressedInt("W");

		velocityX = speed * horizontalSign;
		velocityY = speed * verticalSign;
	}

	public void render(Graphics g) {
		super.render(g);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}
