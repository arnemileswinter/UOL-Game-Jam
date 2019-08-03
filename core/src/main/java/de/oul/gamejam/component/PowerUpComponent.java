package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import de.oul.gamejam.powerups.BuffStrategy;
import de.oul.gamejam.powerups.NerfStrategy;

public class PowerUpComponent implements Component, Pool.Poolable {
  public BuffStrategy buffStrategy;
  public NerfStrategy nerfStrategy;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
  }
}
