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

        // 1) питание
        for (Animal a : snapshot) {
            if (delta.getAnimalsToRemove().contains(a)) continue;

            a.eat(loc, delta, rnd);
            a.reproduce(loc, delta);

            if (a.isStarving()) {
                delta.addAnimalToRemove(a);
            }
        }

        // 2) рост растений
        if (rnd.nextInt(100) < 30) {
            delta.addPlantToAdd();
        }


        return delta;
    }
}