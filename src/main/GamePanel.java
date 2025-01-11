package main;

import io.KeyboardInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private KeyboardInput keyboardInput;
    public Game game;
    public int secondsTimeCounter;
    public int updatesNum = 0;
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
        if (secondsTimeCounter == 30) velocityAdded = 3;
        if (secondsTimeCounter == 60) velocityAdded = 4;
        if (secondsTimeCounter == 80) velocityAdded = 5;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.render(g);
    }

    public void reset() {
        this.updatesNum = 0;
        this.secondsTimeCounter = 0;
        this.velocityAdded = 0;
    }
}
