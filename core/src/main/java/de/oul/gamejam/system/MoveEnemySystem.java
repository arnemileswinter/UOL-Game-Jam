package de.oul.gamejam.system;

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
            entity.getComponent(EnemyComponent.class).lastCheck=0;
        }else {
            entity.getComponent(EnemyComponent.class).lastCheck+=v;
        }
        Vector2 position = entity.getComponent(PositionComponent.class).vector;
        Vector2 velocity = entity.getComponent(VelocityComponent.class).vector;
        float speed = entity.getComponent(VelocityComponent.class).speed;
        position.x += velocity.x*speed;
        position.y += velocity.y*speed;
    }

    private void changeDirection(Entity entity){
        int randomInt = random.nextInt(4);
        View view = entity.getComponent(ViewComponent.class).view;
        Vector2 velocity = entity.getComponent(VelocityComponent.class).vector;
        switch (randomInt){
            case 0:
                view = View.Up;
                velocity.y =1;
                velocity.x=0;
                break;
            case 1:
                view = View.Left;
                velocity.y =0;
                velocity.x=-1;
                break;
            case 2:
                view = View.Right;
                velocity.y =0;
                velocity.x=1;
                break;
            case 3:
                view = View.Down;
                velocity.y =-1;
                velocity.x=0;
                break;
        }
    }
}
