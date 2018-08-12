package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.ID;

public class Board extends GameObject {
	public int tileWidth;
	private BoardState[][] gameBoard = new BoardState[8][8];
	private int boardSize = 8;

	public Board(int x, int y, ID id, int tileWidth) {
		super(x, y, id, null);
		this.tileWidth = tileWidth;
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				gameBoard[i][j] = BoardState.FREE;
			}
		}
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect((int)x-1, (int)y-1, tileWidth*boardSize+1, tileWidth*boardSize+1);
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (j % 2 == 0 ^ i % 2 == 0) {
					switch (gameBoard[i][j]) {
						case VALID :{
							g.setColor(Color.GREEN);
						}
						default:{
							g.setColor(Color.BLACK);
						}
					}
					
				} else {
					switch (gameBoard[i][j]) {
					case VALID :{
							g.setColor(Color.GREEN);
						}
						default:{
							g.setColor(Color.WHITE);
						}
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

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
