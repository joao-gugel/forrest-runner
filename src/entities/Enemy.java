package entities;

public class Enemy extends Entity {
    public boolean alive = true;

    public Enemy(int x, int y) {
        this.defaultYPosition = y;
        this.x = x;
        this.y = y;
    }
}
