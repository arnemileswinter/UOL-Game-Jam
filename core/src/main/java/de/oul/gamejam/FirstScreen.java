package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.entity.PlayerFactory;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
	private PooledEngine engine;

	/**
	 * Initialize the game engine and the game world.
	 */
	@Override
	public void show() {
		World world = new World(new Vector2(0, 0), true);
		EngineFactory engineFactory = new EngineFactory(world);
		engine = engineFactory.createEngine();
		PlayerFactory playerFactory = new PlayerFactory(engine);
		playerFactory.createPlayer(0,0);
	}

	/**
	 * Update the systems in the ECS.
	 * @param delta The time passed since the last frame.
	 */
	@Override
	public void render(float delta) {
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// Resize your screen here. The parameters represent the new window size.
	}

	@Override
	public void pause() {
		// Invoked when your application is paused.
	}

	@Override
	public void resume() {
		// Invoked when your application is resumed after pause.
	}

	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}

	@Override
	public void dispose() {
		// Destroy screen's assets here.
	}
}
