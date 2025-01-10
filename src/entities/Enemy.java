package entities;

import main.Settings;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    public boolean alive = true;
    public boolean hasCollided = false;
    public BufferedImage image;

    public Enemy(int x, int y, BufferedImage img) {
        this.defaultYPosition = y;
        this.x = x;
        this.y = y;
        this.image = img;

        this.collisionWidth = (Settings.TILE_SIZE - img.getWidth());
        this.collisionHeight = (Settings.TILE_SIZE - img.getHeight() - 10);

        this.collisionX = this.x + (Settings.TILE_SIZE / 2) / 2;
        this.collisionY = this.y + Settings.TILE_SIZE / 2;
    }
}
