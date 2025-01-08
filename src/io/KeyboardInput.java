package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private boolean jumpPressed = false, downPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP) this.setJumpPressed(true);
        if(keyCode == KeyEvent.VK_DOWN) this.setDownPressed(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP) this.setJumpPressed(false);
        if(keyCode == KeyEvent.VK_DOWN) this.setDownPressed(false);
    }





    // Getters & Setters

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public void setJumpPressed(boolean jumpPressed) {
        this.jumpPressed = jumpPressed;
    }
}
