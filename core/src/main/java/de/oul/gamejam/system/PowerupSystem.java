package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PowerUpComponent;
import de.oul.gamejam.component.ToRemoveComponent;
import de.oul.gamejam.entity.PowerupLabelFactory;

public class PowerupSystem extends IteratingSystem {

  private final ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);
  private final ComponentMapper<PowerUpComponent> powm = ComponentMapper.getFor(PowerUpComponent.class);
  private final PowerupLabelFactory labelFactory;


  public PowerupSystem(PowerupLabelFactory labelFactory){
    super(Family.all(PlayerComponent.class, PhysicsComponent.class).get());
    this.labelFactory = labelFactory;
  }

  @Override
  protected void processEntity(Entity player, float deltaTime){
    PhysicsComponent phyC = phym.get(player);
    if(phyC.colliding == null) return;
    PowerUpComponent powC = powm.get(phyC.colliding);
    if(powC == null) return;
    Entity powerUp = phyC.colliding;
    powC.buffStrategy.buff(player);
    labelFactory.create(phyC.body.getPosition().x - 0.5f, phyC.body.getPosition().y + 0.5f, powC.buffStrategy.name(), true);
    powC.nerfStrategy.nerf(player);
    labelFactory.create(phyC.body.getPosition().x + 0.5f, phyC.body.getPosition().y - 0.5f, powC.nerfStrategy.name(), false);

    // The Power-Up is collected and applied.
    powerUp.add(getEngine().createComponent(ToRemoveComponent.class));
  }
}
