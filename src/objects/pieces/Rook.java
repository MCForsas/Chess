package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * Rook piece, can move like itself in chess
 * @author MCForsas 2018
 */
public class Rook extends Piece{

	public Rook(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			this.sprite = this.spriteSheet.grabImage(1, 2, 32, 32);
		}else {
			this.sprite = this.spriteSheet.grabImage(3, 2, 32, 32);
		}
		this.pieceType = PieceType.Rook;
	}
	
	@Override
	protected boolean isValidEndPoint(int finalX, int finalY) {
		return (finalX == this.x || finalY == this.y);
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY)  {
		if(isMovedToItSelf(finalX, finalY)) {
			return false;
		}
		
		int directionX = 0, directionY = 0;
		int lenght = 0;
		
		//Chechk if piece is moved horizontally or vertically
		if(finalY == this.y) {
			if(finalX - this.x > 0) {
				directionX = 1;
			}else {
				directionX = -1;
			}
			directionY = 0;
		}
		if(finalX == this.x){
			if(finalY - this.y > 0) {
				directionY = 1;
			}else {
				directionY = -1;
			}
			directionX = 0;
		}
		lenght = Math.abs(finalX - this.x) + Math.abs(finalY - this.y);
	
		 //Check if piece doesn't jump over other pieces
		for(int i = 1; i < lenght; i++){
			Piece piece = this.board.getPiece(this.x+directionX*i, this.y+directionY*i);
			if(piece != null) {
				return false;
			}
		}
		return true;
	}
}
