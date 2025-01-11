package entities;

import main.GamePanel;
import main.Settings;
import main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {
    private int jumpSpeed = 20;
    private int gravity = 1;
    private int velocityY = 0;
    public boolean onGround = true;
    public int maxFallSpeed = 3;
    private boolean isAlive = false;

    private int health = 3;

    private BufferedImage playerImage;
    private BufferedImage[] playerImages;
    private int imageIndex = 0;

    private GamePanel gamePanel;

    private int animationDelay = 30;
    private int animationCounter = 0;


    public Player(int x, int y, GamePanel gamePanel) {
        this.defaultYPosition = y;
        this.x = x;
        this.y = y;

        this.collisionWidth = Settings.TILE_SIZE / 2;
        this.collisionHeight = Settings.TILE_SIZE / 2;

        this.collisionX = this.x + (Settings.TILE_SIZE / 2) / 2;
        this.collisionY = this.y + Settings.TILE_SIZE / 2;

        this.playerImages = new BufferedImage[3];
        this.playerImages[0] = Utils.loadImageAsset("/images/forrest-run1.png");
        this.playerImages[1] = Utils.loadImageAsset("/images/forrest-run2.png");
        this.playerImages[2] = Utils.loadImageAsset("/images/forrest-jump1.png");
        this.playerImage = this.playerImages[this.imageIndex];

        this.gamePanel = gamePanel;
    }

    public void update() {
        this.updateRunningImage();
        this.checkMove();
    }

    private void updateRunningImage() {
        animationCounter++;

        if (this.gamePanel.secondsTimeCounter == 10) animationDelay = 25;
        if (this.gamePanel.secondsTimeCounter == 20) animationDelay = 20;
        if (this.gamePanel.secondsTimeCounter == 30) animationDelay = 15;
        if (this.gamePanel.secondsTimeCounter == 60) animationDelay = 10;
        if (this.gamePanel.secondsTimeCounter == 80) animationDelay = 5;

        if (animationCounter >= animationDelay && !this.jumping) {
            animationCounter = 0;

            if (this.imageIndex == 0) {
                this.imageIndex = 1;
            } else {
                this.imageIndex = 0;
            }

            this.playerImage = this.playerImages[this.imageIndex];
        }
    }


    private void checkMove() {
        if (this.jumping && this.onGround) {
            this.velocityY = -jumpSpeed;
            this.onGround = false;
            this.playerImage = this.playerImages[2];
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
        g.drawImage(this.playerImage, this.x, this.y, Settings.TILE_SIZE, Settings.TILE_SIZE, null);
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

    public void reset() {
        this.setAlive(true);
        this.health = 3;
        this.animationDelay = 30;
        this.animationCounter = 0;
    }
}
