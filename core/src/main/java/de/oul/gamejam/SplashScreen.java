package de.oul.gamejam;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** First screen of the application. Displayed after the application is created. */
public class SplashScreen implements Screen {
  private SpriteBatch spriteBatch;
  private Texture launcherImage;
  private float elapsedTime = 0;

  private Game game;

  public SplashScreen(Game game){this.game = game;}

  /**
   * Initialize the game engine and the game world.
   */
  @Override
  public void show(){
    spriteBatch = new SpriteBatch();
    launcherImage = new Texture(Gdx.files.internal("launcher.png"));
  }

  /**
   * Update the systems in the ECS.
   *
   * @param delta The time passed since the last frame.
   */
  @Override
  public void render(float delta){

    spriteBatch.begin();
    spriteBatch.draw(launcherImage, (Gdx.graphics.getWidth()/2f) - (launcherImage.getWidth()/2f), (Gdx.graphics.getHeight()/2f) - (launcherImage.getHeight()/2f));
    spriteBatch.end();

    elapsedTime += delta;
    if(elapsedTime > 2f) {
      game.setScreen(new IntroScreen(game));
    }
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
