package de.oul.gamejam.powerups;

import com.badlogic.ashley.core.Entity;

public interface BuffStrategy {
  /**
   * @return The title of the buff to be displayed.
   */
  String name();
  void buff(Entity player);
}
