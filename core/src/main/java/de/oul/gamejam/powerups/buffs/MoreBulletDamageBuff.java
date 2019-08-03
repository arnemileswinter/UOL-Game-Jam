package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.powerups.BuffStrategy;

public class MoreBulletDamageBuff implements BuffStrategy {
  private final ComponentMapper<ShootingComponent> shootingM = ComponentMapper.getFor(ShootingComponent.class);

  @Override
  public void buff(Entity player){
    ShootingComponent shootingComponent = shootingM.get(player);
    shootingComponent.bulletDamage += 10;
  }
}
