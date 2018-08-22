package objects.menu;

import java.awt.Font;

import engine.LevelManager;
import engine.Levels;

public class ButtonGotoMenu extends MenuButton{

	public ButtonGotoMenu(int x, int y, String title, Font font) {
		super(x, y, title, font);
	}

	@Override
	public void tick() {
		//this.x++;
		if(isPressed()) {
			LevelManager.changeLevel(Levels.MENU);
		}
	}

}
