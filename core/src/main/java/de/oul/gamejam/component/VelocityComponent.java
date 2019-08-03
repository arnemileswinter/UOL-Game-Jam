package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Determines the velocity of an entity
 */
public class VelocityComponent implements Component {
  public Vector2 vector = new Vector2();
  public float speed = 0.1f;
}
