package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsComponent implements Component {
  /** The box2d physics body assigned to this entity. */
  public Body body;
}
