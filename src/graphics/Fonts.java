package graphics;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;

/*
 * Used to load and store fonts
 * @author MCForsas 2018
 */

public class Fonts {
	private static ArrayList<Fonts> fontList = new ArrayList<Fonts>();
	private static String fontPath;
	
	public Fonts(String filePath) {
		Fonts.fontPath = "./res/" + filePath;
		registerFont();
	}
	
	private void registerFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addFont(Fonts font) {
		fontList.add(font);
	}
}
