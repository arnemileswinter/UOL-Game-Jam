package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.View;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.VelocityComponent;
import de.oul.gamejam.component.ViewComponent;

import java.util.Random;

public class MoveEnemySystem extends IteratingSystem {
    ComponentMapper<EnemyComponent> em = ComponentMapper.getFor(EnemyComponent.class);
    ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    Random random = new Random();


    public MoveEnemySystem() {
        super(Family.all(EnemyComponent.class, VelocityComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        float lastCheck = entity.getComponent(EnemyComponent.class).lastCheck;
        float checkTime =entity.getComponent(EnemyComponent.class).directionCheck;
        if(lastCheck>checkTime){
            changeDirection(entity);
            em.get(entity).lastCheck=0;
        }else {
            em.get(entity).lastCheck+=v;
        }
        Vector2 position = pm.get(entity).vector;
        Vector2 velocity = vm.get(entity).vector;
        float speed = vm.get(entity).speed;
        position.x += velocity.x*speed;
        position.y += velocity.y*speed;
    }

    private void changeDirection(Entity entity){
        int randomInt = random.nextInt(4);
        Vector2 velocity = vm.get(entity).vector;
        if (randomInt ==0) {
            entity.getComponent(ViewComponent.class).view = View.Up;
            velocity.y = 1;
            velocity.x = 0;
        }else if(randomInt==1) {
            entity.getComponent(ViewComponent.class).view = View.Left;
            velocity.y = 0;
            velocity.x = -1;
        }else if(randomInt==2) {
            entity.getComponent(ViewComponent.class).view = View.Right;
            velocity.y = 0;
            velocity.x = 1;
        }else {
            entity.getComponent(ViewComponent.class).view = View.Down;
            velocity.y = -1;
            velocity.x = 0;
        }
    }
}
