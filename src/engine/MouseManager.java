package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


public class MouseManager extends MouseAdapter{
	
	private static Map<Integer, Boolean> mouseMap = new HashMap<Integer, Boolean>();
	
	private static int mouseX = 0;
	private static int mouseY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void mousePressed(MouseEvent e){
 		mouseMap.put(e.getButton(), true);
 		mouseX = e.getX();
		mouseY = e.getY();
 	}
	
	public void mouseReleased(MouseEvent e) {
		mouseMap.put(e.getButton(), false);
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	
	public static boolean getMouseButtonPressed(int key) {
		if(mouseMap.containsKey(key)) { 
			return mouseMap.get(key);
		}else {
			return false;
		}
	}
	
	public static boolean isClicked(int x, int y, int width, int height) {
		return ((x >= mouseX && x + width <= mouseX) && (y >= mouseY && y + height <= mouseY));
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		MouseManager.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		MouseManager.mouseY = mouseY;
	}
}
