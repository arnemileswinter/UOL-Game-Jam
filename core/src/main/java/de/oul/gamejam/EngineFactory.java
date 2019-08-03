package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.system.RenderingSystem;
import de.oul.gamejam.system.physics.AlignPhysicsWithTransformSystem;
import de.oul.gamejam.system.physics.AlignTransformWithPhysicsSystem;
import de.oul.gamejam.system.physics.PhysicsDebugRenderSystem;
import de.oul.gamejam.system.physics.PhysicsSystem;

import static de.oul.gamejam.JamGame.PIXELS_PER_METER;

/**
 * Initializes the engine with all its required systems.
 */
public class EngineFactory {
  /**
   * The box2d Physics world
   */
  private final World world;

  public EngineFactory(World world){
    this.world = world;
  }

  public PooledEngine createEngine() {
    Camera camera = new OrthographicCamera(Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight() / PIXELS_PER_METER);

    PooledEngine pooledEngine = new PooledEngine();

    // add systems.
    pooledEngine.addSystem(new RenderingSystem(camera));

    // add physics systems.
    pooledEngine.addSystem(new AlignPhysicsWithTransformSystem());
    pooledEngine.addSystem(new PhysicsSystem(world));
    pooledEngine.addSystem(new AlignTransformWithPhysicsSystem());
    pooledEngine.addSystem(new PhysicsDebugRenderSystem(world, camera));

    return pooledEngine;
  }
}
