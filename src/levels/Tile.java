package levels;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    int xPosition;
    BufferedImage image;

    public Tile(int x, BufferedImage img) {
        this.xPosition = x;
        this.image = img;
    }

}
