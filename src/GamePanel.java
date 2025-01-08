import io.KeyboardInput;

import javax.swing.*;

public class GamePanel extends JPanel {
    private KeyboardInput keyboardInput;

    public GamePanel() {
        this.keyboardInput = new KeyboardInput();
        this.setFocusable(true);
        this.addKeyListener(keyboardInput);
    }

    private void initDependencies() {}
}
