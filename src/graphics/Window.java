package graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import engine.Game;

public class Window extends Canvas {

	private static final long serialVersionUID = -240840600533728354L;

	/*
	 *Creates window, adds game to it and starts the game
	 *@param {int} width window width
	 *@param {int} height window height
	 *@param {String} title window title
	 *@param {Game} game game object
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
