package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.powerups.BuffStrategy;

public class SecondPlayerBuff implements BuffStrategy {
  private final ComponentMapper<PositionComponent> posM = ComponentMapper.getFor(PositionComponent.class);
  private final ComponentMapper<HealthComponent>   healthM = ComponentMapper.getFor(HealthComponent.class);

  private final PlayerFactory                      playerFactory;

  public SecondPlayerBuff(PlayerFactory playerFactory) {
    this.playerFactory = playerFactory;
  }

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Twice the fun!";
  }

  @Override
  public void buff(Entity player){
    PositionComponent pos = posM.get(player);
    HealthComponent health = healthM.get(player);
    health.max /= 2;
    if(health.current < health.max) {
      health.current = health.max;
    }

    Entity otherPlayer = playerFactory.createPlayer(pos.vector.x, pos.vector.y);
    HealthComponent otherHealth = healthM.get(otherPlayer);
    otherHealth.max = health.max;
    otherHealth.current = health.current;
  }
}
