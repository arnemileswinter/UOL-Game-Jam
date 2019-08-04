package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import de.oul.gamejam.component.*;
import de.oul.gamejam.component.DelayedRemove;

public class PowerupLabelFactory {
  private final PooledEngine          engine;

  public PowerupLabelFactory(PooledEngine engine){
    this.engine = engine;
  }

  public Entity create(float x, float y, String text, boolean positiveEffect){
    Entity entity = engine.createEntity();

    PositionComponent position = engine.createComponent(PositionComponent.class);
    position.vector.x = x;
    position.vector.y = y;
    entity.add(position);

    LabelComponent label = engine.createComponent(LabelComponent.class);
    if(positiveEffect){
      label.label.setColor(Color.GREEN);
    } else {
      label.label.setColor(Color.RED);
    }
    label.label.setText(text);
    entity.add(label);

    DelayedRemove delayedRemove = engine.createComponent(DelayedRemove.class);
    delayedRemove.lifeTime = 4;
    entity.add(delayedRemove);

    engine.addEntity(entity);
    return entity;
  }
}
