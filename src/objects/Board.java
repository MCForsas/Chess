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
	private boolean isAbleToMove;

	public Board(int x, int y, int tileWidth, Player player0, Player player1) {
		this.tileWidth = tileWidth;
		this.x = x;
		this.y = y;
		this.player0 = player0;
		this.player1 = player1;
		this.blackColor = new Color(83,83,78);
		this.whiteColor = new Color(196,196,196);
		this.isAbleToMove = true;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);

		//i = x 
		//j = y
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (j % 2 == 0 ^ i % 2 == 0) {
					g.setColor(this.blackColor);
				}else {
					g.setColor(this.whiteColor);
				}
				g.fillRect( x + i * tileWidth, y + j * tileWidth, tileWidth, tileWidth);
				g.setColor(Color.BLACK);
				g.drawRect(x + i * tileWidth, y + j * tileWidth, tileWidth, tileWidth);
				if(pieces[i][j] != null) {
					pieces[i][j].render(g);
				}
				if(i == selectedTileX && j == selectedTileY) {
					g.setColor(Color.RED);
					g.drawRect(x + i * tileWidth, y + j * tileWidth, tileWidth, tileWidth);
				}
				if(i == activeTileX && j == activeTileY && selectedPiece != null) {
					g.setColor(Color.GREEN);
					g.drawRect(x + i * tileWidth,y + j * tileWidth, tileWidth, tileWidth);
				}
				if(selectedPiece != null) {
					if(selectedPiece.isValidEndPoint(i, j) && selectedPiece.isValidMove(i, j) && selectedPiece.isNotMovedToSameColor(i, j)) {
						g.setColor(Color.BLUE);
						g.drawRect(x + i * tileWidth, y + j * tileWidth, tileWidth, tileWidth);
					}
				}
			}
		}

	}

	public void tick() {
		selectTile();
		if(isAbleToMove) {
			move();
		}
	}
	
	private void selectTile() {
		if(MouseManager.getMouseButtonPressed(1)) {
			selectedTileX = (MouseManager.getMouseX() - x) / tileWidth; 
			selectedTileY = (MouseManager.getMouseY() - y) / tileWidth;
			selectedTileX = Methods.clamp(selectedTileX, 0, 7);
			selectedTileY = Methods.clamp(selectedTileY, 0, 7);
			Piece piece = pieces[selectedTileX][selectedTileY];
			if(piece != null) {
				if(piece.getPlayer().isItsTurn()) {
					activeTileX = selectedTileX;
					activeTileY = selectedTileY;
					selectedPiece = pieces[selectedTileX][selectedTileY];
				}
			}
		}
	}
	
	private void move() {
		if(MouseManager.getMouseButtonPressed(3)) {
			moveTileX = (MouseManager.getMouseX() - x) / tileWidth; 
			moveTileY = (MouseManager.getMouseY() - y) / tileWidth;
			moveTileX = Methods.clamp(moveTileX, 0, 7);
			moveTileY = Methods.clamp(moveTileY, 0, 7);
			if(selectedPiece != null) {
				if(selectedPiece.player.isItsTurn()) {
					selectedPiece.move(moveTileX, moveTileY);
				}
				//System.out.println("Moved"+ moveTileX + ":" + moveTileY);
			}
		}
	}
	
	public void setupBoard() {
		/*
		 * Black pieces
		 */
		//Rooks
		this.pieces[0][0] = new Rook(0, 0, this, this.player1);
		this.pieces[7][0] = new Rook(7, 0, this, this.player1);
		//Knights
		this.pieces[1][0] = new Knight(1, 0, this, this.player1);
		this.pieces[6][0] = new Knight(6, 0, this, this.player1);
		//Bishops
		this.pieces[2][0] = new Bishop(2, 0, this, this.player1);
		this.pieces[5][0] = new Bishop(5, 0, this, this.player1);
		//Queen
		this.pieces[3][0] = new Queen(3, 0, this, this.player1);
		//King
		this.pieces[4][0] = new King(4, 0, this, this.player1);
		//Black pawns
		/*for(int i = 0; i < 8; i++) {
			this.pieces[i][1] = new Pawn(i, 1,this, this.player1);
		}*/
		
		/*
		 * White pieces 
		 */
		//Rooks
		this.pieces[0][7] = new Rook(0, 7, this, this.player0);
		this.pieces[7][7] = new Rook(7, 7, this, this.player0);
		//Knights
		this.pieces[1][7] = new Knight(1, 7, this, this.player0);
		this.pieces[6][7] = new Knight(6, 7, this, this.player0);
		//Bishops
		this.pieces[2][7] = new Bishop(2, 7, this, this.player0);
		this.pieces[5][7] = new Bishop(5, 7, this, this.player0);
		//Queen
		this.pieces[3][7] = new Queen(3, 7, this, this.player0);
		//King
		this.pieces[4][7] = new King(4, 7, this, this.player0);
		//White pawns
		/*for(int i = 0; i < 8; i++) {
			this.pieces[i][6] = new Pawn(i, 6,this, this.player0);
		}*/
	}
	
	public Piece getPiece(int finalX, int finalY) {
		if((finalX >= 0 && finalX <= this.pieces.length-1) && (finalY >= 0 && finalY <= this.pieces[finalX].length-1)) {
			return this.pieces[finalX][finalY];
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
		this.selectedPiece = null;
		this.selectedTileX = finalX;
		this.selectedTileY = finalY;
		player0.toggleItsTurn();
		player1.toggleItsTurn();
	}
	
	public void capture(int finalX, int finalY) {
		Piece piece = this.pieces[finalX][finalY];
		if(piece != null) {
			this.pieces[finalX][finalY] = null;
		}
	}
	
	public void setPieces(int x, int y, Piece piece) {
		this.pieces[x][y] = piece;
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
