package de.oul.gamejam.powerups;

import com.badlogic.ashley.core.Entity;

public interface NerfStrategy {
  void nerf(Entity player);
}
