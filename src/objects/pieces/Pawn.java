package objects.pieces;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;
import objects.PieceType;
import objects.Player;

public class Pawn extends Piece{

	public Pawn(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			sprite = spriteSheet.grabImage(1, 1, 32, 32);
		}else {
			sprite = spriteSheet.grabImage(3, 1, 32, 32);
		}
		this.pieceType = PieceType.Pawn;
	}
	
	protected boolean isValidEndPoint(int finalX, int finalY)  {
		int sign = finalY - y;
		if(player.getColor() == Color.WHITE && sign >= 0) {
			return false;//Capture path
		}
		int diffX = Math.abs(finalX - x);
		int diffY = Math.abs(finalY - y);
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
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}
}
