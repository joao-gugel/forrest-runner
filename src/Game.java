public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;

    public Game() {
        this.gamePanel = new GamePanel();
        this.window = new Window(this.gamePanel);
    }

    @Override
    public void run() {

    }
}
