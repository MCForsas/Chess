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
	private int selectedTileX, selectedTileY,activeTileX, activeTileY, moveTileX, moveTileY;
	private Piece selectedPiece;

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
					if(selectedPiece.isValidEndPoint(i, j) && selectedPiece.isValidMove(i, j) && selectedPiece.isNotMovedToSameColor(i, j)) {
						g.setColor(Color.BLUE);
						g.drawRect(((int) x) + j * tileWidth+2, ((int) y) + i * tileWidth+2, tileWidth-2, tileWidth-2);
					}
				}
			}
		}

	}

	public void tick() {
		selectTile();
		move();
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
	
	private void move() {
		if(MouseManager.getMouseButtonPressed(3)) {
			moveTileX = (MouseManager.getMouseX() - x) / tileWidth; 
			moveTileY = (MouseManager.getMouseY() - y) / tileWidth;
			moveTileX = Methods.clamp(selectedTileX, 0, 7);
			moveTileY = Methods.clamp(selectedTileX, 0, 7);
			if(selectedPiece != null) {
				selectedPiece.move(moveTileX, moveTileY);
			}
		}
	}
	
	public void setupBoard() {
		this.pieces[2][1] = new Rook(2,1,this,this.player0);
		this.pieces[1][2] = new Knight(1,2,this,this.player0);
		this.pieces[2][4] = new Queen(2,4,this,this.player1);
		this.pieces[5][4] = new Bishop(5,4,this,this.player1);
		this.pieces[6][3] = new Pawn(6,3,this,this.player1);
		this.pieces[7][2] = new Pawn(7,2,this,this.player0);
	}
	
	public Piece getPiece(int row, int col) {
		if((row >= 0 && row <= this.pieces.length-1) && (col >= 0 && col <= this.pieces[row].length-1)) {
			return this.pieces[row][col];
		}else {
			return null;
		}
	}
	
	public void movePiece(int x, int y, int finalX, int finalY) {
		Piece piece = this.pieces[x][y];
		if(piece != null) {
			this.pieces[finalX][finalY] = piece;
			this.pieces[x][y] = null;
		}
	}
	
	public void capture(int finalX, int finalY) {
		Piece piece = this.pieces[finalX][finalY];
		if(piece != null) {
			this.pieces[finalX][finalY] = null;
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
