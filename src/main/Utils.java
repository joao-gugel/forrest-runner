package main;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Utils {
    public static BufferedImage loadImageAsset(String path) {
        try (InputStream inputStream = Utils.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + path);
            }
            return ImageIO.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
