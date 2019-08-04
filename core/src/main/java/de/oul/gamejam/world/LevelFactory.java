package de.oul.gamejam.world;

import de.oul.gamejam.entity.EnemyFactory;
import de.oul.gamejam.entity.MapTileFactory;

import java.util.Random;

public class LevelFactory {
  private final MapTileFactory mapTileFactory;
  Random random = new Random();
  public Room[][] rooms = new Room[10][10];

  public LevelFactory(MapTileFactory mapTileFactory){
    this.mapTileFactory = mapTileFactory;
  }


  public void createLevel(){
    int goalX = createRandomInt();
    int goalY = createRandomInt();
    for (int x = 0; x < 10; x++) {
      for (int y = 9; y >= 0; y--) {
        boolean bottomDoor = randomBoolean();
        boolean leftDoor   = randomBoolean();
        boolean rightDoor  = randomBoolean();
        boolean topDoor    = randomBoolean();

        if (x == 0) {
          leftDoor = false;
        } else {
          leftDoor = rooms[y][x - 1].rightDoor;
        }


        if (y == 9) {
          bottomDoor = false;
        } else {
          bottomDoor = rooms[y + 1][x].topDoor;
        }
        if (!bottomDoor && !leftDoor && !rightDoor && !topDoor) {
          topDoor = true;
          rightDoor = true;
        }
        if (y == 0) {
          topDoor = false;
        }
        if (x == 9) {
          rightDoor = false;
        }
        BasicRoom basicRoom = new BasicRoom(mapTileFactory, x, y, bottomDoor, leftDoor, rightDoor, topDoor);
        if(x==goalX && y == goalY){
          basicRoom.goal = true;
        }
        basicRoom.createRoom(basicRoom.room);
        rooms[y][x] = basicRoom;
      }
    }
  }

  private boolean randomBoolean(){
    int number = random.nextInt(10);
    return number < 7;
  }

  private int createRandomInt(){
    return random.nextInt(10);
  }
}
