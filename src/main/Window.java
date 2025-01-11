package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(GamePanel gamePanel) {
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setTitle("Forrest Runner");
        this.setResizable(false);
        this.setVisible(true);
        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
