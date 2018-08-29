package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import engine.Game;

/*
 * Renders HUD
 * @author MCForsas 2018
 */

public class HUD {
	
	private Color textColor = Color.WHITE;
	private Font hudFont;
	private static String winner = null;
	
	/*
	 * Tick
	 */
	public void tick() {

	}

	/*
	 * Render any hud elements there are
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		int fontSize = 28;
		g.setColor(textColor);
		
		hudFont = new Font("Bebas", Font.TRUETYPE_FONT, fontSize);
		g.setFont(hudFont);
		
		String title = "Chess";
		int width = title.length()*fontSize;
		
		//Draw game title
		g.drawString(title,Game.WINDOW_WIDTH/2-width/4, fontSize);
		
		//Draw winner color
		if(winner != null) {
			width = winner.length()*fontSize;
			g.drawString(winner,Game.WINDOW_WIDTH/2-width/4, fontSize*2);
		}
	}
	
	/*
	 * Set winner string 
	 * @param String winnerName winner color (Black/White)
	 */
	public static void setWinner(String winnerName) {
		winner = winnerName;
	}
}
