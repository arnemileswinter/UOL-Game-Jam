package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.component.HealthComponent;

public class EnemyDeathSystem extends IteratingSystem {
    public EnemyDeathSystem() {
        super(Family.all(EnemyComponent.class, HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        if(healthComponent.current<1f){
            getEngine().removeEntity(entity);
        }
    }
}
