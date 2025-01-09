package entities;

import main.Settings;

import java.awt.*;

public class Player extends Entity {
    private int jumpSpeed = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.checkMove();
    }

    private void checkMove() {
        if (this.jumping) this.y -= jumpSpeed;
    }


    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(this.x, this.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
    }
}
