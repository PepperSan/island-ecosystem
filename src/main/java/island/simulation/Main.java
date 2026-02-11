package island.simulation;

import island.model.island.Island;
import island.simulation.init.IslandPopulator;
import island.simulation.service.FeedingService;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(3, 3);
        new IslandPopulator().populate(island);

        System.out.println("BEFORE:");
        System.out.println("Animals in (1,1): " + island.getLocation(1, 1).getAnimals().size());
        System.out.println("Plants in (1,1): " + island.getLocation(1, 1).getPlants().size());

        new FeedingService().feed(island);

        System.out.println("AFTER:");
        System.out.println("Animals in (1,1): " + island.getLocation(1, 1).getAnimals().size());
        System.out.println("Plants in (1,1): " + island.getLocation(1, 1).getPlants().size());
    }
}
