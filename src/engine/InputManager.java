package engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;


public class InputManager extends KeyAdapter {

	private static Map<String, Boolean> keyMap = new HashMap<String, Boolean>();

	public void keyPressed(KeyEvent e) {
		keyMap.put(KeyEvent.getKeyText(e.getKeyCode()), true);
	}

	public void keyReleased(KeyEvent e) {
		keyMap.put(KeyEvent.getKeyText(e.getKeyCode()), false);
	}

	/*
	 * Returns if key is pressed or not
	 * @param {String} key "A", "B" , "C" , "numpad-1"
	 * @return {boolean}
	 */
	public static boolean getKeyPressed(String key) {
		if (keyMap.containsKey(key)) {
			return keyMap.get(key);
		} else {
			return false;
		}
	}

	/*
	 * Returns 1 if key is pressed or 0 if not
	 * @param {String} key "A", "B" , "C" , "numpad-1"
	 * @return {short}
	 */
	public static short getKeyPressedInt(String key) {
		if (keyMap.containsKey(key)) {
			if (keyMap.get(key)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
}
