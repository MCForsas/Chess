package engine;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/*
 * Loads game sounds, stores them and returns
 * @author MCForsas 2018
 */

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	/*
	 * Load music files by: name - resource path
	 */
	public static void load() {
		try {
			musicMap.put("victory", new Music("res/sounds/victory.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get music by name
	 * @param string key
	 */
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	/*
	 * Get sound by name
	 * @param string key
	 */
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
