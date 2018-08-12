package engine;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	/*
	 * Load music files by: name - resource path
	 */
	public static void load() {
		try {
			musicMap.put("music", new Music("res/Music.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get sound by name
	 */
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
}
