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

    private final float SCROLL_SPEED = 2f;
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

        BufferedImage[] enemies = {rockOneImage, rockTwoImage};

        int randomEnemy = (int) (Math.random() * enemies.length);

        return enemies[randomEnemy];
    }

    private void generateEnemies() {
        for (int i = 0; i < enemiesQty; i++) {
            BufferedImage enemyTypeImg = getRandomEnemyType();

            int xPosition = i * 500;

            // Above the ground.
            int yPosition = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2;

            enemies.add(new Enemy(xPosition, yPosition, enemyTypeImg));
        }
    }

    public void update() {
        int enemiesCounter = 0;
        for (Enemy enemy : enemies) {
            enemiesCounter++;
            enemy.x -= SCROLL_SPEED;

            if (enemiesCounter == enemiesQty) enemy.x += 1200;

            enemy.collisionX = enemy.x;

            if (enemy.x + Settings.TILE_SIZE < 0) {
                enemy.x += Settings.SCREEN_WIDTH + 500;
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
}
