package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.Handler;
import engine.ID;

public class Enemy extends GameObject {

	
	public Enemy(int x, int y, ID id, Handler handler, BufferedImage sprite) {
		super(x, y, id, sprite);
		velocityX = 8;
		velocityY = 8;
	}

	public void tick() {
		x += velocityX;
		y += velocityY;

		if (x <= 0 || x >= Game.WIDTH - 32)
			velocityX *= -1;
		if (y <= 0 || y >= Game.HEIGHT - 48)
			velocityY *= -1;
	}

	public void render(Graphics g) {
	}

	public Rectangle getBounds() {
		return new Rectangle();
	}
}
