package objects.menu;

import java.awt.Font;

import engine.LevelManager;
import engine.Levels;

/*
 * Button which changes level to main menu
 * @author MCForsas 2018
 */

public class ButtonGotoMenu extends MenuButton{

	/*
	 * @param x button's x coordinate
	 * @param y button's y coordinate
	 * @param String title title to draw on button
	 * @param Font font to use for button title
	 */
	public ButtonGotoMenu(int x, int y, String title, Font font) {
		super(x, y, title, font);
	}

	@Override
	public void tick() {
		if(isPressed()) {
			LevelManager.changeLevel(Levels.MENU);
		}
	}

}
