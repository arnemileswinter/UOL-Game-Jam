package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.component.ShootingCreatesEnemiesComponent;
import de.oul.gamejam.component.ViewComponent;
import de.oul.gamejam.entity.BulletFactory;
import de.oul.gamejam.entity.EnemyFactory;

public class ShootingSystem extends IteratingSystem {
  private final BulletFactory bulletFactory;
  private       EnemyFactory  enemyFactory;

  public ShootingSystem(BulletFactory bulletFactory, EnemyFactory enemyFactory){
    super(Family.all(ShootingComponent.class, PositionComponent.class).get());
    this.bulletFactory = bulletFactory;
    this.enemyFactory = enemyFactory;
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime){
    ShootingComponent shootingComponent = entity.getComponent(ShootingComponent.class);
    if (!shootingComponent.isShooting) return;

    if (shootingComponent.timeSinceLastShot < shootingComponent.shootSpeed) {
      shootingComponent.timeSinceLastShot += deltaTime;
      return;
    }

    PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

    ViewComponent viewComponent = entity.getComponent(ViewComponent.class);

    shootingComponent.timeSinceLastShot -= shootingComponent.shootSpeed;
    if(entity.getComponent(ShootingCreatesEnemiesComponent.class) == null) {
      bulletFactory.shootBullet(entity, 1, positionComponent.vector, shootingComponent.bulletSpeed,     viewComponent.view.angle);
    } else {
      enemyFactory.spawn((int) positionComponent.vector.x, (int) positionComponent.vector.y);
    }
  }
}
