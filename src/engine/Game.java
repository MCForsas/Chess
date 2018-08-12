package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.SpriteSheet;
import graphics.Window;
import objects.Player;

import java.awt.Graphics;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1178041823728909735L;
	public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
	public static int FPS;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	public static BufferedImage spriteSheet;

	/*
	 * Instantiates objects and window
	 */
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		this.addKeyListener(new InputManager());
		handler = new Handler();

		try {
			spriteSheet = loader.loadImage("/Sheet.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet, 32);
		
		// AudioPlayer.load();
		// AudioPlayer.getMusic("music").loop();
		
		handler.addObject(new Player(WIDTH / 2, HEIGHT / 2, ID.Player, spriteSheet.grabImage(1, 1, 32, 32)));
		
		new Window(WIDTH, HEIGHT, "The game", this);
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

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		g.dispose();
		bufferStrategy.show();
	}

	public static void main(String args[]) {
		new Game();
	}
}
