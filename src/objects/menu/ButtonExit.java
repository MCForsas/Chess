package objects.menu;

import java.awt.Font;

/*
 * Button which exit's game
 * @author MCForsas 2018
 */

public class ButtonExit extends MenuButton{

	/*
	 * @param x button's x coordinate
	 * @param y button's y coordinate
	 * @param String title title to draw on button
	 * @param Font font to use for button title
	 */
	public ButtonExit(int x, int y, String title, Font font) {
		super(x, y, title, font);
	}

	@Override
	public void tick() {
		if(isPressed()) {
			System.exit(1);
		}
	}

}
