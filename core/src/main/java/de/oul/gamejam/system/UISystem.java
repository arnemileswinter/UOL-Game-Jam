package de.oul.gamejam.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class UISystem extends EntitySystem {
  private final Stage stage;

  public UISystem(Stage stage){
    this.stage = stage;
  }

  @Override
  public void update(float delta) {
    stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    stage.getCamera().position.set(stage.getCamera().viewportWidth/2.0f,stage.getCamera().viewportHeight/2.0f,0);

    stage.act(delta);
    stage.draw();
  }
}
