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

public class King extends Piece{

	public King(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			sprite = spriteSheet.grabImage(2, 2, 32, 32);
		}else {
			sprite = spriteSheet.grabImage(4, 2, 32, 32);
		}
		this.pieceType = PieceType.King;
	}

	protected boolean isValidEndPoint(int finalX, int finalY)  {
		int diffX = Math.abs(finalX - x);
		int diffY = Math.abs(finalY - x);
		return diffX < 2 && diffY < 2 && !(diffX + diffY == 0);
	}

	protected boolean isValidMove(int finalX, int finalY) {
		/*Piece piece = this.board.getPiece(finalX, finalY);
		if(piece != null) {
			if(piece.getPlayer() == this.player) {
				return false;
			}
		}*/
		return true;
	}
}
