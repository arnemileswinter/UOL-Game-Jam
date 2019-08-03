package de.oul.gamejam;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;
import de.oul.gamejam.component.BulletComponent;

/**
 * This serves as a bridge to the ECS and the box2d physics world. Used to resolve collisions into components.
 */
public class BulletCollisionFilter implements ContactFilter {
  @Override
  public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB){
    return !isBulletCollidingWithOwner(fixtureA, fixtureB) && !isBulletCollidingWithOwner(fixtureB,
                                                                                          fixtureA) && !isBulletCollidingWithBullet(
            fixtureA,
            fixtureB);
  }

  /**
   * @param fixA a bullet's fixture
   * @param fixB a possible owner's fixture
   *
   * @return whether the fixtures of a contact are a bullet colliding with its owner
   */
  private boolean isBulletCollidingWithOwner(Fixture fixA, Fixture fixB){
    if ((fixA.getBody().getUserData() instanceof Entity)) {
      BulletComponent bulletComponent = ((Entity) fixA.getBody().getUserData()).getComponent(BulletComponent.class);
      if (bulletComponent != null) return isBulletOwnedByFixtureEntity(bulletComponent, fixB);
    }
    return false;
  }

  /**
   * @param bulletComponent A bullet's bullet component.
   * @param collider        A fixture of an entity that might be the bullet's owner.
   *
   * @return whether the collider is the owner of the fixture.
   */
  private boolean isBulletOwnedByFixtureEntity(BulletComponent bulletComponent, Fixture collider){
    // if the required references are null, then it is not owned by the fixture's entity.
    if (collider.getBody().getUserData() == null) {
      return false;
    }
    if (bulletComponent.owner == null) return false;
    // if the bullet's owner is equal to the collider's entity (the user data) then it is owned by
    // it.
    return bulletComponent.owner == collider.getBody().getUserData();
    // else it is not.
  }

  /**
   * @param fixA a bullet's fixture
   * @param fixB a possible owner's fixture
   *
   * @return whether the fixtures of a contact are a bullet colliding with its owner
   */
  private boolean isBulletCollidingWithBullet(Fixture fixA, Fixture fixB){
    if ((fixA.getBody().getUserData() instanceof Entity)) {
      BulletComponent bulletComponent = ((Entity) fixA.getBody().getUserData()).getComponent(BulletComponent.class);
      if (bulletComponent != null) {
        BulletComponent bulletComponentB = ((Entity) fixB.getBody().getUserData()).getComponent(BulletComponent.class);
        if (bulletComponentB != null) {
          return true;
        }
      }
    }
    return false;
  }

}
