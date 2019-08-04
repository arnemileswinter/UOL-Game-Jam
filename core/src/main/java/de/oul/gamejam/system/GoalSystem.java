package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import de.oul.gamejam.FirstScreen;
import de.oul.gamejam.component.GoalComponent;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PlayerComponent;

public class GoalSystem extends IteratingSystem {
  private final ComponentMapper<PhysicsComponent> physicsM = ComponentMapper.getFor(PhysicsComponent.class);
  private final ComponentMapper<PlayerComponent> playerM = ComponentMapper.getFor(PlayerComponent.class);
  private final Game game;

  public GoalSystem(Game game){
    super(Family.all(GoalComponent.class, PhysicsComponent.class).get());
    this.game = game;
  }

  @Override
  protected void processEntity(Entity entity, float v){
    PhysicsComponent physics = physicsM.get(entity);
    if(physics.colliding == null) return;
    if(playerM.has(physics.colliding)) {
      // We have a collision with the goal and the player
      game.setScreen(new FirstScreen(game));
    }
  }
}
