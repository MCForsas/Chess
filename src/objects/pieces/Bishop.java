package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * Bishop piece, can move like itself in chess
 * @author MCForsas 2018
 */
public class Bishop extends Piece{

	public Bishop(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			this.sprite = this.spriteSheet.grabImage(1, 4, 32, 32);
		}else {
			this.sprite = this.spriteSheet.grabImage(3, 4, 32, 32);
		}
		this.pieceType = PieceType.Bishop;
	}
 
	@Override
	protected boolean isValidEndPoint(int finalX, int finalY) {
		int diffX = Math.abs(finalX - this.x);
		int diffY = Math.abs(finalY - this.y);
		return diffX == diffY;
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		if(isMovedToItSelf(finalX, finalY)) {
			return false;
		}
		
		int directionX = 0, directionY = 0;
		int lenght = 0;
		
		if(finalX - this.x > 0) {
			directionX = 1;
		}else {
			directionX = -1;
		}
		lenght = Math.abs(finalX - this.x);

		if(finalY - y > 0) {
			directionY = 1;
		}else {
			directionY = -1;
		}
		
		lenght = Math.abs(finalY - this.y);
		
		//Check if any pieces are in the path, so no jumping over would occur
		for(int i = 1; i < lenght; i++){
			Piece piece = this.board.getPiece(this.x+directionX*i, this.y+directionY*i);
			if(piece != null) {
				return false;
			}
		}
		return true;
	}

	
}
