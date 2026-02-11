package island.model.island;

import island.model.location.Location;
import island.model.util.Point;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];
        init();
    }

    private void init() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location();
            }
        }
    }

    public Point randomNeighbor(int x, int y, int maxStep) {
        int step = Math.max(1, maxStep);
        int dx = (int) (Math.random() * (2 * step + 1)) - step; // [-step..step]
        int dy = (int) (Math.random() * (2 * step + 1)) - step;

        int nx = Math.min(width - 1, Math.max(0, x + dx));
        int ny = Math.min(height - 1, Math.max(0, y + dy));

        return new Point(nx, ny);
    }

    public Location getLocation(int x, int y) {
        return locations[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

