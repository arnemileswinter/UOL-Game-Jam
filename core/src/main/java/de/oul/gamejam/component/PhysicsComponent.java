package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

/**
 * Entities with this component have physics.
 */
public class PhysicsComponent implements Component, Pool.Poolable {
  /** The box2d physics body assigned to this entity. */
  public Body   body;
  /** Another entity that currently collides with this entity. */
  public Entity colliding;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    body = null;
    colliding = null;
  }
}
