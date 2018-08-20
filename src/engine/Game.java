package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.Fonts;
import graphics.HUD;
import graphics.Window;
import objects.Board;
import objects.Player;

import java.awt.Graphics;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1178041823728909735L;
	public static final int WINDOW_WIDTH = 720, WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9;
	public static int FPS;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	public static BufferedImage spriteSheet;
	public Board board;
	public final int tileWINDOW_WIDTH = 48;
	private Player player0, player1;
	private HUD hud;

	/*
	 * Instantiates objects and window
	 */
	public Game() {
		Fonts.addFont(new Fonts("/fonts/BEBAS.ttf"));
		BufferedImageLoader loader = new BufferedImageLoader();
		this.addKeyListener(new KeyboardManager());
		this.addMouseListener(new MouseManager());
		handler = new Handler();
		hud = new HUD();
		try {
			spriteSheet = loader.loadImage("/images/pieces.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AudioPlayer.load();
		// AudioPlayer.getMusic("music").loop();
		player0 = new Player(Color.WHITE,true);
		player1 = new Player(Color.BLACK,false);
		board = new Board(WINDOW_WIDTH/2-tileWINDOW_WIDTH*4,WINDOW_HEIGHT/2-tileWINDOW_WIDTH*4, 48, player0, player1);
		player0.setBoard(board);
		player1.setBoard(board);
		board.setupBoard();
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Chess", this);
	}

	
	/*
	 * Starts a new thread, on which all game is held
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/*
	 * Stops the thread;
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ads 'ticks' to the game. Gets FPS. Calls tick() and render()
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
		}
		stop();
	}

	/*
	 * Ticks the game objects
	 */
	private void tick() {
		board.tick();
		handler.tick();
	}

	/*
	 * Renders all game objects, backgrouds, HUD's and etc
	 */
	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		g.setColor(new Color(66,55,21));
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		board.render(g);
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
