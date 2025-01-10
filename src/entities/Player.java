package entities;

import main.Settings;

import java.awt.*;


public class Player extends Entity {
    private int jumpSpeed = 25;
    private int gravity = 1;
    private int velocityY = 0;
    public boolean onGround = true;
    private int maxFallSpeed = 3;
    private boolean isAlive = true;

    private int health = 5;


    public Player(int x, int y) {
        this.defaultYPosition = y;
        this.x = x;
        this.y = y;

        this.collisionWidth = Settings.TILE_SIZE / 2;
        this.collisionHeight = Settings.TILE_SIZE / 2;

        this.collisionX = this.x + (Settings.TILE_SIZE / 2) / 2;
        this.collisionY = this.y + Settings.TILE_SIZE / 2;
    }

    public void update() {
        this.checkMove();
    }

    private void checkMove() {
        if (this.jumping && this.onGround) {
            this.velocityY = -jumpSpeed;
            this.onGround = false;
        }

        if (!this.onGround) {
            this.velocityY += gravity;

            if (this.velocityY > maxFallSpeed) {
                this.velocityY = maxFallSpeed;
            }
            this.y += velocityY;

            if (this.y >= this.defaultYPosition) {
                this.y = this.defaultYPosition;
                this.velocityY = 0;
                this.onGround = true;
                this.jumping = false;
            }

            this.collisionY = this.y + Settings.TILE_SIZE / 2;
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(this.x, this.y, Settings.TILE_SIZE, Settings.TILE_SIZE);

        g.setColor(Color.BLACK);
        g.drawRect(this.collisionX, this.collisionY, this.collisionWidth, this.collisionHeight);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth() {
        this.health -= 1;
    }
}
