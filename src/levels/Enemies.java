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
            int xPosition = random.nextInt(Settings.SCREEN_WIDTH) + i * 300;
            // Above the ground.
            int yPosition = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2;

            enemies.add(new Enemy(xPosition, yPosition));
        }
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.x -= SCROLL_SPEED;

            if (enemy.x + Settings.TILE_SIZE < 0) {
                enemy.x += Settings.SCREEN_COLUMNS * Settings.TILE_SIZE + random.nextInt(300);
            }

            if (this.isColliding(player, enemy)) {
                System.out.println("Game over!");
            }
        }
    }

    private boolean isColliding(Player player, Enemy enemy) {
        return (player.x + Settings.TILE_SIZE > enemy.x &&
                player.x < enemy.x + Settings.TILE_SIZE) &&
                (player.y + Settings.TILE_SIZE > enemy.y &&
                        player.y < enemy.y + Settings.TILE_SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);

        for (Enemy enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
        }
    }
}
