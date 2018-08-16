package objects.pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;
import objects.Player;

public class Pawn extends Piece{

	public Pawn(int x, int y, BufferedImage sprite, Board board, Player player) {
		super(x, y, sprite, board, player);
	}
	
	protected boolean isValidPath(int finalX, int finalY) {
		int diffX = Math.abs(finalX - x);
		int diffY = Math.abs(finalY - x);
		if(moves >= 1) {
			if(diffX == 0 && diffY == 1) {
				return true;
			}
		}else {
			if(diffX == 0 && diffY <= 2) {
				return true;
			}
		}
		return diffX == 1 && diffY == 1;
		//Capture path
	}
}
