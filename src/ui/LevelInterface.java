package ui;

import main.GamePanel;
import main.Settings;
import main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelInterface {
    private BufferedImage heartImage;

    private GamePanel gamePanel;
    private Font mainFont;
    private Font secondaryFont;
    private Color mainColor;
    private Color secondaryColor;

    public LevelInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mainFont = new Font("Arial", Font.PLAIN, 24);
        this.secondaryFont = new Font("Arial", Font.BOLD, 16);
        this.mainColor = Color.WHITE;
        this.secondaryColor = new Color(80, 80, 80);

        this.loadImageAssets();
    }

    private void loadImageAssets() {
        this.heartImage = Utils.loadImageAsset("/images/heart.png");
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        g.setColor(this.mainColor);
        g.setFont(this.mainFont);

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        this.drawPlayerLives(g);
        this.drawPlayerDistance(g);
    }

    private void drawPlayerLives(Graphics2D g) {
        for (int i = 1; i <= this.gamePanel.game.player.getHealth(); i++) {
            g.drawImage(
                    this.heartImage,
                    i * 40,
                    30,
                    Settings.TILE_SIZE / 2,
                    Settings.TILE_SIZE / 2,
                    null
            );
        }
    }

    private void drawPlayerDistance(Graphics2D g) {
        g.setFont(this.secondaryFont);
        g.setColor(this.secondaryColor);
        g.drawString("Tempo: " + this.gamePanel.secondsTimeCounter + " seg", 40, 80);
    }
}
