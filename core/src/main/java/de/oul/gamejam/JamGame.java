package de.oul.gamejam;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class JamGame extends Game {
	/** How many pixels make up a meter */
	public static final float PIXELS_PER_METER = 32;

	@Override
	public void create() {
		setScreen(new FirstScreen());
	}
}
