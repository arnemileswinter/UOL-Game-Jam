package de.oul.gamejam.system;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.entity.EnemyFactory;
import de.oul.gamejam.world.LevelFactory;

import java.util.Random;

public class SpawnSystem extends IteratingSystem{

    private final PooledEngine pooledEngine;

    private final World world;
    private final LevelFactory levelFactory;
    private final EnemyFactory enemyFactory;

    Random random = new Random();

    public SpawnSystem(PooledEngine pooledEngine, World world, LevelFactory levelFactory){
        super(Family.all(EnemyComponent.class).get());
        this.pooledEngine = pooledEngine;
        this.world = world;
        this.levelFactory = levelFactory;
        this.enemyFactory = new EnemyFactory(pooledEngine, world);
        placeEnemysInWorld();
    }

    public void placeEnemysInWorld(){
        int enemyCounter = 0;
        while(enemyCounter <30){
            int xRoom = random.nextInt(10);
            int yRoom = random.nextInt(10);
            levelFactory.rooms[xRoom][yRoom].createEntity(enemyFactory,0,0);
            enemyCounter ++;
        }

    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }
}
