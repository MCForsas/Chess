package objects.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import engine.MouseManager;
import objects.GameObject;

/*
 * Button, which can be pressed and performs action
 * @author MCForsas 2018
 */

public abstract class MenuButton extends GameObject {
	
	private int padding = 8;
	private String title;
	private int width, height, fontSize;
	private Font font;
	
	
	/*
	 * @param x button's x coordinate
	 * @param y button's y coordinate
	 * @param String title title to draw on button
	 * @param Font font to use for button title
	 */
	public MenuButton(int x, int y, String title, Font font) {
		super(x, y);
		this.title = title;
		this.font = font;
		this.fontSize = font.getSize()/2; 
		this.width = (title.length()*this.fontSize)+padding*2;
		this.height = this.fontSize+this.padding*2;
		this.x = x - width/2 + padding;
		this.y = y;
	}
	
	/*
	 * Checks if mouse cursor is on button and and if left mouse button is pressed
	 * @returns boolean isPressed is cursor on button and is pressed?
	 */
	protected boolean isPressed() {
		if(MouseManager.getMouseButtonPressed(1) && MouseManager.isMouseCursorInRectangle(this.x, this.y, this.width + this.padding, this.height + this.padding)){
			return true;
		}else {
			return false;
		}
			
	}
	
	public abstract void tick();

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(this.x-padding, this.y-padding, width+padding*2, height+padding*2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.x, this.y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(this.font);
		g.drawString(this.title, x+padding, this.y+height-padding/2);
	}
}
