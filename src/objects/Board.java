package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import engine.Game;
import engine.ID;
import engine.KeyboardManager;
import engine.Methods;
import engine.MouseManager;
import graphics.SpriteSheet;
import objects.pieces.*;

public class Board {
	private int tileWidth;
	private int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];
	private Color blackColor, whiteColor;
	private int x, y;
	private Player player0, player1;
	private int selectedTileX, selectedTileY;
	private Piece selectedPiece;
	private int activeTileX, activeTileY;

	public Board(int x, int y, int tileWidth, Player player0, Player player1) {
		this.tileWidth = tileWidth;
		this.x = x;
		this.y = y;
		this.player0 = player0;
		this.player1 = player1;
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
				if(pieces[i][j] != null) {
					pieces[i][j].render(g);
				}
				if(i == selectedTileY && j == selectedTileX) {
					g.setColor(Color.RED);
					g.drawRect(((int) x) + j * tileWidth+2, ((int) y) + i * tileWidth+2, tileWidth-2, tileWidth-2);
				}
				if(i == activeTileY && j == activeTileX && selectedPiece != null) {
					g.setColor(Color.GREEN);
					g.drawRect(((int) x) + j * tileWidth+2, ((int) y) + i * tileWidth+2, tileWidth-2, tileWidth-2);
				}
				if(selectedPiece != null) {
					if(selectedPiece.isValidEndPoint(i, j)) {
						if(selectedPiece.isValidMove(i, j)) {
							g.setColor(Color.BLUE);
							g.drawRect(((int) x) + j * tileWidth+2, ((int) y) + i * tileWidth+2, tileWidth-2, tileWidth-2);
						}
					}
				}
			}
		}

	}

	public void tick() {
		selectTile();
	}
	
	private void selectTile() {
		if(MouseManager.getMouseButtonPressed(1)) {
			selectedTileX = (MouseManager.getMouseX() - x) / tileWidth; 
			selectedTileY = (MouseManager.getMouseY() - y) / tileWidth;
			selectedTileX = Methods.clamp(selectedTileX, 0, 7);
			selectedTileY = Methods.clamp(selectedTileY, 0, 7);
			if(pieces[selectedTileY][selectedTileX] != null) {
				activeTileX = selectedTileX;
				activeTileY = selectedTileY;
				selectedPiece = pieces[selectedTileY][selectedTileX];
			}
		}
	}
	
	public void setupBoard() {
		//this.pieces[0][0] = new Rook(0,0,this,this.player0);
		//this.pieces[0][1] = new Knight(0,1,this,this.player0);
		this.pieces[3][4] = new Queen(3,4,this,this.player1);
		this.pieces[2][2] = new Bishop(2,2,this,this.player1);
	}
	
	public Piece getPiece(int row, int col) {
		if((row >= 0 && row <= this.pieces.length-1) && (col >= 0 && col <= this.pieces[row].length-1)) {
			return this.pieces[row][col];
		}else {
			return null;
		}
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
