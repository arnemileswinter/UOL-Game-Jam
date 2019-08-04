package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.EntityListener.PlayerBuffer;
import de.oul.gamejam.component.FocusedComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PositionComponent;

/**
 * Focuses a camera on the player.
 */
public class CameraFocusPlayerSystem extends IteratingSystem {
  private final Camera  camera;
  private       boolean lastPlayerFound;
  private       Entity  lastPlayer;
  private       Entity  focusedPlayer;
  private PlayerBuffer playerBuffer;
  private       Family  focusedEntities = Family.all(FocusedComponent.class).get();
  private Entity nextPlayer;
  private Entity lastFocusedPlayer;

  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public CameraFocusPlayerSystem(Camera camera, PlayerBuffer playerBuffer){
    super(Family.all(PlayerComponent.class, PositionComponent.class).get());
    this.camera = camera;
    this.playerBuffer = playerBuffer;
  }

  @Override
  public void update(float deltaTime){
    super.update(deltaTime);
    unmarkOtherPlayersAsFocused();
    focusedPlayer.add(getEngine().createComponent(FocusedComponent.class));
    if(focusedPlayer== null ||focusedPlayer.getComponent(PositionComponent.class)==null ){
      changePlayer(focusedPlayer);
      return;
    }
    Vector2 playerPosition = focusedPlayer.getComponent(PositionComponent.class).vector;
    camera.position.set(playerPosition.x, playerPosition.y, 0);
  }

  private void unmarkOtherPlayersAsFocused(){
    for (Entity e : getEngine().getEntitiesFor(focusedEntities)) {
      e.remove(FocusedComponent.class);
    }
  }

  /**
   * This method is called on every entity on every update call of the EntitySystem. Override this to implement your
   * system's specific processing.
   *
   * @param entity    The current Entity being processed
   * @param deltaTime The delta time between the last and current frame
   */
  @Override
  protected void processEntity(Entity entity, float deltaTime){
    if (lastPlayer == null) {
      lastPlayer = entity;
    }
    if (lastPlayer.isScheduledForRemoval()) {
      lastPlayer = null;
    }
    if (lastPlayerFound) return;

    if(lastFocusedPlayer == entity) return;
    focusedPlayer = entity;
    if(nextPlayer == null) return;
    focusedPlayer = nextPlayer;
    nextPlayer = null;
    if (lastPlayer == entity) {
      lastPlayerFound = true;
    }
  }

  public void changePlayer(Entity entity) {
    nextPlayer = playerBuffer.getNextPayer();
    while(playerBuffer.list.size() > 1 && nextPlayer == entity){
      nextPlayer = playerBuffer.getNextPayer();
      lastPlayerFound = false;
    }


  }
}
