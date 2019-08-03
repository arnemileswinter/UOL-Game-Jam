package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.component.ViewComponent;
import de.oul.gamejam.entity.BulletFactory;

public class ShootingSystem extends IteratingSystem {
  private final BulletFactory bulletFactory;

  public ShootingSystem(BulletFactory bulletFactory){
    super(Family.all(ShootingComponent.class, PositionComponent.class).get());
    this.bulletFactory = bulletFactory;
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
    bulletFactory.shootBullet(entity, 1, positionComponent.vector, shootingComponent.bulletSpeed,     viewComponent.view.angle);
  }
}
