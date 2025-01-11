package main;

import io.KeyboardInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private KeyboardInput keyboardInput;
    public Game game;
    public int secondsTimeCounter;
    private int updatesNum = 0;
    public int velocityAdded = 0;

    public GamePanel(Game game) {
        this.game = game;
        this.keyboardInput = new KeyboardInput(this);

        this.addKeyListener(keyboardInput);
        this.setFocusable(true);

        Dimension size = new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.setPreferredSize(size);
    }


    public void update() {
        if (updatesNum == 120) {
            this.secondsTimeCounter++;
            this.handleAddedVelocity();
            this.updatesNum = 0;
        } else
            updatesNum++;
    }

    private void handleAddedVelocity() {
        if (secondsTimeCounter == 10) velocityAdded = 1;
        if (secondsTimeCounter == 20) velocityAdded = 2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.render(g);
    }
}
