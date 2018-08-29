package objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import engine.Game;
import engine.MouseManager;
import graphics.SpriteSheet;

/*
 * Chess piece, extended by particular pieces
 * @author MCForsas 2018
 */

public abstract class Piece{
	protected Board board;
	protected int moves;
	protected BufferedImage sprite;
	protected int x, y;
	protected Player player;
	protected SpriteSheet spriteSheet;
	protected PieceType pieceType;
	
	/*
	 * Set all member varialbles
	 * @param int x piece x position on the board
	 * @param int y piece y position on the board
	 * @param Board board object
	 * @param Player player object (white/black)
	 */
	public Piece(int x, int y, Board board, Player player) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.player = player;
		this.spriteSheet = new SpriteSheet(Game.spriteSheet, 32);
		this.moves = 0;
	}
	
	/*
	 * Is mouse cursor on this piece and is left pressed?
	 * @return boolean isPressed is pressed on piece
	 */
	protected boolean isPressed() {
		if(MouseManager.getMouseButtonPressed(MouseEvent.BUTTON1)){
			if(MouseManager.isMouseCursorInRectangle(
					this.x * board.getTileWidth(),
					this.y * board.getTileWidth(),
					this.x+1 * board.getTileWidth(),
					this.y+1 * board.getTileWidth())) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Draw piece on tile 
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		if (this.sprite != null) {
			g.drawImage(
				sprite,
				board.getX() + x * board.getTileWidth() + sprite.getWidth()/4,
				board.getY() + y * board.getTileWidth() + sprite.getHeight()/4,
				null
			);
		}
	}
	
	/*
	 * Check if when piece is being moved, it isn't moved to the same tile it is on
	 * @return boolean isMovedToItSelf   
	 */
	protected boolean isMovedToItSelf(int finalX, int finalY) {
		return (finalX == this.x && finalY == this.y);
	}
	
	/*
	 * Check if when piece is being moved, it isn't moved to the same color piece it is itself
	 * @return boolean isNotMovedToSameColor 
	 */
	protected boolean isNotMovedToSameColor(int finalX, int finalY) {
		Piece piece = this.board.getPiece(finalX, finalY);
		if(piece != null) {
			if(piece.getPlayer().getColor() != this.getPlayer().getColor()) {
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Check if move is valid by that piece (Bishops only move diagonally, kings one tile at a time...)
	 * @param int finalX tile x to move to
	 * @param int finalY tile y to move to
	 * @return boolean isValidMove
	 */
	protected abstract boolean isValidMove(int finalX, int finalY);
	
	/*
	 * Check if tile can be occupied by that piece (Bishops only move diagonally, kings one tile at a time...)
	 * @param int finalX tile x to move to
	 * @param int finalY tile y to move to
	 * @return boolean isValidEndPoint
	 */
	protected abstract boolean isValidEndPoint(int finalX, int finalY);
	
	/*
	 * Check if possible to move to the tile and if so, move to it
	 * @param int finalX tile x to move to
	 * @param int finalY tile y to move to
	 */
	public void move(int finalX, int finalY) {
		if(isValidEndPoint(finalX,finalY) && isValidMove(finalX, finalY) && isNotMovedToSameColor(finalX, finalY)) {
			//Get piece info of piece that is on that tile
			Piece piece = board.getPiece(finalX, finalY);
			if(piece != null) {
				//If tile is occupied by other color, capture that piece
				if(piece.getPlayer().getColor() != this.player.getColor()) {
					board.capture(finalX, finalY);
				}
			}
			
			//Move the piece
			board.movePiece(this.x, this.y, finalX, finalY);
			this.x = finalX;
			this.y = finalY;
			this.moves++;
		}
	}

	/*
	 * Get piece type
	 * @return PieceType pieceType
	 */
	public PieceType getPieceType() {
		return pieceType;
	}

	/*
	 * Set piece type
	 * @param PieceType pieceType
	 */
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	
	/*
	 * Get player this, piece belongs to
	 * @reurn Player player owner of this piece
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/*
	 * Get how many times the piece was mooved
	 * @return int moves amount of moves
	 */
	protected int getMoves() {
		return moves;
	}
	
	/*
	 * Set how many times the piece was mooved
	 * @param int moves amount of moves
	 */
	protected void setMoves(int moves) {
		this.moves = moves;
	}

	/*
	 * Get piece x tile
	 * @return int x x tile
	 */
	public int getX() {
		return x;
	}

	/*
	 * Set piece x tile
	 * @param int x x tile
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/*
	 * Get piece y tile
	 * @return int y y tile
	 */
	public int getY() {
		return y;
	}

	/*
	 * Set piece y tile
	 * @param int y y tile
	 */
	public void setY(int y) {
		this.y = y;
	}
}
