package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.MouseManager;
import graphics.SpriteSheet;

public class Promotion {
	int x, y;
	private Piece pawn;
	private Board board;
	private PieceType pieceType;
	private Color playerColor;
	private SpriteSheet spriteSheet;
	private Color backgroundColor = Color.WHITE;
	
	public Promotion(int x, int y, Piece pawn, Board board) {
		this.x = x;
		this.y = y;
		this.pawn = pawn;
		this.playerColor = pawn.getPlayer().getColor();
		this.spriteSheet = new SpriteSheet(Game.spriteSheet, 32);
		this.board = board;
		this.board.setAbleToMove(false);
	}
	
	public void tick() {
		int tileWidth = this.board.getTileWidth();
		if(MouseManager.getMouseButtonPressed(1)) {
			if(((MouseManager.getMouseX() > this.x && MouseManager.getMouseX() < this.x + tileWidth*4) && (MouseManager.getMouseY() > this.y && MouseManager.getMouseY() < this.y + tileWidth*4))) {
				int selectedPiece = (MouseManager.getMouseX() - x) / tileWidth;
				switch (selectedPiece) {
					case (0):{
						this.pieceType = PieceType.Queen;
						break;
					}
					case (1):{
						this.pieceType = PieceType.Rook;
						break;
					}
					case (2):{
						this.pieceType = PieceType.Bishop;
						break;
					}
					case (3):{
						this.pieceType = PieceType.Knight;
						break;
					}
				}
			}
			this.board.promote(this.pawn, this.pieceType);
		}
	}
	
	public void render(Graphics g) {
		BufferedImage spriteQueen, spriteRook, spriteBishop, spriteKnight; 
		if(playerColor == Color.WHITE) {
			spriteQueen = spriteSheet.grabImage(2, 1, 32, 32);
			spriteRook = spriteSheet.grabImage(1, 2, 32, 32);
			spriteBishop = spriteSheet.grabImage(1, 4, 32, 32);
			spriteKnight = spriteSheet.grabImage(1, 3, 32, 32);
		}else{
			spriteQueen = spriteSheet.grabImage(4, 1, 32, 32);
			spriteRook = spriteSheet.grabImage(3, 2, 32, 32);
			spriteBishop = spriteSheet.grabImage(3, 4, 32, 32);
			spriteKnight = spriteSheet.grabImage(3, 3, 32, 32);
		}
		int tileWidth = this.board.getTileWidth();
		//int padding = tileWidth/4;
		int spriteWidth = spriteQueen.getWidth();
		//int spriteHeight = spriteQueen.getHeight();
		g.setColor(backgroundColor);
		g.fillRect(x , y, tileWidth*4, tileWidth);
		g.setColor(Color.BLACK);
		g.drawRect(x , y, tileWidth*4, tileWidth);
		
		//draw pieces
		g.drawImage(
			spriteQueen,
			x + spriteWidth/4 + tileWidth*0,
			y + spriteWidth/4,
			null
		);
		
		g.drawImage(
			spriteRook,
			x + spriteWidth/4 + tileWidth*1,
			y + spriteWidth/4,
			null
		);
		
		g.drawImage(
			spriteBishop,
			x + spriteWidth/4 + tileWidth*2, 
			y + spriteWidth/4,
			null
		);
		
		g.drawImage(
			spriteKnight,
			x + spriteWidth/4 + tileWidth*3,
			y + spriteWidth/4,
			null
		);
		
	}
}
