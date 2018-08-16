package objects.pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;
import objects.Player;

public class King extends Piece{

	public King(int x, int y, BufferedImage sprite, Board board, Player player) {
		super(x, y, sprite, board, player);
	}

	protected boolean isValidPath(int finalX, int finalY) {
		int diffX = Math.abs(finalX - x);
		int diffY = Math.abs(finalY - x);
		return diffX < 2 && diffY < 2 && !(diffX + diffY == 0);
	}
}
