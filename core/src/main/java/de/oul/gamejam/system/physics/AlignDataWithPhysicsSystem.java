package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.VelocityComponent;

/**
 * Applies the location of the transform component onto the physics body.
 * Should be invoked before the stepping the physics world.
 * @see PhysicsSystem
 */
public class AlignDataWithPhysicsSystem extends IteratingSystem {
  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public AlignDataWithPhysicsSystem(){
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
    // Phyiscs without a position is no physics at all!
    if(position == null) return;

    physics.colliding = null;
    physics.body.setTransform(position.vector, physics.body.getAngle());

    VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
    if(velocityComponent != null) {
      physics.body.setLinearVelocity(velocityComponent.vector);
    }
  }
}
