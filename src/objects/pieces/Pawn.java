package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * Pawn piece, can move like itself in chess
 * @author MCForsas 2018
 */

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
	
	@Override
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
		
		//Check if moves backwards
		if(this.getPlayer().getColor() == Color.WHITE) {
			if(finalY > this.y) {
				return false;
			}
		}else if(this.getPlayer().getColor() == Color.BLACK) {
			if(finalY < this.y) {
				return false;
			}
		}
		
		//Check if diagonal tile is occupied and if so, allow to capture
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
		
		//If is moved for 2 tiles forward
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
