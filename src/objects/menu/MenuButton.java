package objects.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import engine.MouseManager;
import objects.GameObject;

public abstract class MenuButton extends GameObject {
	private int padding = 8;
	private String title;
	private int width, height, fontSize;
	private Font font;
	
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
	
	protected boolean isPressed() {
		int mX = MouseManager.getMouseX();
		int mY = MouseManager.getMouseY();
		if(
			MouseManager.getMouseButtonPressed(1) &&
			(mX > this.x-padding && mX < this.x + this.width + padding) && 
			(mY > this.y-padding && mY < this.y + this.height + padding)
		)	{
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
