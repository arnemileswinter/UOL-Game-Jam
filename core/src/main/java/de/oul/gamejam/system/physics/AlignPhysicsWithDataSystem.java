package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.VelocityComponent;

/**
 * Applies the transformation of a physics body onto the transform after
 * the world is stepped.
 * @see PhysicsSystem
 */
public class AlignPhysicsWithDataSystem extends IteratingSystem {
  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public AlignPhysicsWithDataSystem(){
    super(Family.all(PhysicsComponent.class).one(PositionComponent.class, VelocityComponent.class).get());
  }

  /**
   * This method is called on every entity on every update call of the EntitySystem. Override this to implement your
   * system's specific processing.
   *
   * @param entity    The current Entity being processed
   * @param deltaTime The delta time between the last and current frame
   */
  @Override
  protected void processEntity(Entity entity, float deltaTime){
    PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
    PositionComponent position = entity.getComponent(PositionComponent.class);

    if(position != null) {
      position.vector.set(physics.body.getPosition());
    }

    VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
    if(velocity != null) {
      velocity.vector.set(physics.body.getLinearVelocity());
    }
  }
}
