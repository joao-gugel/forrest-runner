package levels;

import entities.Enemy;
import entities.Player;
import main.GamePanel;
import main.Settings;
import main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemies {
    private GamePanel gamePanel;
    private int enemiesQty;
    private ArrayList<Enemy> enemies;
    private Player player;

    private Random random = new Random();

    public Enemies(GamePanel gamePanel, int enemiesQty, Player player) {
        this.gamePanel = gamePanel;
        this.enemiesQty = enemiesQty;
        this.player = player;

        this.enemies = new ArrayList<>();

        generateEnemies();
    }

    private BufferedImage getRandomEnemyType() {
        BufferedImage rockOneImage = Utils.loadImageAsset("/images/rock1.png");
        BufferedImage rockTwoImage = Utils.loadImageAsset("/images/rock2.png");
        BufferedImage bushOneImage = Utils.loadImageAsset("/images/bush1.png");
        BufferedImage bushTwoImage = Utils.loadImageAsset("/images/bush2.png");
        BufferedImage treeOneImage = Utils.loadImageAsset("/images/tree1.png");

        BufferedImage[] enemies = {rockOneImage, rockTwoImage, bushOneImage, bushTwoImage, treeOneImage};

        int randomEnemy = (int) (Math.random() * enemies.length);

        return enemies[randomEnemy];
    }

    private void generateEnemies() {
        enemies.clear();
        
        for (int i = 0; i < enemiesQty; i++) {
            BufferedImage enemyTypeImg = getRandomEnemyType();

            int xPosition = i * 500;
            System.out.println(xPosition);

            // Above the ground.
            int yPosition = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2;

            enemies.add(new Enemy(xPosition, yPosition, enemyTypeImg));
        }
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.x -= Settings.WORLD_SCROLL_SPEED + this.gamePanel.velocityAdded;
            ;
            enemy.collisionX = enemy.x;

            if (enemy.x + Settings.TILE_SIZE < 0) {
                enemy.x = getMaxEnemyX() + (400 + (int) (Math.random() * 200));
                enemy.hasCollided = false;
            }

            if (this.isColliding(player, enemy) && !enemy.hasCollided) {
                this.gamePanel.game.player.decreaseHealth();
                enemy.hasCollided = true;
                if (this.gamePanel.game.player.getHealth() == 0) {
                    this.gamePanel.game.player.setAlive(false);
                    System.out.println("GAME OVER!");
                }
            }

            enemy.collisionX = enemy.x;
        }
    }

    private int getMaxEnemyX() {
        int maxX = 0;
        for (Enemy enemy : enemies) {
            if (enemy.x > maxX) {
                maxX = enemy.x;
            }
        }
        return maxX;
    }

    private boolean isColliding(Player player, Enemy enemy) {
        // Cria dois retângulos, com coordenadas e tamanhos
        Rectangle playerRect = new Rectangle(player.collisionX, player.collisionY, player.collisionWidth, player.collisionHeight);
        Rectangle enemyRect = new Rectangle(enemy.collisionX, enemy.collisionY, enemy.collisionWidth, enemy.collisionHeight);
        // Ve se um retângulo está dentro do outro.
        return playerRect.intersects(enemyRect);
    }

    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            g.drawImage(enemy.image, enemy.x, enemy.y, Settings.TILE_SIZE, Settings.TILE_SIZE, null);
        }
    }

    public void reset() {
        this.generateEnemies();
    }
}
