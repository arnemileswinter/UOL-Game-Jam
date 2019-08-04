package de.oul.gamejam.EntityListener;

import com.badlogic.ashley.core.*;
import de.oul.gamejam.component.PlayerComponent;

public class PlayerListener extends EntitySystem implements EntityListener {

    PlayerBuffer playerBuffer;

    public PlayerListener (PlayerBuffer playerBuffer){
        this.playerBuffer = playerBuffer;
    }

    @Override
    public void addedToEngine(Engine engine){
        super.addedToEngine(engine);
        engine.addEntityListener(Family.all(PlayerComponent.class).get(),this);
    }

    @Override
    public void entityAdded(Entity entity) {
            playerBuffer.addEntity(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        if(entity.getComponent(PlayerComponent.class) != null){
            playerBuffer.removeEntity(entity);
        }
    }
}
