package io;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInput implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            if (!this.gamePanel.game.player.jumping && this.gamePanel.game.player.onGround)
                this.gamePanel.game.player.jumping = true;
        }

        if (keyCode == KeyEvent.VK_DOWN) this.gamePanel.game.player.crouching = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_DOWN) this.gamePanel.game.player.crouching = false;
    }
}
