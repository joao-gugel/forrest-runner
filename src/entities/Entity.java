package entities;

public abstract class Entity {
    public int x, y;
    public boolean jumping, crouching;
    protected int defaultYPosition;
    
    public int collisionWidth, collisionHeight;
    public int collisionX, collisionY;
}
