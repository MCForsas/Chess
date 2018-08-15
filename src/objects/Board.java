package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.ID;

public class Board extends GameObject {
	private int tileWidth;
	private int boardSize = 8;
	private BoardState[][] gameBoard = new BoardState[boardSize][boardSize];
	private BoardState[][] validPath = new BoardState[boardSize][boardSize];
	private Color blackColor, whiteColor;

	public Board(int x, int y, ID id, int tileWidth) {
		super(x, y, id, null);
		this.tileWidth = tileWidth;
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				gameBoard[i][j] = BoardState.FREE;
			}
		}
		this.blackColor = new Color(83,83,78);
		this.whiteColor = new Color(196,196,196);
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		//g.drawRect((int)x-1, (int)y-1, tileWidth*boardSize+1, tileWidth*boardSize+1);
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if(validPath[i][j] != BoardState.VALID) {
					if (j % 2 == 0 ^ i % 2 == 0) {
						g.setColor(this.blackColor);
					}else {
						g.setColor(this.whiteColor);
					}
				}else {
					g.setColor(Color.GREEN);
				}
				
				g.fillRect(((int) x) + j * tileWidth, ((int) y) + i * tileWidth, tileWidth, tileWidth);
				g.setColor(Color.BLACK);
				g.drawRect(((int) x) + j * tileWidth, ((int) y) + i * tileWidth, tileWidth, tileWidth);
			}
		}

	}

	public void tick() {

	}
	
	public BoardState getBoardState(int row, int col) {
		if((row < boardSize || col < boardSize) && (row >= 0 || col >= 0)) {
			return gameBoard[row][col];
		}else {
			return BoardState.INVALID;
		}
	}
	
	public void setBoardState(int row, int col, BoardState state) {
		if((row < boardSize || col < boardSize) && (row >= 0 || col >= 0)) {
			gameBoard[row][col] = state;
		}
	}
	
	public void setValidPath(int row, int col, BoardState state) {
		if((row < boardSize || col < boardSize) && (row >= 0 || col >= 0)) {
			 validPath[row][col] = state;
		}
	}
	
	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public BoardState[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(BoardState[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public Rectangle getBounds() {
		return null;
	}


}
