package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ShootingComponent implements Component, Pool.Poolable {
  public boolean isShooting;
  public float bulletSpeed = 2;
  public float shootSpeed = 1;
  public float timeSinceLastShot = 0;
  public float bulletDamage = 10;


  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    isShooting = false;
    shootSpeed = 1f;
    timeSinceLastShot = 0f;
    bulletSpeed = 1f;
    bulletDamage = 1;
  }
}
