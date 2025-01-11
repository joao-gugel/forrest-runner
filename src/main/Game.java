package main;

import entities.Player;
import levels.Enemies;
import levels.World;
import ui.LevelInterface;

import java.awt.*;

public class Game implements Runnable {
    public Player player;
    private Window window;
    private World world;
    private Enemies enemies;
    private Thread gameThread;
    private GamePanel gamePanel;
    private LevelInterface levelInterface;

    private double deltaFrames = 0;

    public Game() {
        this.gamePanel = new GamePanel(this);

        this.initDependencies();

        this.window = new Window(this.gamePanel);

        this.startGame();
    }

    private void initDependencies() {
        this.world = new World(this.gamePanel);
        this.player = new Player(Settings.TILE_SIZE, (Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2));
        this.enemies = new Enemies(this.gamePanel, 20, player);
        this.levelInterface = new LevelInterface(this.gamePanel);
    }

    private void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        this.world.update();
        this.player.update();
        this.enemies.update();
        this.gamePanel.update();
        this.levelInterface.update();
    }

    public void render(Graphics g) {
        this.world.draw(g);
        this.player.draw((Graphics2D) g);
        this.enemies.draw(g);
        this.levelInterface.draw((Graphics2D) g);
    }

    @Override
    public void run() {
        double timePerUpdate = 1_000_000_000.0 / Settings.UPS_SET;
        double timePerFrame = 1_000_000_000.0 / Settings.FPS_SET;

        long prevTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;

        while (this.gamePanel.game.player.isAlive()) {
            long currTime = System.nanoTime();
            double elapsedTime = currTime - prevTime;
            prevTime = currTime;

            deltaU += elapsedTime / timePerUpdate;
            deltaF += elapsedTime / timePerFrame;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }

            // Controle de taxa de frames
            try {
                Thread.sleep(1); // Dá tempo ao processador para outras tarefas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}