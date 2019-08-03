package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;
import de.oul.gamejam.entity.MapTileFactory;


public class BasicRoom extends Room {

  public BasicRoom(MapTileFactory mapTileFactory, int x, int y, boolean bottomDoor,
                   boolean leftDoor, boolean rightDoor, boolean topDoor){
    super(mapTileFactory, x, y);
    this.bottomDoor = bottomDoor;
    this.topDoor = topDoor;
    this.leftDoor = leftDoor;
    this.rightDoor = rightDoor;
    room[0] = wallHorizontal.clone();
    room[1] = wallVertical.clone();
    room[2] = wallVertical.clone();
    room[3] = wallVertical.clone();
    room[4] = wallVertical.clone();
    room[5] = wallVertical.clone();
    room[6] = wallVertical.clone();
    room[7] = wallVertical.clone();
    room[8] = wallVertical.clone();
    room[9] = wallHorizontal.clone();
    if (rightDoor) {
      room[4][9] = false;
      room[5][9] = false;
    }
    if (leftDoor) {
      room[4][0] = false;
      room[5][0] = false;
    }
    if (topDoor) {
      room[0][4] = false;
      room[0][5] = false;
    }
    if (bottomDoor) {
      room[9][4] = false;
      room[9][5] = false;
    }
    createRoom(room);
  }

  private void displayMatrix(int N, boolean[][] mat){
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++)
        System.out.print(" " + mat[i][j]);

      System.out.print("\n");
    }
    System.out.print("\n");
  }

}
