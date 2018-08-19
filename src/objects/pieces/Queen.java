package objects.pieces;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import graphics.SpriteSheet;
import objects.Board;
import objects.BoardState;
import objects.Piece;
import objects.PieceType;
import objects.Player;

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
		
		for(int i = 1; i < lenght; i++){
			Piece piece = this.board.getPiece(this.x+directionX*i, this.y+directionY*i);
			if(piece != null) {
				//if(piece.getPlayer() == this.player) {
					return false;
				//}
			}
		}
		return true;
	}
}
