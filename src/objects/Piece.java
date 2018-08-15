package objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import engine.Game;
import engine.ID;
import engine.MouseManager;
import pieces.PieceColor;

public abstract class Piece extends GameObject {
	protected Board board;
	protected int row, col;
	protected Game game;
	protected BoardState color;
	protected int moves;
	protected int sign;
	protected MouseManager mouseManager;
	
	public Piece(int row, int col, ID id, BufferedImage sprite, Board board, Game game, BoardState color) {
		super(0, 0, id,sprite);
		this.row  = row;
		this.col = col;
		this.board = board;
		this.game = game;
		this.color = color;
		this.sign  = (color == BoardState.WHITE) ? 1 : -1;
		
	}

	public void tick() {
		//if(isPressed()) {
			for(int i = 0; i < board.getBoardSize(); i++) {
				for(int j = 0; j < board.getBoardSize(); j++) {
					if(isValidPath(i, j)) {
						board.setValidPath(i, j, BoardState.VALID);
					}
				}
			}
		//}else {
			//board.setBoardState(row, col, color);
		//}
		
	}
	
	protected boolean isPressed() {
		if(MouseManager.getMouseButtonPressed(MouseEvent.BUTTON1)){
			if(MouseManager.mouseX >= this.row * board.getTileWidth() && MouseManager.mouseX <= this.row+1 * board.getTileWidth()) {
				if(MouseManager.mouseY >= this.col * board.getTileWidth() && MouseManager.mouseY <= this.col+1 * board.getTileWidth()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		if (this.sprite != null) {
			g.drawImage(
				sprite,
				(int) board.getX() + col * board.getTileWidth() + sprite.getWidth()/4,
				(int) board.getY() + row * board.getTileWidth() + sprite.getHeight()/4,
				null
			);
		}
	}
	
	protected abstract boolean isValidPath(int row, int col);
	public abstract void move(int row, int col);
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public BoardState getColor() {
		return color;
	}

	public void setColor(BoardState color) {
		this.color = color;
	}
	
	protected int getMoves() {
		return moves;
	}
	
	protected void setMoves(int moves) {
		this.moves = moves;
	}
}
