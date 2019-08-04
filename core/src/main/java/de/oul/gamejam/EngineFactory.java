package de.oul.gamejam;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import de.oul.gamejam.EntityListener.PlayerBuffer;
import de.oul.gamejam.EntityListener.PlayerListener;
import de.oul.gamejam.entity.BulletFactory;
import de.oul.gamejam.entity.MapTileFactory;
import de.oul.gamejam.entity.PowerupLabelFactory;
import de.oul.gamejam.entity.*;
import de.oul.gamejam.powerups.RandomPowerUpEffectProvider;
import de.oul.gamejam.system.*;
import de.oul.gamejam.system.physics.AlignDataWithPhysicsSystem;
import de.oul.gamejam.system.physics.AlignPhysicsWithDataSystem;
import de.oul.gamejam.system.physics.PhysicsDebugRenderSystem;
import de.oul.gamejam.system.physics.PhysicsSystem;
import de.oul.gamejam.ui.HealthBar;
import de.oul.gamejam.ui.UI;
import de.oul.gamejam.world.LevelFactory;

/**
 * Initializes the engine with all its required systems.
 */
public class EngineFactory {
  /**
   * The box2d Physics world
   */
  private final World      world;
  private final Scoreboard scoreboard;

  public EngineFactory(World world, Scoreboard scoreboard){
    this.scoreboard = scoreboard;
    this.world = world;
  }

  public PooledEngine createEngine(Game game){
    Camera camera = new OrthographicCamera();

    PooledEngine pooledEngine = new PooledEngine();

    // Game rules
    pooledEngine.addSystem(new GoalSystem(game));

    PlayerBuffer playerBuffer = new PlayerBuffer();
    pooledEngine.addSystem(new PlayerListener(playerBuffer));
    // add player.
    PlayerFactory playerFactory = new PlayerFactory(pooledEngine, world);
    playerFactory.createPlayer(5, 5);

    // add systems.

    // housekeeping
    pooledEngine.addSystem(new DelayedRemoval());
    pooledEngine.addSystem(new ToRemoveSystem());

    // visibility
    pooledEngine.addSystem(new VisibilitySystem(camera));
    pooledEngine.addSystem(new RenderingSystem(camera));
    pooledEngine.addSystem(new SwitchAssetSystem());

    // player systems
    pooledEngine.addSystem(new CameraFocusPlayerSystem(camera, playerBuffer));
    pooledEngine.addSystem(new InputSystem());
    pooledEngine.addSystem(new PlayerDeathSystem());

    // Enemy Systems
    EnemyFactory enemyFactory = new EnemyFactory(pooledEngine, world);
    pooledEngine.addSystem(new EnemyDeathSystem(scoreboard));
    LevelFactory levelFactory = new LevelFactory(new MapTileFactory(pooledEngine, world));
    levelFactory.createLevel();
    pooledEngine.addSystem(new SpawnSystem(levelFactory, enemyFactory));
    pooledEngine.addSystem(new MoveEnemySystem());

    // combat
    pooledEngine.addSystem(new ShootingSystem(new BulletFactory(pooledEngine, world), enemyFactory));
    pooledEngine.addSystem(new BulletHurtSystem());

    PowerupFactory powerupFactory = new PowerupFactory(pooledEngine,
                                                       world,
                                                       new RandomPowerUpEffectProvider(pooledEngine, playerFactory));
    pooledEngine.addSystem(new PowerupSpawnSystem(powerupFactory));
    pooledEngine.addSystem(new PowerupSystem(new PowerupLabelFactory(pooledEngine)));

    // add physics systems.
    world.setContactListener(new EntityCollisionListener());
    world.setContactFilter(new BulletCollisionFilter());
    pooledEngine.addSystem(new AlignDataWithPhysicsSystem());
    pooledEngine.addSystem(new PhysicsSystem(world));
    pooledEngine.addSystem(new AlignPhysicsWithDataSystem());
    // pooledEngine.addSystem(new PhysicsDebugRenderSystem(world, camera));
    pooledEngine.addSystem(new ViewSystem());

    // add UI
    HealthBar healthBar = new HealthBar();
    UI        ui        = new UI(healthBar, scoreboard);
    ui.setFillParent(true);

    pooledEngine.addSystem(new HealthBarSystem(healthBar));

    Stage stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    stage.addActor(ui);
    pooledEngine.addSystem(new UISystem(stage, camera));

    return pooledEngine;
  }
}
