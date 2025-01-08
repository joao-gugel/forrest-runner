public class Game implements Runnable {
    private Window window;
    private Thread gameThread;
    private GamePanel gamePanel;

    public Game() {
        this.gamePanel = new GamePanel();
        this.window = new Window(this.gamePanel);

        this.startGame();
    }

    private void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        this.gamePanel.repaint();
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
        double deltaFrames = 0;

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
