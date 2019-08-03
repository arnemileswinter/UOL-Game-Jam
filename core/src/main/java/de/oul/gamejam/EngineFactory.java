package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.system.InputSystem;
import de.oul.gamejam.system.RenderingSystem;

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
    PooledEngine pooledEngine = new PooledEngine();

    // add systems.
    pooledEngine.addSystem(new RenderingSystem());
    pooledEngine.addSystem(new InputSystem());
    // TODO: Physics

    return pooledEngine;
  }
}
