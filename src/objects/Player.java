package objects;

import java.awt.Color;

public class Player {
	private Color color;
	private Board board;
	private boolean isItsTurn;
	private int time;

	public Player(Color color, boolean isItsTurn) {
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

}
