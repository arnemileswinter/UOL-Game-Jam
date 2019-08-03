package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kotcrab.vis.ui.VisUI;
import de.oul.gamejam.entity.BulletFactory;
import de.oul.gamejam.system.*;
import de.oul.gamejam.system.physics.AlignPhysicsWithDataSystem;
import de.oul.gamejam.system.physics.AlignDataWithPhysicsSystem;
import de.oul.gamejam.system.physics.PhysicsDebugRenderSystem;
import de.oul.gamejam.system.physics.PhysicsSystem;
import de.oul.gamejam.ui.HealthBar;
import de.oul.gamejam.ui.UI;

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
    Camera camera = new OrthographicCamera();

    PooledEngine pooledEngine = new PooledEngine();

    // add systems.
    pooledEngine.addSystem(new RenderingSystem(camera));
    pooledEngine.addSystem(new SwitchAssetSystem());

    // player systems
    pooledEngine.addSystem(new CameraFocusPlayerSystem(camera));
    pooledEngine.addSystem(new InputSystem());

    // combat
    pooledEngine.addSystem(new ShootingSystem(new BulletFactory(pooledEngine, world)));

    pooledEngine.addSystem(new SpawnSystem(pooledEngine,world));

    // add physics systems.
    world.setContactListener(new EntityCollisionListener());
    world.setContactFilter(new BulletCollisionFilter());
    pooledEngine.addSystem(new AlignDataWithPhysicsSystem());
    pooledEngine.addSystem(new PhysicsSystem(world));
    pooledEngine.addSystem(new AlignPhysicsWithDataSystem());
    pooledEngine.addSystem(new PhysicsDebugRenderSystem(world, camera));
    pooledEngine.addSystem(new ViewSystem());

    // add UI
    VisUI.load();
    HealthBar healthBar = new HealthBar();
    UI ui = new UI(healthBar);
    ui.setFillParent(true);

    pooledEngine.addSystem(new HealthBarSystem(healthBar));

    Stage stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    stage.addActor(ui);
    pooledEngine.addSystem(new UISystem(stage));

    return pooledEngine;
  }
}
