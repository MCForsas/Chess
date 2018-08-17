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

public class Rook extends Piece{

	public Rook(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			sprite = spriteSheet.grabImage(1, 2, 32, 32);
		}else {
			sprite = spriteSheet.grabImage(3, 2, 32, 32);
		}
		this.pieceType = PieceType.Rook;
	}
	
	protected boolean isValidEndPoint(int finalX, int finalY) {
		return (finalX == this.x || finalY == this.y);
	}

	protected boolean isValidMove(int finalX, int finalY)  {
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
			directionY = 0;
		}
		if(finalX == this.x){
			if(finalY - y > 0) {
				directionY = 1;
			}else {
				directionY = -1;
			}
			directionX = 0;
		}
		lenght = Math.abs(finalX - this.x) + Math.abs(finalY - this.y);
		for(int i = 1; i < lenght+1; i++){
			Piece piece = this.board.getPiece(this.x+directionX*i, this.y+directionY*i);
			if(piece != null) {
				if(piece.getPlayer() == this.player) {
					return false;
				}
			}
		}
		return true;
	}
}
