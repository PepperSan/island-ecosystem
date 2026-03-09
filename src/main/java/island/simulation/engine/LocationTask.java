package island.simulation.engine;

import island.model.animals.Animal;
import island.model.animals.Herbivore;
import island.model.animals.Predator;
import island.model.island.Island;
import island.model.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        Location loc = island.getLocation(x, y);
        List<Animal> snapshot = new ArrayList<>(loc.getAnimals());
        Random rnd = ThreadLocalRandom.current();
        LocationDelta delta = new LocationDelta(x, y);

        processAnimals(loc, snapshot, delta, rnd);
        processPlantGrowth(delta, rnd);

        return delta;
    }

    private void processAnimals(Location loc, List<Animal> snapshot, LocationDelta delta, Random rnd) {
        for (Animal a : snapshot) {
            if (delta.getAnimalsToRemove().contains(a)) {
                continue;
            }

            a.eat(loc, delta, rnd);
            a.reproduce(loc, delta);

            if (a.isStarving()) {
                delta.addAnimalToRemove(a);
                continue;
            }

            processMovement(a, delta, rnd);
        }
    }

    private void processMovement(Animal a, LocationDelta delta, Random rnd) {
        int speed = a.getSpeed();

        if (speed <= 0) {
            return;
        }

        int dx = rnd.nextInt(-speed, speed + 1);
        int dy = rnd.nextInt(-speed, speed + 1);

        int newX = Math.max(0, Math.min(island.getWidth() - 1, x + dx));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, y + dy));

        if (newX != x || newY != y) {
            delta.addMove(new MoveRequest(a, x, y, newX, newY));
        }
    }

    private void processPlantGrowth(LocationDelta delta, Random rnd) {
        if (rnd.nextInt(100) < 30) {
            delta.addPlantToAdd();
        }
    }
}