package de.oul.gamejam.system.physics;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsDebugRenderSystem extends EntitySystem {
  private final World world;
  private final Box2DDebugRenderer debugRenderer;
  private final Camera camera;

  public PhysicsDebugRenderSystem(World world, Camera camera) {
    this.world = world;
    this.debugRenderer = new Box2DDebugRenderer();
    this.camera = camera;
  }

  @Override
  public void update(float deltaTime) {
    debugRenderer.render(world, camera.combined);
  }
}
