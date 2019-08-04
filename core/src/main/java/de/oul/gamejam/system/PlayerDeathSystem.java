package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.FocusedComponent;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.ToRemoveComponent;

public class PlayerDeathSystem extends IteratingSystem {

    public PlayerDeathSystem(){
        super(Family.all(PlayerComponent.class).get().exclude(ToRemoveComponent.class).get());

    }

    @Override
    protected void processEntity(Entity entity, float v){
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        if (healthComponent.current < 1f) {
            entity.add(getEngine().createComponent(ToRemoveComponent.class));
        }
    }
}
