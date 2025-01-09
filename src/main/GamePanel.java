package main;

import io.KeyboardInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private KeyboardInput keyboardInput;
    public Game game;

    public GamePanel(Game game) {
        this.game = game;
        this.keyboardInput = new KeyboardInput(this);

        this.addKeyListener(keyboardInput);
        this.setFocusable(true);

        Dimension size = new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.setPreferredSize(size);
    }


    public void update() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.render(g);
    }
}
