package objects.pieces;

import java.awt.Color;
import objects.Board;
import objects.Piece;
import objects.PieceType;
import objects.Player;

/*
 * King piece, can move like itself in chess
 * @author MCForsas 2018
 */
public class King extends Piece{

	public King(int x, int y, Board board, Player player) {
		super(x, y, board, player);
		if(player.getColor() == Color.WHITE) {
			this.sprite = this.spriteSheet.grabImage(2, 2, 32, 32);
		}else {
			this.sprite = this.spriteSheet.grabImage(4, 2, 32, 32);
		}
		this.pieceType = PieceType.King;
	}

	@Override
	protected boolean isValidEndPoint(int finalX, int finalY)  {
		int diffX = Math.abs(finalX - this.x);
		int diffY = Math.abs(finalY - this.y);
		return diffX < 2 && diffY < 2 && !(diffX + diffY == 0);
	}

	@Override
	protected boolean isValidMove(int finalX, int finalY) {
		return true;
	}
}
