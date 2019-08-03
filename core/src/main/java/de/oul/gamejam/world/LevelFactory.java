package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;

public class LevelFactory {
    PooledEngine engine;
    private Room[][] rooms = new Room[10][10];

    public LevelFactory(PooledEngine pooledEngine) {
        this.engine = pooledEngine;
    }

    public void createLevel() {
        for(int x = 0; x<10;x++) {
            for (int y = 9; y >=0; y--) {
                boolean bottomDoor = true;
                boolean leftDoor= true;
                boolean rightDoor = true;
                boolean topDoor = true;
                if(x==0){
                    leftDoor = false;
                }else if(y==9){
                    rightDoor = false;
                }
                if(y==0){
                    bottomDoor = false;
                }else if(y==9){
                    topDoor = false;
                }
                rooms[y][x]=new BasicRoom(engine,x,y,bottomDoor,leftDoor,rightDoor,topDoor);
            }
        }
    }
}
