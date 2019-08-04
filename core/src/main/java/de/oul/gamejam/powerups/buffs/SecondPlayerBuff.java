package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.powerups.BuffStrategy;

public class SecondPlayerBuff implements BuffStrategy {
  private final ComponentMapper<PositionComponent> posM = ComponentMapper.getFor(PositionComponent.class);
  private final ComponentMapper<HealthComponent>   healthM = ComponentMapper.getFor(HealthComponent.class);

  private final PlayerFactory                      playerFactory;
  private final PooledEngine engine;
  private final Family playerFamily;
  private boolean enabled;

  public SecondPlayerBuff(PlayerFactory playerFactory, PooledEngine engine) {
    this.playerFactory = playerFactory;
    this.engine = engine;
    this.playerFamily = Family.all(PlayerComponent.class).get();
  }

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    if(enabled) {
      return "Twice the fun!";
    } else {
      return "There are too many!";
    }
  }

  @Override
  public void buff(Entity player){
    if(engine.getEntitiesFor(playerFamily).size() > 20) {
      enabled = false;
      return;
    }
    enabled = true;

    PositionComponent pos = posM.get(player);
    HealthComponent health = healthM.get(player);
    health.max /= 2;
    if(health.max <10){
      health.max =10;
    }
    if(health.current > health.max) {
      health.current = health.max;
    }

    Entity otherPlayer = playerFactory.createPlayer(pos.vector.x, pos.vector.y);
    HealthComponent otherHealth = healthM.get(otherPlayer);
    otherHealth.max = health.max;
    otherHealth.current = health.current;
  }
}
