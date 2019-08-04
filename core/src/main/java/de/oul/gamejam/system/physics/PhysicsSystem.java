package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.component.PhysicsComponent;

/**
 * Steps the box2d physics world and thus runs the simulation.
 */
public class PhysicsSystem extends EntitySystem {
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
}
