package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.BulletComponent;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.ShootingComponent;

public class BulletHurtSystem extends IteratingSystem {
  public BulletHurtSystem(){
    super(Family.all(BulletComponent.class, PhysicsComponent.class).get());
  }

  /**
   * This method is called on every entity on every update call of the EntitySystem. Override this to implement your
   * system's specific processing.
   *
   * @param bullet    The current Entity being processed
   * @param deltaTime The delta time between the last and current frame
   */
  @Override
  protected void processEntity(Entity bullet, float deltaTime){
    PhysicsComponent physicsComponent = bullet.getComponent(PhysicsComponent.class);
    if (physicsComponent.colliding == null) return;

    BulletComponent bulletComponent = bullet.getComponent(BulletComponent.class);

    HealthComponent enemyHealth = physicsComponent.colliding.getComponent(HealthComponent.class);
    if (enemyHealth != null && bulletComponent.owner.getComponent(ShootingComponent.class) != null){
      float damage = bulletComponent.owner.getComponent(ShootingComponent.class).bulletDamage;
      enemyHealth.current -= damage;
    }

    getEngine().removeEntity(bullet);
  }
}
