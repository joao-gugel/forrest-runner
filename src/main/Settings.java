package main;

public class Settings {
    public static final int ORIGINAL_TILE_SIZE = 96;
    public static final float SCALE = 1f;
    public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
    public static final int SCREEN_COLUMNS = 14;
    public static final int SCREEN_ROWS = 8;
    public static final int SCREEN_WIDTH = SCREEN_COLUMNS * TILE_SIZE;
    public static final int SCREEN_HEIGHT = SCREEN_ROWS * TILE_SIZE;

    public static final int FPS_SET = 60;
    public static final int UPS_SET = 120;

}
