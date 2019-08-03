package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class HealthComponent implements Component, Pool.Poolable {
  public float current = 100;
  public float max = 100;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    current = 100;
    max = 100;
  }
}
