package de.oul.gamejam.powerups;

public interface PowerUpEffectProvider {
  BuffStrategy getBuffStrategy();

  NerfStrategy getNerfStrategy();
}
