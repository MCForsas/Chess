package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;

public class Pawn extends Piece{

	public Pawn(int row, int col, BufferedImage sprite, Board board, Game game, BoardState color) {
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
	

	protected boolean isValidPath(int finalRow, int finalCol) {
		BoardState state = board.getBoardState(finalRow, finalCol);
		if(state != color && state != BoardState.INVALID) {
			if(moves >= 1) {
				if(finalRow == this.row+sign && finalCol == this.col) {
					return true;
				}
			}else {
				if((finalRow == this.row+sign && finalCol == this.col) || (finalRow == this.row+sign*2 && finalCol == this.col)) {
					return true;
				}
			}
			if(isCapturePath(finalRow, finalCol)){
				return true;
			}
		}
		
		return false;
	}

	private boolean isCapturePath(int finalRow, int finalCol) {
		BoardState state = board.getBoardState(finalRow, finalCol);
		if(state == BoardState.WHITE){
			if(state != color && state != BoardState.INVALID) {
				if((finalRow == this.row+sign && finalCol == this.col+1) || (finalRow == this.row+sign && finalCol == this.col-1)) {
					return true;
				}
			}
		}else if(state == BoardState.BLACK) {
			if(state != color && state != BoardState.INVALID) {
				if((finalRow == this.row+sign && finalCol == this.col+1) || (finalRow == this.row+sign && finalCol == this.col-1)) {
					return true;
				}
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
