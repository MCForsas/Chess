package objects.menu;

import java.awt.Font;

import engine.LevelManager;
import engine.Levels;

/*
 * Button which starts the game
 * @author MCForsas 2018
 */

public class ButtonPlay extends MenuButton{

	/*
	 * @param x button's x coordinate
	 * @param y button's y coordinate
	 * @param String title title to draw on button
	 * @param Font font to use for button title
	 */
	public ButtonPlay(int x, int y, String title, Font font) {
		super(x, y, title, font);
	}

	@Override
	public void tick() {
		//this.x++;
		if(isPressed()) {
			LevelManager.changeLevel(Levels.GAME);
		}
	}

}
