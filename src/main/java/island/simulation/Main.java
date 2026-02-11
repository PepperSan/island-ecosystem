package island.simulation;

import island.model.island.Island;
import island.simulation.init.IslandPopulator;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(3, 3);
        new IslandPopulator().populate(island);

        // Проверим одну клетку
        System.out.println("Animals in (1,1): " + island.getLocation(1, 1).getAnimals().size());
        System.out.println("Plants in (1,1): " + island.getLocation(1, 1).getPlants().size());
    }
}




