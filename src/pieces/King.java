package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;

public class King extends Piece{

	public King(int row, int col, BufferedImage sprite, Board board, Game game, BoardState color) {
		super(row, col, ID.PIECE, sprite, board, game, color);
	}	

	protected boolean isValidPath(int finalRow, int finalCol) {
		BoardState state = board.getBoardState(row, col);
		if(state != color && state != BoardState.INVALID) {
			if( (row == this.row+1 && col == this.col+1) ||
				(row == this.row+1 && col == this.col-1) ||
				(row == this.row-1 && col == this.col+1) ||
				(row == this.row-1 && col == this.col-1) ||
				(row+1 == this.row+1 && col+1 == this.col) ||
				(row+1 == this.row+1 && col-1 == this.col) ||
				(row+1 == this.row && col+1 == this.col+1) ||
				(row-1 == this.row && col+1 == this.col+1)
				
				) {
				return true;
			}
		}
		return false;
	}

	/*private boolean isCapturePath(int row, int col) {
		BoardState state = board.getBoardState(row, col);
		if(state == BoardState.WHITE){
			if(state != color && state != BoardState.INVALID) {
				if((row == this.row+sign && col == this.col+1) || (row == this.row+sign && col == this.col-1)) {
				return true;
				}
			}
		}else if(state == BoardState.BLACK) {
			if(state != color && state != BoardState.INVALID) {
				if((row == this.row+sign && col == this.col+1) || (row == this.row+sign && col == this.col-1)) {
				return true;
				}
			}
		}
		return false;
	}*/
	
	@Override
	public void move(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
