package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Entities with this component have physics.
 */
public class PhysicsComponent implements Component {
  /** The box2d physics body assigned to this entity. */
  public Body   body;
  /** Another entity that currently collides with this entity. */
  public Entity colliding;
}
