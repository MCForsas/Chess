package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.Piece;

public class Pawn extends Piece{

	public Pawn(int row, int col, BufferedImage sprite, Board board, Game game, PieceColor color) {
		super(row, col, ID.PIECE, sprite, board, game, color);
	}

	public void tick() {
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
