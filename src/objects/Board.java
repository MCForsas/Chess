package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.ID;

public class Board extends GameObject {
	public int tileWidth;
	private BoardState[][] board;
	private int boardSize = 8;

	public Board(int x, int y, ID id, int tileWidth) {
		super(x, y, id, null);
		this.tileWidth = tileWidth;
	}

	public void render(Graphics g) {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (j % 2 == 0) {
					if (i % 2 != 0) {
						g.setColor(Color.BLACK);
					} else {
						g.setColor(Color.WHITE);
					}
				} else {
					if (i % 2 != 0) {
						g.setColor(Color.WHITE);
					} else {
						g.setColor(Color.BLACK);
					}
				}
				g.fillRect(((int) x) + j * tileWidth, ((int) y) + i * tileWidth, tileWidth, tileWidth);
			}
		}

	}

	public void tick() {

	}
	
	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public BoardState[][] getBoard() {
		return board;
	}

	public void setBoard(BoardState[][] board) {
		this.board = board;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
