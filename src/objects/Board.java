package objects;

import java.awt.Color;
import java.awt.Graphics;

import engine.AudioPlayer;
import engine.Methods;
import engine.MouseManager;
import graphics.HUD;
import objects.pieces.Bishop;
import objects.pieces.King;
import objects.pieces.Knight;
import objects.pieces.Pawn;
import objects.pieces.Queen;
import objects.pieces.Rook;

/*
 * Board object holds all pieces in array, players, selects tiles, promotes, moves pieces and captures them
 * @author MCForsas 2018
 */

public class Board extends GameObject{
	private int tileWidth;
	private int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];
	private Color blackColor, whiteColor;
	private Player player0, player1;
	private int selectedTileX = -1, selectedTileY = -1,activeTileX, activeTileY, moveTileX, moveTileY;
	private Piece selectedPiece;
	private boolean isAbleToMove;
	private Promotion promotion;

	/*
	 * Create board and set players
	 * @param int x x position of the board
	 * @param int y y position of the board
	 * @param int tileWidth width of board tiles
	 * @param Player player0 player0
	 * @param Player player1 player1
	 */
	public Board(int x, int y, int tileWidth, Player player0, Player player1) {
		super(x,y);
		this.tileWidth = tileWidth;
		this.player0 = player0;
		this.player1 = player1;
		this.blackColor = new Color(83,83,78);
		this.whiteColor = new Color(196,196,196);
		this.isAbleToMove = true;
		this.promotion = null;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);

		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				//Render tiles
				if (j % 2 == 0 ^ i % 2 == 0) {
					g.setColor(this.blackColor);
				}else {
					g.setColor(this.whiteColor);
				}
				g.fillRect( this.x + i * this.tileWidth, this.y + j * this.tileWidth, this.tileWidth, this.tileWidth);
				
				//draw selected tile
				if(i == this.selectedTileX && j == this.selectedTileY) {
					g.setColor(Color.RED);
					g.fillRect(this.x + i * this.tileWidth, this.y + j * this.tileWidth, this.tileWidth, this.tileWidth);
				}
				
				//Draw active tile
				if(i == this.activeTileX && j == this.activeTileY && this.selectedPiece != null) {
					g.setColor(Color.GREEN);
					g.fillRect(this.x + i * this.tileWidth, this.y + j * this.tileWidth, this.tileWidth, this.tileWidth);
				}
				
				//Draw available squares to move to
				if(this.selectedPiece != null) {
					if(
						this.selectedPiece.isValidEndPoint(i, j) &&
						this.selectedPiece.isValidMove(i, j) && 
						this.selectedPiece.isNotMovedToSameColor(i, j)
					) {
						g.setColor(Color.BLUE);
						g.fillRect(this.x + i * this.tileWidth, this.y + j * this.tileWidth, this.tileWidth, this.tileWidth);
					}
				}
				
				//draw rectangle outlines
				g.setColor(Color.BLACK);
				g.drawRect(this.x + i * this.tileWidth, this.y + j * this.tileWidth, this.tileWidth, this.tileWidth);
				
				//render pieces
				if(this.pieces[i][j] != null) {
					this.pieces[i][j].render(g);
				}
			}
		}
		
		//render promotion
		if(this.promotion != null) {
			this.promotion.render(g);
		}

	}

	public void tick() {
		if(this.isAbleToMove) {
			selectTile();
			move();
		}
		if(this.promotion != null) {
			this.promotion.tick();
		}
	}
	
	/*
	 * Select tile if user clicks on the board
	 */
	private void selectTile() {
		if(MouseManager.getMouseButtonPressed(1)) {
			
			this.selectedTileX = (MouseManager.getMouseX() - this.x) / this.tileWidth; 
			this.selectedTileY = (MouseManager.getMouseY() - y) / this.tileWidth;
			this.selectedTileX = (int) Methods.clamp(this.selectedTileX, 0, 7);
			this.selectedTileY = (int) Methods.clamp(this.selectedTileY, 0, 7);
			
			Piece piece = this.pieces[this.selectedTileX][this.selectedTileY];
			
			//Try to move piece
			if(piece != null) {
				if(piece.getPlayer().isItsTurn()) {
					this.activeTileX = this.selectedTileX;
					this.activeTileY = this.selectedTileY;
					this.selectedPiece = this.pieces[this.selectedTileX][this.selectedTileY];
				}
			}
		}
	}
	
	/*
	 * try to move piece when it's selected and valid tile is pressed
	 */
	private void move() {
		if(MouseManager.getMouseButtonPressed(1)) {
			this.moveTileX = (MouseManager.getMouseX() - this.x) / this.tileWidth; 
			this.moveTileY = (MouseManager.getMouseY() - this.y) / this.tileWidth;
			this.moveTileX = (int) Methods.clamp(this.moveTileX, 0, 7);
			this.moveTileY = (int) Methods.clamp(this.moveTileY, 0, 7);
			if(this.selectedPiece != null) {
				if(this.selectedPiece.getPlayer().isItsTurn()) {
					this.selectedPiece.move(this.moveTileX, this.moveTileY);
				}
			}
		}
	}
	
	/*
	 * Promote pawn if it reaches the end
	 * @param Piece pawn pawn to promote
	 * @param PieceType piece to promote to
	 */
	public void promote(Piece pawn, PieceType type) {
		int pawnX = pawn.getX();
		int pawnY = pawn.getY();
		
		Player pawnPlayer = pawn.getPlayer();
		Piece pawnPiece = this.pieces[pawnX][pawnY];
		
		//Switch type and promote
		if(pawnPiece != null && pawnPiece.getPieceType() != null) {
			if(type == PieceType.Queen) {
				this.pieces[pawnX][pawnY] = new Queen(pawnX, pawnY, this, pawnPlayer);
			}
			if(type == PieceType.Rook) {
				this.pieces[pawnX][pawnY] = new Rook(pawnX, pawnY, this, pawnPlayer);
			}
			if(type == PieceType.Bishop) {
				this.pieces[pawnX][pawnY] = new Bishop(pawnX, pawnY, this, pawnPlayer);
			}
			if(type == PieceType.Knight) {
				this.pieces[pawnX][pawnY] = new Knight(pawnX, pawnY, this, pawnPlayer);
			}
		}
		setAbleToMove(true);
		this.promotion = null;
	}
	
	/*
	 * Set's up board by creating all pieces according to chess rules
	 */
	public void setupBoard() {
		//Tests
		//this.pieces[1][1] = new Pawn(1, 1,this, this.player0);
		this.pieces[1][2] = new Pawn(1, 2,this, this.player0);
		/*
		 * Black pieces
		 */
		//Rooks
		this.pieces[0][0] = new Rook(0, 0, this, this.player1);
		this.pieces[7][0] = new Rook(7, 0, this, this.player1);
		//Knights
		this.pieces[1][0] = new Knight(1, 0, this, this.player1);
		this.pieces[6][0] = new Knight(6, 0, this, this.player1);
		//Bishops
		this.pieces[2][0] = new Bishop(2, 0, this, this.player1);
		this.pieces[5][0] = new Bishop(5, 0, this, this.player1);
		//Queen
		this.pieces[3][0] = new Queen(3, 0, this, this.player1);
		//King
		this.pieces[4][0] = new King(4, 0, this, this.player1);
		//Black pawns
		for(int i = 0; i < 8; i++) {
			this.pieces[i][1] = new Pawn(i, 1,this, this.player1);
		}
		
		/*
		 * White pieces 
		 */
		//Rooks
		this.pieces[0][7] = new Rook(0, 7, this, this.player0);
		this.pieces[7][7] = new Rook(7, 7, this, this.player0);
		//Knights
		this.pieces[1][7] = new Knight(1, 7, this, this.player0);
		this.pieces[6][7] = new Knight(6, 7, this, this.player0);
		//Bishops
		this.pieces[2][7] = new Bishop(2, 7, this, this.player0);
		this.pieces[5][7] = new Bishop(5, 7, this, this.player0);
		//Queen
		this.pieces[3][7] = new Queen(3, 7, this, this.player0);
		//King
		this.pieces[4][7] = new King(4, 7, this, this.player0);
		//White pawns
		for(int i = 0; i < 8; i++) {
			this.pieces[i][6] = new Pawn(i, 6,this, this.player0);
		}
	}
	
	/*
	 * Gets piece that is located on a given tile
	 * @param int finalX tile x to check
	 * @param int finalY tile y to check
	 * @return Piece piece if tile is occupied, returns piece, if not, null
	 */
	public Piece getPiece(int finalX, int finalY) {
		if((finalX >= 0 && finalX <= this.pieces.length-1) && (finalY >= 0 && finalY <= this.pieces[finalX].length-1)) {
			return this.pieces[finalX][finalY];
		}else {
			return null;
		}
	}
	
	/*
	 * Move piece from one tile to another
	 * @param int x piece to move x tile
	 * @param int y piece to move y tile
	 * @param int finalX x tile to move to
	 * @param int finalY y tile to move to
	 */
	public void movePiece(int x, int y, int finalX, int finalY) {
		Piece piece = this.pieces[x][y];
		if(piece != null) {
			this.pieces[finalX][finalY] = piece;
			this.pieces[x][y] = null;
		}
		this.selectedPiece = null;
		this.selectedTileX = finalX;
		this.selectedTileY = finalY;
		
		//Promote
		if(piece.getPieceType() == PieceType.Pawn) {
			if(piece.getPlayer().getColor() == Color.WHITE && finalY == 0) {
				this.promotion = new Promotion(this.x + this.tileWidth*2, this.y + this.tileWidth*2, piece, this);
			}
			if(piece.getPlayer().getColor() == Color.BLACK && finalY == this.boardSize-1) {
				this.promotion = new Promotion(this.x + this.tileWidth*2, this.y + this.tileWidth*2, piece, this);
			}
		}
		this.player0.toggleItsTurn();
		this.player1.toggleItsTurn();
	}

	/*
	 * Captures piece from tile if that tile is occupied
	 * @param finalX tile x to check
	 * @param finalY tile y to check
	 */
	public void capture(int finalX, int finalY) {
		Piece piece = this.pieces[finalX][finalY];
		if(piece != null) {
			//Check if king was captured
			if(piece.getPieceType() == PieceType.King) {
				if(piece.getPlayer().getColor() == Color.WHITE) {
					HUD.setWinner("Black won!");
				}
				if(piece.getPlayer().getColor() == Color.BLACK) {
					HUD.setWinner("White won!");
				}
				AudioPlayer.getMusic("victory").play();
				this.isAbleToMove = false;
			}
			//Set piece to null
			this.pieces[finalX][finalY] = null;
		}
	}
	
	/*
	 * Checks whether player is able to move pieces or not (used when promoting, so player can't move pieces until promotion ends)
	 * @return boolean isAbleToMove
 	 */
	public boolean isAbleToMove() {
		return this.isAbleToMove;
	}

	/*
	 * Sets ability to move (used when promoting, so player can't move pieces until promotion ends)
	 * @param boolean isAbleToMove
 	 */
	public void setAbleToMove(boolean isAbleToMove) {
		this.isAbleToMove = isAbleToMove;
	}

	/*
	 * Gets tile width of the board
	 * @return boolean tileWidth
	 */
	public int getTileWidth() {
		return this.tileWidth;
	}

	/*
	 * Sets board tile width
	 * @param int tileWidth
	 */
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/*
	 * Gets how many tiles board has across (default: 8)
	 * @return int boardSize
	 */
	public int getBoardSize() {
		return this.boardSize;
	}

	/*
	 * Sets how many tiles board has across (default: 8)
	 * @param int boardSize
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
}
