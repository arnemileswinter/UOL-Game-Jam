package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DelayedRemove implements Component, Pool.Poolable {
  public float lifeTime    = 2;
  public float elapsedTime = 0;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    lifeTime = 2;
    elapsedTime = 0;
  }
}
