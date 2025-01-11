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
    private ArrayList<Tile> mounts;

    private float treeScrollX = 0;
    private float mountScrollX = 0;

    public WorldBackground(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.trees = new ArrayList<>();
        this.mounts = new ArrayList<>();

        this.initializeTrees();
        this.initializeMounts();
    }


    private void initializeTrees() {
        BufferedImage treeImage = Utils.loadImageAsset("/images/decoration-tree.png");

        for (int i = 0; i < Settings.SCREEN_COLUMNS + 1; i++) {
            int xPosition = i * Settings.TILE_SIZE;
            trees.add(new Tile(xPosition, treeImage));

        }
    }

    private void initializeMounts() {
        BufferedImage mountImage = Utils.loadImageAsset("/images/mount1.png");

        for (int i = 0; i < Settings.SCREEN_COLUMNS / 2 + 1; i++) {
            int xPosition = i * Settings.TILE_SIZE * 12;
            mounts.add(new Tile(xPosition, mountImage));
        }
    }

    public void update() {

        this.updateTrees();
        this.updateMounts();
    }

    private void updateTrees() {
        treeScrollX -= Settings.WORLD_SCROLL_SPEED - 2 + this.gamePanel.velocityAdded;

        for (Tile tree : trees) {
            // Verifica se o tile saiu da tela, se sim, muda a posição para o final
            if (tree.xPosition + treeScrollX + Settings.TILE_SIZE < 0) {
                tree.xPosition += trees.size() * Settings.TILE_SIZE;
            }
        }
    }

    private void updateMounts() {
        mountScrollX -= Settings.WORLD_SCROLL_SPEED - 3 + this.gamePanel.velocityAdded;

        for (Tile mount : mounts) {
            if (mount.xPosition + mountScrollX + Settings.TILE_SIZE * 12 < 0) {
                mount.xPosition += mounts.size() * Settings.TILE_SIZE * 12;
            }
        }
    }

    public void draw(Graphics g) {
        for (Tile mount : mounts) {
            int drawX = (int) (mount.xPosition + mountScrollX);
            int drawY = Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 7;

            g.drawImage(
                    mount.image,
                    drawX,
                    drawY,
                    Settings.TILE_SIZE * 12,
                    Settings.TILE_SIZE * 6,
                    null
            );
        }

        for (Tile tree : trees) {
            int drawX = (int) (tree.xPosition + treeScrollX);
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
