package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseManager extends MouseAdapter{
	public static int mouseX = 0;
	public static int mouseY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
 	public void mousePressed(MouseEvent e){
 		
 	}
}
