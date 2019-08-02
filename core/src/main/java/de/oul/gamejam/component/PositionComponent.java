package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Identifies an entity of having a vector.
 */
public class PositionComponent implements Component, Pool.Poolable {
  public Vector2 vector = new Vector2();

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    vector.setZero();
  }
}
