package de.oul.gamejam;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;

/** First screen of the application. Displayed after the application is created. */
public class IntroScreen implements Screen {
  private Stage stage;

  private Game game;

  public IntroScreen(Game game){this.game = game;}

  /**
   * Initialize the game engine and the game world.
   */
  @Override
  public void show(){
    stage = new Stage();
    Table table = new Table();
    table.add(new VisLabel("You are bacteria in a bad person's body."));
    table.row();
    table.add(new VisLabel("Doctor's have sent Nanobots into the systems to prevent you to dig deeper."));
    table.row();
    table.add(new VisLabel());
    table.row();
    table.add(new VisLabel("Move with W, A, S, D."));
    table.row();
    table.add(new VisLabel("Shoot by holding ENTER."));
    table.row();
    table.add(new VisLabel("TAB to cycle through your companions."));
    table.row();
    table.add(new VisLabel());
    table.row();
    table.add(new VisLabel("Find your way into the hole, to get deeper in the system!"));
    table.row();
    table.add(new VisLabel("But be prepared enemys all around you and nothing is as it seems"));
    table.row();
    table.add(new VisLabel("Press ENTER to continue."));
    table.setFillParent(true);
    stage.addActor(table);
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

    stage.act(delta);
    stage.draw();

    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
      game.setScreen(new FirstScreen(game));
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
