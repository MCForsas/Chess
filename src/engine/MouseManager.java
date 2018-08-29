package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/*
 * Manages mouse, stores it's coordinates and button presses
 * @author MCForsas 2018
 */

public class MouseManager extends MouseAdapter{
	
	private static Map<Integer, Boolean> mouseMap = new HashMap<Integer, Boolean>();
	
	private static int mouseX = 0;
	private static int mouseY = 0;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e){
 		mouseMap.put(e.getButton(), true);
 		mouseX = e.getX();
		mouseY = e.getY();
 	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseMap.put(e.getButton(), false);
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	/*
	 * Returns if key is pressed
	 * @param int key mouse key number
	 * @return boolean isKeyPressed
	 */
	public static boolean getMouseButtonPressed(int key) {
		if(mouseMap.containsKey(key)) { 
			return mouseMap.get(key);
		}else {
			return false;
		}
	}
	
	/*
	 * Returns true if mouse is in rectangle which is at x, y, of width and height
	 * @param int x start x position
	 * @param int y start y position
	 * @param int width width of the rectangle
	 * @param int height height of the rectangle
	 * @return boolean isInRectangle
	 */
	public static boolean isMouseCursorInRectangle(int x, int y, int width, int height) {
		//System.out.printf("mX: %d mY: %d %n bX: %d bY: %d %n w: %d h: %d %n ------------ %n", mouseX, mouseY, x, y, width, height);
		boolean isInRectangle = (
				(mouseX >= x && (mouseX <= x + width)) &&
				(mouseY >= y && (mouseY <= y + height))
			);
		//System.out.println(isInRectangle);
		return isInRectangle;
	}

	/*
	 * Returns mouse x coordinate on window
	 * @return int x mouse x
	 */
	public static int getMouseX() {
		return mouseX;
	}

	/*
	 * Returns mouse y coordinate on window
	 * @return int y mouse y
	 */
	public static int getMouseY() {
		return mouseY;
	}
}
