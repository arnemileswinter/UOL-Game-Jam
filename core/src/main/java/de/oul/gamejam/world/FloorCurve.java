package de.oul.gamejam.world;

import com.badlogic.ashley.core.PooledEngine;

public class FloorCurve extends Room {
    public FloorCurve(PooledEngine pooledEngine, int x, int y, int rotate) {
        super(pooledEngine, x, y);
        topDoor = false;
        rightDoor = true;
        leftDoor = false;
        bottomDoor = true;
        room[0] = nothingVertical.clone();
        room[1] = nothingVertical.clone();
        room[2] = nothingVertical.clone();
        room[3] = floorRightHorizontal.clone();
        room[4] = floorRight.clone();
        room[5] = floorRight.clone();
        room[6] = floorRightVerticalBottom.clone();
        room[7] = floorVertical.clone();
        room[8] = floorVertical.clone();
        room[9] = floorVertical.clone();
        displayMatrix(10,room);
        for(int i =0;i<rotate;i++){
            rotateMatrix(10,room);
            displayMatrix(10,room);
        }
        createRoom(room);
    }


    private void rotateMatrix(int N, boolean a[][])
    {
        boolean tempBoolean = topDoor;
        topDoor = leftDoor;
        leftDoor = bottomDoor;
        bottomDoor=rightDoor;
        rightDoor=tempBoolean;
        for (int i = 0; i < N / 2; i++)
        {
            for (int j = i; j < N - i - 1; j++)
            {

                // Swap elements of each cycle
                // in clockwise direction
                boolean temp = a[i][j];
                a[i][j] = a[N - 1 - j][i];
                a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
                a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
                a[j][N - 1 - i] = temp;
            }
        }
    }
    private void displayMatrix(int N, boolean mat[][])
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(" " + mat[i][j]);

            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
