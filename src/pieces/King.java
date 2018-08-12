package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.ID;
import objects.Board;
import objects.Piece;

public class King extends Piece{

	public King(float x, float y, ID id, BufferedImage sprite, int tileWidth, Board board) {
		super(x, y, id, sprite, board);
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
