package objects;

import java.awt.image.BufferedImage;

import engine.ID;

public abstract class Piece extends GameObject {
	public Piece(float x, float y, ID id, BufferedImage sprite) {
		super(x, y, id,sprite);
	}
}
