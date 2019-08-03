package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PowerUpComponent;
import de.oul.gamejam.component.ToRemoveComponent;

public class PowerupSystem extends IteratingSystem {

  private final ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);
  private final ComponentMapper<PowerUpComponent> powm = ComponentMapper.getFor(PowerUpComponent.class);


  public PowerupSystem(){
    super(Family.all(PlayerComponent.class, PhysicsComponent.class).get());
  }

  @Override
  protected void processEntity(Entity player, float deltaTime){
    PhysicsComponent phyC = phym.get(player);
    if(phyC.colliding == null) return;
    PowerUpComponent powC = powm.get(phyC.colliding);
    if(powC == null) return;
    Entity powerUp = phyC.colliding;
    powC.buffStrategy.buff(player);
    powC.nerfStrategy.nerf(player);

    // The Power-Up is collected and applied.
    powerUp.add(getEngine().createComponent(ToRemoveComponent.class));
  }
}
