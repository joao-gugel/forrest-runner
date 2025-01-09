package entities;

import main.Settings;

import java.awt.*;

public class Player extends Entity {
    private int jumpSpeed = 25;
    private int gravity = 1;
    private int velocityY = 0;
    public boolean onGround = true;
    private int maxFallSpeed = 5;


    public Player(int x, int y) {
        this.defaultYPosition = y;
        this.x = x;
        this.y = y;
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
        }

    }


    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(this.x, this.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
    }
}
