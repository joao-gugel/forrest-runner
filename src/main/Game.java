package main;

import entities.Player;
import levels.Enemies;
import levels.World;

import java.awt.*;

public class Game implements Runnable {
    public Player player;
    private Window window;
    private World world;
    private Enemies enemies;
    private Thread gameThread;
    private GamePanel gamePanel;

    private double deltaFrames = 0;

    public Game() {
        this.gamePanel = new GamePanel(this);
        this.window = new Window(this.gamePanel);

        this.initDependencies();

        this.startGame();
    }

    private void initDependencies() {
        this.player = new Player(Settings.TILE_SIZE, (Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2));
        this.enemies = new Enemies(this.gamePanel, 10, player);
        this.world = new World(this.gamePanel);
    }

    private void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        this.player.update();
        this.world.update();
        this.enemies.update();
    }

    public void render(Graphics g) {
        this.world.draw(g);
        this.player.draw(g);
        this.enemies.draw(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / Settings.FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / Settings.UPS_SET;

        long prevTime = System.nanoTime();
        int FPS = 0;
        int UPDATES = 0;

        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;

        while (true) {
            long currTime = System.nanoTime();

            deltaU += (currTime - prevTime) / timePerUpdate;
            deltaFrames += (currTime - prevTime) / timePerFrame;
            prevTime = currTime;

            if (deltaU >= 1) {
                update();
                UPDATES++;
                deltaU--;
            }

            if (deltaFrames >= 1) {
                gamePanel.repaint();
                deltaFrames--;
                FPS++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + FPS + " | UPS: " + UPDATES);
                FPS = 0;
                UPDATES = 0;
            }
        }
    }
}