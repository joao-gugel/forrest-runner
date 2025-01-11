package ui;

import main.GamePanel;
import main.Settings;
import main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StartGameInterface {
    private GamePanel gamePanel;
    private BufferedImage startButtonImg;

    public StartGameInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.startButtonImg = Utils.loadImageAsset("/images/startbtn.png");
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        g.drawImage(
                startButtonImg,
                (Settings.SCREEN_WIDTH / 2) - (Settings.TILE_SIZE * 2),
                Settings.SCREEN_HEIGHT / 2 - (Settings.TILE_SIZE),
                Settings.TILE_SIZE * 4,
                Settings.TILE_SIZE * 2,
                null
        );

        g.setColor(new Color(220, 220, 220));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("PRESS SPACE TO START", Settings.SCREEN_WIDTH / 2 - 110, Settings.SCREEN_HEIGHT / 2 + Settings.TILE_SIZE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("ARROW UP TO JUMP", Settings.SCREEN_WIDTH / 2 - 70, (Settings.SCREEN_HEIGHT / 2 + Settings.TILE_SIZE) + 50);
        g.drawString("ARROW DOWN TO INCREASE FALL SPEED", Settings.SCREEN_WIDTH / 2 - 140, (Settings.SCREEN_HEIGHT / 2 + Settings.TILE_SIZE) + 70);
    }
}
