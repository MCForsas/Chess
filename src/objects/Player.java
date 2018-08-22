package objects;

import java.awt.Color;
import java.awt.Graphics;

import engine.Game;

public class Player extends GameObject {
	private Color color;
	private Board board;
	private boolean isItsTurn;
	private int time;

	public Player(Color color, boolean isItsTurn) {
		super(0,0);
		this.color = color;
		this.isItsTurn = isItsTurn;
	}
	
	public boolean isItsTurn() {
		return isItsTurn;
	}

	public void setItsTurn(boolean isItsTurn) {
		this.isItsTurn = isItsTurn;
	}
	
	public void toggleItsTurn() {
		if(this.isItsTurn) {
			this.isItsTurn = false;
		}else {
			this.isItsTurn = true;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
