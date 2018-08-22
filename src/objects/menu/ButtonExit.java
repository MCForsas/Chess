package objects.menu;

import java.awt.Font;

public class ButtonExit extends MenuButton{

	public ButtonExit(int x, int y, String title, Font font) {
		super(x, y, title, font);
	}

	@Override
	public void tick() {
		//this.x++;
		if(isPressed()) {
			System.exit(1);
		}
	}

}
