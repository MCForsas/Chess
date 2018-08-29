package objects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Player object, holds color, is used with pieces
 * @author MCForsas 2018
 */

public class Player extends GameObject {
	private Color color;
	private Board board;
	private boolean isItsTurn;
	
	
	/*
	 * Set color and if it is it's turn in the begginnig
	 * @param Color color white/black
	 * @param booleand isItsTurn is it it's turn in the beggining?
	 */
	public Player(Color color, boolean isItsTurn) {
		super(0,0);
		this.color = color;
		this.isItsTurn = isItsTurn;
	}
	
	/*
	 * Toggles the turn between true an false, true -> false -> true
	 */
	public void toggleItsTurn() {
		if(this.isItsTurn) {
			this.isItsTurn = false;
		}else {
			this.isItsTurn = true;
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Get isItsTurn (true/false)
	 * @return boolean isItsTurn
	 */
	public boolean isItsTurn() {
		return this.isItsTurn;
	}

	/*
	 * Set's isItsTurn to given value
	 * @param boolean isItsTurn
	 */
	public void setItsTurn(boolean isItsTurn) {
		this.isItsTurn = isItsTurn;
	}

	/*
	 * Get color of the player (white/black)
	 * @return Color color 
	 */
	public Color getColor() {
		return this.color;
	}

	/*
	 * Set Color of the player (white/black)
	 * @param Color color 
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * Get Board object of the player (There shouldn't be more than one board)
	 * @return Board board
	 */
	public Board getBoard() {
		return this.board;
	}

	/*
	 * Set Board object of the player
	 * @param Board board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}
