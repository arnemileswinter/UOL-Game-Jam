package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.oul.gamejam.JamGame;
import de.oul.gamejam.component.*;

public class UISystem extends EntitySystem {
  private final ComponentMapper<PositionComponent> pm     = ComponentMapper.getFor(PositionComponent.class);
  private final ComponentMapper<LabelComponent>    labelM = ComponentMapper.getFor(LabelComponent.class);
  private final ComponentMapper<DelayedRemove> delayedRm = ComponentMapper.getFor(DelayedRemove.class);

  private final Stage  stage;
  private final Family entitiesWithHealthBars;
  private final Family positionedLabels;
  private final Camera worldCamera;

  private final Vector3 projectionVector = new Vector3();

  public UISystem(Stage stage, Camera worldCamera){
    this.stage = stage;
    this.worldCamera = worldCamera;
    this.entitiesWithHealthBars = Family
            .all(HealthComponent.class, PositionComponent.class)
            .exclude(FocusedComponent.class)
            .get();
    positionedLabels = Family.all(PositionComponent.class, LabelComponent.class).get();
  }

  @Override
  public void update(float delta){
    stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    stage.getCamera().position.set(stage.getCamera().viewportWidth / 2.0f, stage.getCamera().viewportHeight / 2.0f, 0);
    stage.act(delta);
    stage.draw();
    stage.getBatch().begin();
    drawHealthBars(stage.getBatch());
    drawPowerupLabels(stage.getBatch());
    stage.getBatch().end();
  }

  private void drawPowerupLabels(Batch batch){
    for (Entity labeled : getEngine().getEntitiesFor(positionedLabels)) {
      PositionComponent positionComponent = pm.get(labeled);

      projectionVector.x = positionComponent.vector.x;
      projectionVector.y = positionComponent.vector.y;

      projectionVector.set(worldCamera.project(projectionVector));

      LabelComponent l = labelM.get(labeled);

      l.label.setPosition(projectionVector.x, projectionVector.y);

      l.label.setSize(JamGame.PIXELS_PER_METER * 1.5f, JamGame.PIXELS_PER_METER * 0.1f);
      l.label.setPosition(projectionVector.x - (l.label.getWidth() * 0.5f),
                                            projectionVector.y + (0.75f * JamGame.PIXELS_PER_METER));


      DelayedRemove delayed = delayedRm.get(labeled);

      if(delayed != null) {
        l.label.draw(batch, delayed.lifeTime - delayed.elapsedTime);
      } else {
        l.label.draw(batch, 1);
      }
    }
  }

  private void drawHealthBars(Batch batch){
    for (Entity healthy : getEngine().getEntitiesFor(entitiesWithHealthBars)) {
      HealthComponent healthComponent = healthy.getComponent(HealthComponent.class);
      if (healthComponent.current == healthComponent.max) {
        continue;
      }

      PositionComponent positionComponent = pm.get(healthy);

      projectionVector.x = positionComponent.vector.x;
      projectionVector.y = positionComponent.vector.y;

      projectionVector.set(worldCamera.project(projectionVector));

      healthComponent.healthBar.setSize(JamGame.PIXELS_PER_METER * 1.5f, JamGame.PIXELS_PER_METER * 0.1f);
      healthComponent.healthBar.setPosition(projectionVector.x - (healthComponent.healthBar.getWidth() * 0.5f),
                                            projectionVector.y + (0.75f * JamGame.PIXELS_PER_METER));
      healthComponent.healthBar.draw(batch, 1);
    }
  }
}
