package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;

import java.util.Random;

public class LevelFactory {
    PooledEngine engine;
    private Room[][] rooms = new Room[10][10];
    Random random = new Random();

    public LevelFactory(PooledEngine pooledEngine) {
        this.engine = pooledEngine;
    }

    public void createLevel() {
        for(int x = 0; x<10;x++) {
            for (int y = 9; y >=0; y--) {
                boolean bottomDoor = random.nextBoolean();
                boolean leftDoor= random.nextBoolean();
                boolean rightDoor = random.nextBoolean();
                boolean topDoor = random.nextBoolean();
                if(x==0){
                    leftDoor = false;
                }else {
                    leftDoor = rooms[y][x-1].rightDoor;
                }
                if(y==9){
                    rightDoor = false;
                }
                if(y==0){
                    topDoor = false;
                }
                if(y==9){
                    bottomDoor = false;
                }else{
                    bottomDoor = rooms[y+1][x].topDoor;
                }
                rooms[y][x]=new BasicRoom(engine,x,y,bottomDoor,leftDoor,rightDoor,topDoor);
            }
        }
    }
}
