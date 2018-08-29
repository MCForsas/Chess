package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * Knight piece, can move like itself in chess
 * @author MCForsas 2018
 */

public class Knight extends Piece{

	public Knight(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			this.sprite = this.spriteSheet.grabImage(1, 3, 32, 32);
		}else {
			this.sprite = this.spriteSheet.grabImage(3, 3, 32, 32);
		}
		this.pieceType = PieceType.Knight;
	}
	
	@Override
	protected boolean isValidEndPoint(int finalX, int finalY) {
		int diffX = Math.abs(finalX - this.x);
		int diffY = Math.abs(finalY - this.y);
		if((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)) {
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		return true;
	}
}
