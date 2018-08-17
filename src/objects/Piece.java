package objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import engine.Game;
import engine.ID;
import engine.MouseManager;
import graphics.SpriteSheet;

public abstract class Piece{
	protected Board board;
	protected int moves;
	protected BufferedImage sprite;
	protected int x, y;
	protected Player player;
	protected SpriteSheet spriteSheet;
	protected PieceType pieceType;
	
	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public Piece(int x, int y, Board board, Player player) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.player = player;
		this.spriteSheet = new SpriteSheet(Game.spriteSheet, 32);
	}
	
	protected boolean isPressed() {
		if(MouseManager.getMouseButtonPressed(MouseEvent.BUTTON1)){
			if(MouseManager.getMouseX() >= this.x * board.getTileWidth() && MouseManager.getMouseX() <= this.x+1 * board.getTileWidth()) {
				if(MouseManager.getMouseY() >= this.y * board.getTileWidth() && MouseManager.getMouseY() <= this.y+1 * board.getTileWidth()) {
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
				(int) board.getX() + y * board.getTileWidth() + sprite.getWidth()/4,
				(int) board.getY() + x * board.getTileWidth() + sprite.getHeight()/4,
				null
			);
		}
	}
	
	protected boolean isMovedToItSelf(int finalX, int finalY) {
		return (finalX == this.x && finalY == this.y);
	}
	
	protected abstract boolean isValidMove(int finalX, int finalY);
	protected abstract boolean isValidEndPoint(int finalX, int finalY);
	//public abstract int[][] drawPath(int finalX, int finalY);
	
	public Player getPlayer() {
		return this.player;
	}
	
	protected int getMoves() {
		return moves;
	}
	
	protected void setMoves(int moves) {
		this.moves = moves;
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
