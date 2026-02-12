package island.simulation.engine;

import island.model.animals.Animal;
import island.model.animals.Rabbit;
import island.model.animals.Wolf;
import island.model.island.Island;
import island.model.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class LocationTask implements Callable<LocationDelta> {

    private final Island island;
    private final int x;
    private final int y;

    public LocationTask(Island island, int x, int y) {
        this.island = island;
        this.x = x;
        this.y = y;
    }

    @Override
    public LocationDelta call() {
        Location location = island.getLocation(x, y);


        List<Animal> animals = new ArrayList<>(location.getAnimals());
        int plantsCount = location.getPlants().size();

        List<Animal> toRemove = new ArrayList<>();
        List<Animal> born = new ArrayList<>();
        List<MoveRequest> moves = new ArrayList<>();

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        // 1) Питание (пример: Wolf -> Rabbit 60%)
        List<Wolf> wolves = new ArrayList<>();
        List<Rabbit> rabbits = new ArrayList<>();

        for (Animal a : animals) {
            if (a instanceof Wolf w) wolves.add(w);
            else if (a instanceof Rabbit r) rabbits.add(r);
        }

        int rabbitsAlive = rabbits.size();
        for (Wolf w : wolves) {
            if (rabbitsAlive == 0) break;
            if (rnd.nextInt(100) < 60) { // 60% шанс
                Rabbit victim = rabbits.get(rabbitsAlive - 1);
                toRemove.add(victim);
                rabbitsAlive--;
            }
        }

        // 2) Кролики едят растения (по 1 растению)
        int rabbitsAfterHunt = rabbitsAlive;
        int plantsToRemove = Math.min(plantsCount, rabbitsAfterHunt);

        // 3) Движение (двигаются те, кого НЕ съели)
        for (Animal a : animals) {
            if (toRemove.contains(a)) continue;

            int speed = a.getSpeed();
            int stepX = rnd.nextInt(-speed, speed + 1);
            int stepY = rnd.nextInt(-speed, speed + 1);

            int nx = clamp(x + stepX, 0, island.getWidth() - 1);
            int ny = clamp(y + stepY, 0, island.getHeight() - 1);

            if (nx == x && ny == y) continue;

            moves.add(new MoveRequest(a, x, y, nx, ny));
        }

        return new LocationDelta(x, y, toRemove, plantsToRemove, born, moves);
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}

