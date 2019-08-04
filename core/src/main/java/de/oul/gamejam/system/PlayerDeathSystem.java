package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import de.oul.gamejam.DeathScreen;
import de.oul.gamejam.Scoreboard;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.ToRemoveComponent;

public class PlayerDeathSystem extends IteratingSystem {

  private final Game       game;
  private final Scoreboard scoreboard;

  public PlayerDeathSystem(Game game, Scoreboard scoreboard){
    super(Family.all(PlayerComponent.class, HealthComponent.class).exclude(ToRemoveComponent.class).get());
    this.game = game;
    this.scoreboard = scoreboard;
  }

  @Override
  protected void processEntity(Entity entity, float v){
    HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
    if (healthComponent.current < 1f) {
      if (getEntities().size() == 1) {
        // game over.
        game.setScreen(new DeathScreen(game, scoreboard));
      }
      entity.add(getEngine().createComponent(ToRemoveComponent.class));
    }
  }
}
