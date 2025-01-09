package levels;

import main.GamePanel;
import main.Settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class World {
    private GamePanel gamePanel;
    private ArrayList<Tile> groundTiles;

    private final float SCROLL_SPEED = 2f;
    private float scrollX = 0;

    private Random random = new Random();

    public World(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.groundTiles = new ArrayList<>();

        this.generateGround();
    }

    private Color getRandomTileColor() {
        Color desert = new Color(240, 214, 170);
        Color grass = new Color(85, 107, 76);

        Color[] grounds = {desert, grass};

        int randomGround = (int) (Math.random() * grounds.length);

        return grounds[randomGround];
    }

    private int getRandomGroundLength() {
        int[] groundLengths = {2, 1, 3};
        int randomLength = (int) (Math.random() * groundLengths.length);
        return groundLengths[randomLength];
    }

    private void generateGround() {
        int tilesNeeded = Settings.SCREEN_COLUMNS * 4;

        Color atualGroundColor = this.getRandomTileColor();
        int atualGroundLength = this.getRandomGroundLength();

        int groundLengthCounter = 0;

        for (int i = 0; i < tilesNeeded; i++) {
            if (groundLengthCounter == atualGroundLength) {
                atualGroundColor = this.getRandomTileColor();
                groundLengthCounter = 0;
            }
            groundLengthCounter++;
            groundTiles.add(new Tile(i * Settings.TILE_SIZE, atualGroundColor));
        }
    }

    public void update() {
        scrollX -= SCROLL_SPEED;

        for (Tile tile : groundTiles) {
            // Verifica se o tile saiu da tela, se sim, muda a posição para o final
            if (tile.xPosition + scrollX + Settings.TILE_SIZE < 0) {
                tile.xPosition += groundTiles.size() * Settings.TILE_SIZE;
            }
        }
    }

    public void draw(Graphics g) {
        // Drawing a blue sky
        for (int i = 0; i < Settings.SCREEN_COLUMNS; i++) {
            for (int j = 0; j < Settings.SCREEN_ROWS - 1; j++) {
                g.setColor(Color.BLUE);
                g.fillRect(
                        i * Settings.TILE_SIZE,
                        j * Settings.TILE_SIZE,
                        Settings.TILE_SIZE,
                        Settings.TILE_SIZE
                );
            }
        }

        // Drawing the ground
        for (Tile tile : groundTiles) {
            int drawX = (int) (tile.xPosition + scrollX);
            int drawY = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE;

            g.setColor(tile.color);
            g.fillRect(
                    drawX,
                    drawY,
                    Settings.TILE_SIZE,
                    Settings.TILE_SIZE
            );
        }
    }
}
