package levels;

import entities.Enemy;
import entities.Player;
import main.GamePanel;
import main.Settings;

import java.awt.*;
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

    private void generateEnemies() {
        for (int i = 0; i < enemiesQty; i++) {
            int xPosition = i * 300;
            System.out.println(xPosition);
            // Above the ground.
            int yPosition = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2;

            enemies.add(new Enemy(xPosition, yPosition));
        }
    }

    public void update() {
        int enemiesCounter = 0;
        for (Enemy enemy : enemies) {
            enemiesCounter++;
            enemy.x -= SCROLL_SPEED;

            if (enemiesCounter == enemiesQty) enemy.x += 300;

            if (enemy.x + Settings.TILE_SIZE < 0) {
                enemy.x += Settings.SCREEN_COLUMNS * Settings.TILE_SIZE + random.nextInt(300);
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
        }
    }

    private boolean isColliding(Player player, Enemy enemy) {
        // Cria dois retângulos, com coordenadas e tamanhos
        Rectangle playerRect = new Rectangle(player.collisionX, player.collisionY, player.collisionWidth, player.collisionHeight);
        Rectangle enemyRect = new Rectangle(enemy.x, enemy.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
        // Ve se um retângulo está dentro do outro.
        return playerRect.intersects(enemyRect);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);

        for (Enemy enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
        }
    }
}
