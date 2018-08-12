package objects;

import java.awt.image.BufferedImage;

import engine.ID;

public abstract class Piece extends GameObject {
	protected Board board;
	public Piece(float x, float y, ID id, BufferedImage sprite, Board board) {
		super(x, y, id,sprite);
	}
}
