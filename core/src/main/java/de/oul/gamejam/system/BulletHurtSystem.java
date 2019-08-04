package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import de.oul.gamejam.component.*;

public class BulletHurtSystem extends IteratingSystem {

  private final Sound playerHitSound;
  private final Sound robotHit;

  public BulletHurtSystem(){
    super(Family.all(BulletComponent.class, PhysicsComponent.class).get());

    playerHitSound = Gdx.audio.newSound(Gdx.files.internal("SoundsOGG/playerHit.ogg"));
    robotHit = Gdx.audio.newSound(Gdx.files.internal("SoundsOGG/robotHit.ogg"));

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

    if(physicsComponent.colliding.getComponent(PlayerComponent.class) != null) {
      playerHitSound.play(0.4f);
    } else if(physicsComponent.colliding.getComponent(EnemyComponent.class) != null) {
      if(bulletComponent.owner.getComponent(PlayerComponent.class) != null) {
        robotHit.play(0.4f);
      }
    }

    HealthComponent enemyHealth = physicsComponent.colliding.getComponent(HealthComponent.class);
    if (enemyHealth != null && bulletComponent.owner.getComponent(ShootingComponent.class) != null){
      float damage = bulletComponent.owner.getComponent(ShootingComponent.class).bulletDamage;
      if (bulletComponent.owner.getComponent(PlayerComponent.class) == null || physicsComponent.colliding.getComponent(PlayerComponent.class) == null) {
        enemyHealth.current -= damage;
      }
    }

    bullet.add(getEngine().createComponent(ToRemoveComponent.class));
  }
}
