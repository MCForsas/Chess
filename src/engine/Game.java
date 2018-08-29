package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.Fonts;
import graphics.HUD;
import graphics.Window;

import java.awt.Graphics;

/*
 * Runs the game, instantiates needed object, holds game thread, holds all global fields 
 * @author MCForsas 2018
 */

public class Game extends Canvas implements Runnable {

	public static final int WINDOW_WIDTH = 720, WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9;
	public static final int DEFAULT_FONT_SIZE = 24;
	public static int FPS;
	public static BufferedImage spriteSheet;
	
	private static final long serialVersionUID = -1178041823728909735L;
	private Thread thread;
	private boolean running = false;
	private HUD hud;
	private LevelManager level;

	/*
	 * Instantiates objects and window
	 */
	public Game() {
		//Add font, image and sound loaders
		Fonts.addFont(new Fonts("/fonts/BEBAS.ttf"));
		BufferedImageLoader loader = new BufferedImageLoader();
		AudioPlayer.load();
		
		//Try to load up the sprite sheet
		try {
			spriteSheet = loader.loadImage("/images/pieces.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Create level manager and HUD (Heads up display)
		level = new LevelManager(Levels.MENU);
		hud = new HUD();
		
		//Add input listeners
		this.addKeyListener(new KeyboardManager());
		this.addMouseListener(new MouseManager());
		
		//Create game window
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
		level.tick();
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
		
		//Render background
		g.setColor(new Color(66,55,21));
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		//Render level and HUD
		level.render(g);
		hud.render(g);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	/*
	 * Starts the game
	 */
	public static void main(String args[]) {
		new Game();
	}
}
