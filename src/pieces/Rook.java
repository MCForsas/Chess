package pieces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import objects.Board;
import objects.BoardState;
import objects.Piece;

public class Rook extends Piece{

	public Rook(int row, int col, BufferedImage sprite, Board board, Game game, BoardState color) {
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
			if(finalRow == this.row ^ finalCol == this.col) {
				if(finalRow == this.row) {
					boolean isBlocked = false;
					int direction = (finalCol-this.col < 0)? -1 : 1;
					if(direction == -1) {
						for(int i = finalCol; i < this.col - 1; i++) {
							if(board.getBoardState(finalRow, i) == color) {
								isBlocked = true;
							}
						}
					}else {
						for(int i = finalCol; i > this.col + 1; i--) {
							if(board.getBoardState(finalRow, i) == color) {
								isBlocked = true;
							}
						}
						
					}
					if(isBlocked) {
						return false;
					}else {
						return true;
					}
				}else {
					if(finalCol == this.col) {
						boolean isBlocked = false;
						int direction = (finalRow-this.row < 0)? -1 : 1;
						if(direction == -1) {
							for(int i = finalRow; i < this.row - 1; i++) {
								if(board.getBoardState(i, col) == color) {
									isBlocked = true;
									
								}
							}
						}else {
							for(int i = finalRow; i > this.row + 1; i--) {
								if(board.getBoardState(i, col) == color) {
									isBlocked = true;
								}
							}
							
						}
						if(isBlocked) {
							return false;
						}else {
							return true;
						}
					}
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
