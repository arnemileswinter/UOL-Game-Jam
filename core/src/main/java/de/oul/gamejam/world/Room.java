package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;
import de.oul.gamejam.entity.MapTitleFactory;

public abstract class Room {
    protected PooledEngine pooledEngine;
    private int roomPositionX;
    private int roomPositionY;
    private int roomSize = 10;
    protected boolean topDoor;
    protected boolean bottomDoor;
    protected boolean leftDoor;
    protected boolean rightDoor;

    protected boolean[][] room = new boolean[10][10];



    protected boolean[] wallHorizontal = {true,true,true,true,true,true,true,true,true,true};
    protected boolean[] wallVertical = {true,false,false,false,false,false,false,false,false,true};
    protected boolean[] doorVertical = {false,false,false,false,false,false,false,false,false,false};
    protected boolean[] doorHorizontal = {true,true,true,true,false,false,true,true,true,true};

    protected boolean[] floorVertical = {false,false,false,true,false,false,true,false,false,false};
    protected boolean[] floorRightHorizontal = {false,false,false,true,true,true,true,true,true,true};
    protected boolean[] nothingVertical = {false,false,false,false,false,false,false,false,false,false};
    protected boolean[] floorRight = {false, false,false,true, false,false,false,false,false,false};
    protected boolean[] floorRightVerticalBottom = {false,false,false,true,false,false,true,true,true,true};

    public Room(PooledEngine pooledEngine, int x, int y){
        this.pooledEngine = pooledEngine;
        this.roomPositionX = x;
        this.roomPositionY = y;
    }

    public void createRoom(boolean[][]room){
        MapTitleFactory mapTitleFactory = new MapTitleFactory(pooledEngine);
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                if(room[j][i]){
                    mapTitleFactory.createWand(i + roomPositionX*roomSize,-j + (-1*roomPositionY+9)*roomSize);
                }
            }
        }
    }
}
