package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Determines the velocity of an entity
 */
public class VelocityComponent implements Component {
  public Vector2 vector;
  // 0.25 tiles per second
  public float speed = 0.25f;
}
