package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class BulletComponent implements Component, Pool.Poolable {
  public Entity owner;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    owner = null;
  }
}
