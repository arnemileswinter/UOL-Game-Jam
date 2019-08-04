package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;
import de.oul.gamejam.entity.EnemyFactory;
import de.oul.gamejam.entity.GoalFactory;
import de.oul.gamejam.entity.MapTileFactory;
import de.oul.gamejam.entity.SpawnInterface;

import java.util.Random;

public abstract class Room {
    private final MapTileFactory mapTileFactory;
    private int roomPositionX;
    private int roomPositionY;
    private int roomSize = 10;
    protected boolean topDoor;
    protected boolean bottomDoor;
    protected boolean leftDoor;
    protected boolean rightDoor;
    protected boolean goal = false;

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

    public Room(MapTileFactory mapTileFactory, int x, int y){
        this.roomPositionX = x;
        this.roomPositionY = y;
        this.mapTileFactory = mapTileFactory;
    }

    public void createRoom(boolean[][]room){
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                if(room[j][i]){
                    mapTileFactory.createWall(i + roomPositionX*roomSize, -j + (-1*roomPositionY+9)*roomSize);
                }
            }
        }
        if(goal){
            createEntity(new GoalFactory(mapTileFactory.engine,mapTileFactory.world),5 + roomPositionX*roomSize, -5 + (-1*roomPositionY+9)*roomSize);
        }
    }

    public void createEntity(SpawnInterface spawnInterface, int x, int y){
        Random random = new Random();
        if(spawnInterface instanceof GoalFactory) {
            spawnInterface.spawn(x, y);
            spawnInterface.spawn(5,4);
        }else if (spawnInterface instanceof EnemyFactory){
            while(room[x][y]){
                x = random.nextInt(9);
                y = random.nextInt(9);
            }
            spawnInterface.spawn(x+roomPositionX*roomSize,-y+(-1*roomPositionY+9)*roomSize);
        }
    }
}
