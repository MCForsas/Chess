package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


public class MouseManager extends MouseAdapter{
	
	private static Map<Integer, Boolean> mouseMap = new HashMap<Integer, Boolean>();
	
	public static int mouseX = 0;
	public static int mouseY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void mousePressed(MouseEvent e){
 		mouseMap.put(e.getButton(), true);
 	}
	
	public void mouseReleased(MouseEvent e) {
		mouseMap.put(e.getButton(), false);
	}
	
	public static boolean getMouseButtonPressed(int key) {
		if(mouseMap.containsKey(key)) { 
			return mouseMap.get(key);
		}else {
			return false;
		}
	}
}
