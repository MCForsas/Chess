package engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/*
 * Holds maps of keyboard keys and returns them
 * @author MCForsas 2018
 */

public class KeyboardManager extends KeyAdapter{

	private static Map<String, Boolean> keyMap = new HashMap<String, Boolean>();
	private static String lastKey;
	private static boolean isKeyTyped;
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyMap.put(KeyEvent.getKeyText(e.getKeyCode()), true);
		lastKey = KeyEvent.getKeyText(e.getKeyCode());
		isKeyTyped = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyMap.put(KeyEvent.getKeyText(e.getKeyCode()), false);
		lastKey = null;
		isKeyTyped = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
		isKeyTyped = true;
	}

	/*
	 * Returns if key is pressed or not
	 * @param String key "A", "B" , "C" , "numpad-1"
	 * @return boolean
	 */
	public static boolean getKeyPressed(String key) {
		if (keyMap.containsKey(key)) {
			return keyMap.get(key);
		} else {
			return false;
		}
	}
	
	/*
	 * Returns last typed key if it was typed
	 * @return string last key
	 */
	public static String getKeyTyped() {
		if(isKeyTyped) {
			return lastKey;
		}else {
			return null;
		}
	}

	/*
	 * Returns 1 if key is pressed or 0 if not
	 * @param String key "A", "B" , "C" , "numpad-1"
	 * @return short
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
