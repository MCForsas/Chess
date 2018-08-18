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

public class Knight extends Piece{

	public Knight(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			sprite = spriteSheet.grabImage(1, 3, 32, 32);
		}else {
			sprite = spriteSheet.grabImage(3, 3, 32, 32);
		}
		this.pieceType = PieceType.Knight;
	}
	
	protected boolean isValidEndPoint(int finalX, int finalY) {
		int diffX = Math.abs(finalX - x);
		int diffY = Math.abs(finalY - y);
		if((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)) {
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		/*Piece piece = this.board.getPiece(finalX, finalY);
		if(piece != null) {
			//if(piece.getPlayer() == this.player) {
				return false;
			//}
		}*/
		return true;
	}
}
