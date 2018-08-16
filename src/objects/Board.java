package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Game;
import engine.ID;
import graphics.SpriteSheet;
import objects.pieces.Queen;

public class Board {
	private int tileWidth;
	private int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];
	private Color blackColor, whiteColor;
	private int x, y;

	public Board(int x, int y, ID id, int tileWidth) {
		this.tileWidth = tileWidth;
		this.x = x;
		this.y = y;
		this.blackColor = new Color(83,83,78);
		this.whiteColor = new Color(196,196,196);
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (j % 2 == 0 ^ i % 2 == 0) {
					g.setColor(this.blackColor);
				}else {
					g.setColor(this.whiteColor);
				}
				g.fillRect(((int) x) + j * tileWidth, ((int) y) + i * tileWidth, tileWidth, tileWidth);
				g.setColor(Color.BLACK);
				g.drawRect(((int) x) + j * tileWidth, ((int) y) + i * tileWidth, tileWidth, tileWidth);
			}
		}

	}

	public void tick() {
		
	}
	
	private boolean isValidMove(Piece piece, int finalX, int finalY) {
		return false;
	}
	
	public void setupBoard() {
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet, 32);
		//this.pieces[3][3] = new Queen(3,3,spriteSheet.grabImage(4, 2, 32, 32), this, this);
	}
	
	public Piece getPieces(int row, int col) {
		return this.pieces[row][col];
	}
	
	public void setPieces(int row, int col, Piece piece) {
		this.pieces[row][col] = piece;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
