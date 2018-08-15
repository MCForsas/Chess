package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;

public class Knight extends Piece{

	public Knight(int row, int col, BufferedImage sprite, Board board, Game game, BoardState color) {
		super(row, col, ID.PIECE, sprite, board, game, color);
	}
 
	public void tick() {
		for(int i = 0; i < board.getBoardSize(); i++) {
			for(int j = 0; j < board.getBoardSize(); j++) {
				if(isValidPath(i, j)) {
					board.setValidPath(i, j, BoardState.VALID);
				}
			}
		}
		board.setBoardState(row, col, color);
	}
	

	protected boolean isValidPath(int row, int col) {
		BoardState state = board.getBoardState(row, col);
		if(state != color && state != BoardState.INVALID) {
			if( (row == this.row - 2 && col == this.col -1)||
				(row == this.row - 2 && col == this.col + 1) ||
				(row == this.row + 1 && col == this.col - 2)||
				(row == this.row - 1 && col == this.col + 2) ||
				(row == this.row + 2 && col == this.col -1)||
				(row == this.row + 2 && col == this.col + 1) ||
				(row == this.row - 1 && col == this.col - 2)||
				(row == this.row + 1 && col == this.col + 2)
			) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void move(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
