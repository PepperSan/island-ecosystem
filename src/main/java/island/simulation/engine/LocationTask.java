package island.simulation.engine;

import island.model.animals.Animal;
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

        // снимок животных (чтобы не ловить ConcurrentModification)
        List<Animal> snapshot = new ArrayList<>(loc.getAnimals());

        Random rnd = ThreadLocalRandom.current();

        LocationDelta delta = new LocationDelta(x, y);

        // 1) питание
        for (Animal a : snapshot) {
            if (delta.getAnimalsToRemove().contains(a)) continue;

            if (a instanceof Predator p) {
                p.eat(loc, delta, rnd);
            }
            // позже добавишь Herbivore
        }

        // 2) рост растений (теперь пишем в delta, а не в локальную переменную)
        if (rnd.nextInt(100) < 30) {
            delta.addPlantToAdd();
        }

        // 3) движение (позже)
        // delta.addMove(new MoveRequest(a, x, y, nx, ny));

        System.out.println("delta: removeAnimals=" + delta.getAnimalsToRemove().size()
                + ", born=" + delta.getAnimalsBorn().size()
                + ", moves=" + delta.getMoves().size()
                + ", plantsRemove=" + delta.getPlantsToRemoveCount()
                + ", plantsAdd=" + delta.getPlantsToAddCount());

        return delta;
    }
}