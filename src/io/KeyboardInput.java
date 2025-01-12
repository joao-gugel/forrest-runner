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

        if (keyCode == KeyEvent.VK_SPACE && !this.gamePanel.game.player.isAlive()) {
            if (!this.gamePanel.game.firstTimePlayed) {
                this.gamePanel.game.reset();
            }
            this.gamePanel.game.firstTimePlayed = false;
            this.gamePanel.game.player.setAlive(true);
            this.gamePanel.game.startGame();
        }

        if (keyCode == KeyEvent.VK_UP) {
            if (!this.gamePanel.game.player.jumping && this.gamePanel.game.player.onGround) {
                this.gamePanel.game.player.jumping = true;
                this.gamePanel.game.jumpSound.play();
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            if (this.gamePanel.game.player.jumping && !this.gamePanel.game.player.onGround) {
//                this.gamePanel.sound.setFile(0);
//                this.gamePanel.sound.play();
                this.gamePanel.game.player.maxFallSpeed = 8;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_DOWN)
            this.gamePanel.game.player.maxFallSpeed = 3;
    }
}
