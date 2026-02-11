package island.simulation.init;

import island.model.animals.Rabbit;
import island.model.animals.Wolf;
import island.model.island.Island;
import island.model.plants.Plant;

import java.util.concurrent.ThreadLocalRandom;

public class IslandPopulator {


    private static final int MAX_RABBITS_PER_CELL = 5;
    private static final int MAX_WOLVES_PER_CELL = 2;
    private static final int MAX_PLANTS_PER_CELL = 10;

    public void populate(Island island) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {

                int rabbits = rnd.nextInt(MAX_RABBITS_PER_CELL + 1);
                int wolves = rnd.nextInt(MAX_WOLVES_PER_CELL + 1);
                int plants = rnd.nextInt(MAX_PLANTS_PER_CELL + 1);

                for (int i = 0; i < rabbits; i++) {
                    island.getLocation(x, y).addAnimal(new Rabbit());
                }
                for (int i = 0; i < wolves; i++) {
                    island.getLocation(x, y).addAnimal(new Wolf());
                }
                for (int i = 0; i < plants; i++) {
                    island.getLocation(x, y).addPlant(new Plant(1.0));
                }
            }
        }
    }
}

