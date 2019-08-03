package de.oul.gamejam;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.PhysicsComponent;

/**
 * This serves as a bridge to the ECS and the box2d physics world.
 * Used to resolve collisions into components.
 */
public class EntityCollisionListener implements ContactListener {
  @Override
  public void beginContact(Contact contact) {
    // get fixtures involved in the collision.
    Fixture fa = contact.getFixtureA();
    Fixture fb = contact.getFixtureB();

    // check if either fixture has an entity object stored
    if (fa.getUserData() instanceof Entity) {
      Entity ent = (Entity) fa.getUserData();
      alignCollisionComponents(ent, fb);
    } else if (fb.getUserData() instanceof Entity) {
      Entity ent = (Entity) fb.getUserData();
      alignCollisionComponents(ent, fa);
    }

    // check if either fixture has an Entity object stored in the body's userData
    if (fa.getBody()
          .getUserData() instanceof Entity) {
      Entity ent = (Entity) fa.getBody()
                              .getUserData();
      alignCollisionComponents(ent, fb);
    } else if (fb.getBody()
                 .getUserData() instanceof Entity) {
      Entity ent = (Entity) fb.getBody()
                              .getUserData();
      alignCollisionComponents(ent, fa);
    }
  }

  /**
   * Aligns the collision components that are involved in a collision.
   * @param ent The entity that is colliding.
   * @param fb The fixture that the entity is colliding with.
   */
  private void alignCollisionComponents(Entity ent, Fixture fb) {
    // check the collided Entity is also an Entity
    if (fb.getBody()
          .getUserData() instanceof Entity) {
      Entity colEnt = (Entity) fb.getBody()
                                 .getUserData();
      // get the components for this entity
      PhysicsComponent physics  = ent.getComponent(PhysicsComponent.class);
      PhysicsComponent physicsB = colEnt.getComponent(PhysicsComponent.class);

      // set the CollisionEntity of the component
      if (physics != null) {
        physics.colliding = colEnt;
      }
      if (physicsB != null) {
        physicsB.colliding = ent;
      }
    }
  }

  @Override
  public void endContact(Contact contact) {
    // No implementation needed yet.
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
    // No implementation needed yet.
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    // No implementation needed yet.
  }
}
