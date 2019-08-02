package de.oul.gamejam;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Game extends Game {
	@Override
	public void create() {
		setScreen(new FirstScreen());
	}
}