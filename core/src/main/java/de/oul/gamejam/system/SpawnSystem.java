package de.oul.gamejam.system;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.EngineFactory;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.entity.EnemyFactory;

import java.util.Random;

public class SpawnSystem extends IteratingSystem {

    private final PooledEngine pooledEngine;

    private final World world;

    Random random = new Random();

    public SpawnSystem(PooledEngine pooledEngine, World world){
        super(Family.all(EnemyComponent.class).get());
        this.pooledEngine = pooledEngine;
        this.world = world;
        placeEnemysInWorld();
    }

    public void placeEnemysInWorld(){
        int enemyCounter = 0;
        if(enemyCounter < 20){
            int xCoordinate = random.nextInt(100);
            int yCoordinate = random.nextInt(100);
            Entity enemy = new EnemyFactory(pooledEngine, world).createEnemy(xCoordinate,yCoordinate);
            enemyCounter ++;
            System.out.println(xCoordinate);
            System.out.println(yCoordinate);
        }

    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }
}
