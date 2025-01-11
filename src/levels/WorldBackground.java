package levels;

import entities.Enemy;
import main.GamePanel;
import main.Settings;
import main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WorldBackground {
    private GamePanel gamePanel;
    private ArrayList<Tile> trees;

    private float scrollX = 0;

    public WorldBackground(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.trees = new ArrayList<>();

        this.initializeTrees();
    }


    private void initializeTrees() {
        BufferedImage treeImage = Utils.loadImageAsset("/images/decoration-tree.png");

        for (int i = 0; i < Settings.SCREEN_COLUMNS + 1; i++) {
            int xPosition = i * Settings.TILE_SIZE;
            trees.add(new Tile(xPosition, treeImage));

        }
    }

    public void update() {
        scrollX -= Settings.WORLD_SCROLL_SPEED - 2 + this.gamePanel.velocityAdded;

        for (Tile tree : trees) {
            // Verifica se o tile saiu da tela, se sim, muda a posição para o final
            if (tree.xPosition + scrollX + Settings.TILE_SIZE < 0) {
                tree.xPosition += trees.size() * Settings.TILE_SIZE;
            }
        }
    }

    public void draw(Graphics g) {
        for (Tile tree : trees) {
            int drawX = (int) (tree.xPosition + scrollX);
            int drawY = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 3;

            g.drawImage(
                    tree.image,
                    drawX,
                    drawY,
                    Settings.TILE_SIZE,
                    Settings.TILE_SIZE * 2,
                    null
            );
        }

    }
}
