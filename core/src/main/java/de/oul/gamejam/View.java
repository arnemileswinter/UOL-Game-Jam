package de.oul.gamejam;

public enum View {
    Up(90),
    Left(180),
    Right(0),
    Down(270);

    public final int angle;

    View(int angle) {
        this.angle = angle;
    }
}
