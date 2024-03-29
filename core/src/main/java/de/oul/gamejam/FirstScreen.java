package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.entity.MapTileFactory;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.entity.PowerupFactory;
import de.oul.gamejam.powerups.RandomPowerUpEffectProvider;
import de.oul.gamejam.world.LevelFactory;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
  private final Game game;
  private final Scoreboard scoreboard;
  private PooledEngine engine;

  public FirstScreen(Game game, Scoreboard scoreboard) {
    this.game = game;
    this.scoreboard = scoreboard;
  }

  /**
   * Initialize the game engine and the game world.
   */
  @Override
  public void show(){
    World         world         = new World(new Vector2(0, 0), true);
    EngineFactory engineFactory = new EngineFactory(world, scoreboard == null ? new Scoreboard() : scoreboard);
    engine = engineFactory.createEngine(game);
  }

  /**
   * Update the systems in the ECS.
   *
   * @param delta The time passed since the last frame.
   */
  @Override
  public void render(float delta){
    Gdx.gl.glClearColor( 74f / 255f, 17f / 255 ,  17f / 255, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    engine.update(delta);
  }

  @Override
  public void resize(int width, int height){
    // Resize your screen here. The parameters represent the new window size.
  }

  @Override
  public void pause(){
    // Invoked when your application is paused.
  }

  @Override
  public void resume(){
    // Invoked when your application is resumed after pause.
  }

  @Override
  public void hide(){
    // This method is called when another screen replaces this one.
  }

  @Override
  public void dispose(){
    // Destroy screen's assets here.
  }
}
