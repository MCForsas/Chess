package objects;

import java.awt.Graphics;

import objects.pieces.Pawn;

public class Promotion {
	int x, y;
	private Pawn pawn;
	private Piece piece;
	
	public Promotion(int x, int y, Pawn pawn) {
		this.x = x;
		this.y = y;
		this.pawn = pawn;
	}
	
	public void render(Graphics g) {
		
	}
}
