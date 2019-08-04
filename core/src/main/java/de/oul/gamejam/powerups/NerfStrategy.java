package de.oul.gamejam.powerups;

import com.badlogic.ashley.core.Entity;

public interface NerfStrategy {
  /**
   * @return The title of the nerf to be displayed.
   */
  String name();
  void nerf(Entity player);
}
