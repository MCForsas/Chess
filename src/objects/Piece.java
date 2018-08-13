package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.ID;
import pieces.PieceColor;

public abstract class Piece extends GameObject {
	protected Board board;
	protected int row, col;
	protected Game game;
	protected PieceColor color;
	
	public Piece(int row, int col, ID id, BufferedImage sprite, Board board, Game game, PieceColor color) {
		super(0, 0, id,sprite);
		this.row  = row;
		this.col = col;
		this.board = board;
		this.game = game;
		this.color = color;
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

	public PieceColor getColor() {
		return color;
	}

	public void setColor(PieceColor color) {
		this.color = color;
	}
}
