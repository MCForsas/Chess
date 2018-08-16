package objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import engine.Game;
import engine.ID;
import engine.MouseManager;

public abstract class Piece{
	protected Board board;
	protected int moves;
	protected MouseManager mouseManager;
	protected BufferedImage sprite;
	protected int x, y;
	protected Player player;
	
	public Piece(int x, int y, BufferedImage sprite, Board board, Player player) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.board = board;
		this.player = player;
	}
	
	protected boolean isPressed() {
		if(MouseManager.getMouseButtonPressed(MouseEvent.BUTTON1)){
			if(MouseManager.mouseX >= this.x * board.getTileWidth() && MouseManager.mouseX <= this.x+1 * board.getTileWidth()) {
				if(MouseManager.mouseY >= this.y * board.getTileWidth() && MouseManager.mouseY <= this.y+1 * board.getTileWidth()) {
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
	
	protected abstract boolean isValidPath(int row, int col);
	//public abstract void move(int row, int col);
	
	
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
