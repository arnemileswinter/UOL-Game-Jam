package de.oul.gamejam.powerups.nerfs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.ShootingCreatesEnemiesComponent;
import de.oul.gamejam.powerups.NerfStrategy;

public class ShootingCreatesEnemiesNerf implements NerfStrategy {
  private final PooledEngine engine;
  ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

  public ShootingCreatesEnemiesNerf(PooledEngine pooledEngine) {
    this.engine = pooledEngine;
  }

  /**
   * @return The title of the nerf to be displayed.
   */
  @Override
  public String name(){
    return "shoot enemies!";
  }

  @Override
  public void nerf(Entity player){
    player.add(engine.createComponent(ShootingCreatesEnemiesComponent.class));
  }
}
