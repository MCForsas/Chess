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
		Piece piece;
		if(this.getPlayer().getColor() == Color.WHITE) {
			if(finalY > this.y) {
				return false;
			}
		}else if(this.getPlayer().getColor() == Color.BLACK) {
			if(finalY < this.y) {
				return false;
			}
		}
		if(finalX != this.x) {
			piece = board.getPiece(finalX, finalY);
			if(piece == null) {
				return false;
			}
		}else {
			piece = board.getPiece(finalX, finalY);
			if(piece != null) {
				return false;
			}
		}
		if(Math.abs(finalY-this.y) == 2) {
			if(finalY > this.y) {
				piece = board.getPiece(finalX, this.y+1);
			}
			if(finalY < this.y) {
				piece = board.getPiece(finalX, this.y-1);
			}
			if(piece != null) {
				return false;
			}
		}
		return true;
	}
}
