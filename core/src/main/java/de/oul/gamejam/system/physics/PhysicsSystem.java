package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.component.PhysicsComponent;

/**
 * Steps the box2d physics world and thus runs the simulation.
 */
public class PhysicsSystem extends EntitySystem implements EntityListener {
  /** How long a step might take. */
  private static final float MAX_STEP_TIME       = 1 / 45f;
  /** How accurate velocity simulations are. */
  private static final int   VELOCITY_ITERATIONS = 6;
  /** How accurate positioning is. */
  private static final int   POSITION_ITERATIONS = 2;
  /** Physics world to step. */
  private final        World world;
  /** Used to tick at an interval. */
  private              float accumulator;

  /**
   * Steps the box2d physics world.
   *
   * @param world The box2d Physics World to step.
   */
  public PhysicsSystem(World world){
    this.world = world;
  }

  @Override
  public void update(float delta){
    // Step the box2d world (update it)
    float frameTime = Math.min(delta, 0.25f);
    accumulator += frameTime;

    if (accumulator >= MAX_STEP_TIME) {
      this.world.step(MAX_STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
      accumulator -= MAX_STEP_TIME;
    }
  }

  /**
   * Add this system as a listener to entities with a physics component.
   *
   * @param engine The engine that is maintaining the ECS.
   */
  @Override
  public void addedToEngine(Engine engine){
    super.addedToEngine(engine);
    engine.addEntityListener(Family.all(PhysicsComponent.class).get(), this);
  }

  /**
   * Called when an entity with a physics body is added to the engine. Teaches the box2d physics engine that a body is
   * associated with an entity.
   *
   * @param entity The entity that is to have physics.
   */
  @Override
  public void entityAdded(Entity entity){
  }

  /**
   * Destroy the physics body associated with this entity. Called when an entity with a physics component is removed
   * from the engine.
   *
   * @param entity The entity that has physics.
   */
  @Override
  public void entityRemoved(Entity entity){
    world.destroyBody(entity.getComponent(PhysicsComponent.class).body);
  }
}
