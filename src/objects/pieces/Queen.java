package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * Queen piece, can move like itself in chess
 * @author MCForsas 2018
 */

public class Queen extends Piece{
	
	public Queen (int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			sprite = spriteSheet.grabImage(2, 1, 32, 32);
		}else {
			sprite = spriteSheet.grabImage(4, 1, 32, 32);
		}
		this.pieceType = PieceType.Queen;
	}

	@Override
	protected boolean isValidEndPoint(int finalX, int finalY)  {
		 int x_diff = Math.abs(finalX - this.x);
	     int y_diff = Math.abs(finalY - this.y);
	     
	     if((x_diff == y_diff) || (finalX == this.x) || (finalY == this.y)) {
	    	 return true;
	     }

	     return false;
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		if(isMovedToItSelf(finalX, finalY)) {
			return false;
		}

		int directionX = 0, directionY = 0;
		int lenght = 0;
		
		//Check if moves diagonally or horizontally or vertically
		if(finalY == this.y) {
			if(finalX - x > 0) {
				directionX = 1;
			}else {
				directionX = -1;
			}
			lenght = Math.abs(finalX - this.x);
		}else if(finalX == this.x){
			if(finalY - y > 0) {
				directionY = 1;
			}else {
				directionY = -1;
			}
			lenght = Math.abs(finalY - this.y);
		}else {
			
			lenght = Math.abs(finalX - this.x);
			if(finalX - x < 0) {
				directionX = -1;
			}else {
				directionX = 1;
			}
			if(finalY - y < 0) {
				directionY = -1;
			}else {
				directionY = 1;
			}
		}
		
		//Check if piece does jump over other pieces
		for(int i = 1; i < lenght; i++){
			Piece piece = this.board.getPiece(this.x+directionX*i, this.y+directionY*i);
			if(piece != null) {
				return false;
			}
		}
		return true;
	}
}
