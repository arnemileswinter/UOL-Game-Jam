package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsSystem extends EntitySystem {
  private final World world;
  private static final float         MAX_STEP_TIME       = 1 / 45f;
  private static final int           VELOCITY_ITERATIONS = 6;
  private static final int           POSITION_ITERATIONS = 2;
  private float accumulator;

  public PhysicsSystem(World world){
    this.world = world;
  }

  @Override
  public void update(float delta) {
    // Step the box2d world (update it)
    float frameTime = Math.min(delta, 0.25f);
    accumulator += frameTime;

    if (accumulator >= MAX_STEP_TIME) {
      this.world.step(MAX_STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
      accumulator -= MAX_STEP_TIME;
    }
  }
}
