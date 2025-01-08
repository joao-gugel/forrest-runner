import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(GamePanel gamePanel) {
        Dimension size = new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.add(gamePanel);

        this.setLocationRelativeTo(null);
        this.setTitle("Forrest Runner");
        this.setVisible(true);
        this.setSize(size);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
