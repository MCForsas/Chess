package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.MouseManager;
import graphics.SpriteSheet;

/*
 * Promotes pawn to other piece, when pawn reaches the end
 */

public class Promotion{
	int x, y;
	private Piece pawn;
	private Board board;
	private PieceType pieceType;
	private Color playerColor;
	private SpriteSheet spriteSheet;
	private Color backgroundColor = Color.WHITE;
	
	/*
	 * Creates promotion gui instance
	 * @param int x x coordinate of the promotion gui
	 * @param int y y coordinate of the promotion gui
	 * @param piece pawn pawn which is being promoted
	 * @param Board board board object
	 */
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
		
		//if  mouse is pressed and cursor is on promotion gui
		if(MouseManager.getMouseButtonPressed(1)) {
			if(MouseManager.isMouseCursorInRectangle(this.x, this.y, tileWidth*4, tileWidth*4)) {
				int selectedPiece = (MouseManager.getMouseX() - this.x) / tileWidth;
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
				this.board.promote(this.pawn, this.pieceType);
			}
		}
	}
	

	/*
	 * Renders promotion gui
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		BufferedImage spriteQueen, spriteRook, spriteBishop, spriteKnight; 
		
		//Get all sprites according to color
		if(this.playerColor == Color.WHITE) {
			spriteQueen = this.spriteSheet.grabImage(2, 1, 32, 32);
			spriteRook = this.spriteSheet.grabImage(1, 2, 32, 32);
			spriteBishop = this.spriteSheet.grabImage(1, 4, 32, 32);
			spriteKnight = this.spriteSheet.grabImage(1, 3, 32, 32);
		}else{
			spriteQueen = this.spriteSheet.grabImage(4, 1, 32, 32);
			spriteRook = this.spriteSheet.grabImage(3, 2, 32, 32);
			spriteBishop = this.spriteSheet.grabImage(3, 4, 32, 32);
			spriteKnight = this.spriteSheet.grabImage(3, 3, 32, 32);
		}
		
		int tileWidth = this.board.getTileWidth();
		int spriteWidth = spriteQueen.getWidth();
		
		//Draw background rectangle and outline it
		g.setColor(this.backgroundColor);
		g.fillRect(this.x , this.y, tileWidth*4, tileWidth);
		g.setColor(Color.BLACK);
		g.drawRect(this.x , this.y, tileWidth*4, tileWidth);
		
		//draw pieces
		//Queen
		g.drawImage(
			spriteQueen,
			this.x + spriteWidth/4 + tileWidth*0,
			this.y + spriteWidth/4,
			null
		);
		
		//Rook
		g.drawImage(
			spriteRook,
			this.x + spriteWidth/4 + tileWidth*1,
			this.y + spriteWidth/4,
			null
		);
		
		//Bishop
		g.drawImage(
			spriteBishop,
			this.x + spriteWidth/4 + tileWidth*2, 
			this.y + spriteWidth/4,
			null
		);
		
		//Knight
		g.drawImage(
			spriteKnight,
			this.x + spriteWidth/4 + tileWidth*3,
			this.y + spriteWidth/4,
			null
		);
		
	}
}
