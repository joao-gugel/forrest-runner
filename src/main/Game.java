package main;

import entities.Player;
import levels.Enemies;
import levels.World;
import levels.WorldBackground;
import ui.LevelInterface;
import ui.StartGameInterface;

import java.awt.*;

public class Game implements Runnable {
    public Player player;
    private Window window;
    private World world;
    private WorldBackground worldBackground;
    private Enemies enemies;
    private Thread gameThread;
    private GamePanel gamePanel;
    private LevelInterface levelInterface;
    private StartGameInterface startGameInterface;

    public Sound jumpSound;
    public Sound hitSound;

    public boolean firstTimePlayed = true;

    public Game() {
        this.gamePanel = new GamePanel(this);

        this.initDependencies();
        this.initSounds();

        this.window = new Window(this.gamePanel);

        this.startGame();
    }

    private void initDependencies() {
        this.world = new World(this.gamePanel);
        this.worldBackground = new WorldBackground(this.gamePanel);
        this.player = new Player(Settings.TILE_SIZE, (Settings.SCREEN_HEIGHT - Settings.TILE_SIZE * 2), this.gamePanel);
        this.enemies = new Enemies(this.gamePanel, 30, player);
        this.levelInterface = new LevelInterface(this.gamePanel);
        this.startGameInterface = new StartGameInterface(this.gamePanel);
    }

    private void initSounds() {
        this.hitSound = new Sound();
        this.hitSound.setFile(0);

        this.jumpSound = new Sound();
        this.jumpSound.setFile(1);
    }

    public void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        this.world.update();
        this.worldBackground.update();
        this.player.update();
        this.enemies.update();
        this.gamePanel.update();
        this.levelInterface.update();
    }

    public void render(Graphics g) {
        this.world.draw(g);
        this.worldBackground.draw(g);
        this.player.draw((Graphics2D) g);
        this.enemies.draw(g);
        this.levelInterface.draw((Graphics2D) g);

        if (!this.player.isAlive()) this.startGameInterface.draw((Graphics2D) g);
    }

    public void reset() {
        this.world.reset();
        this.player.reset();
        this.enemies.reset();
        this.gamePanel.reset();
        this.worldBackground.reset();
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