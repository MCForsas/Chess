package objects;

import java.awt.Color;

public class Player {
	private Color color;
	private Board board;

	public Player(Color color) {
		this.color = color;
		
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
