package island.simulation.init;

import island.model.animals.*;
import island.model.island.Island;
import island.model.plants.Plant;

import java.util.concurrent.ThreadLocalRandom;

public class IslandPopulator {


    private static final int MAX_RABBITS_PER_CELL = 5;
    private static final int MAX_WOLVES_PER_CELL = 2;
    private static final int MAX_PLANTS_PER_CELL = 10;
    private static final int MAX_MICE_PER_CELL = 3;
    private static final int MAX_FOXES_PER_CELL = 1;
    private static final int MAX_GOATS_PER_CELL = 1;
    private static final int MAX_BOAS_PER_CELL = 1;
    private static final int MAX_SHEEP_PER_CELL = 1;
    private static final int MAX_BEARS_PER_CELL = 1;
    private static final int MAX_EAGLES_PER_CELL = 1;
    private static final int MAX_DUCKS_PER_CELL = 1;
    private static final int MAX_DEER_PER_CELL = 1;
    private static final int MAX_HORSES_PER_CELL = 1;
    private static final int MAX_BUFFALO_PER_CELL = 1;
    private static final int MAX_BOARS_PER_CELL = 1;
    private static final int MAX_CATERPILLARS_PER_CELL = 2;

    public void populate(Island island) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {

                int rabbits = rnd.nextInt(MAX_RABBITS_PER_CELL + 1);
                int wolves = rnd.nextInt(MAX_WOLVES_PER_CELL + 1);
                int plants = rnd.nextInt(MAX_PLANTS_PER_CELL + 1);
                int mice = rnd.nextInt(MAX_MICE_PER_CELL + 1);
                int foxes = rnd.nextInt(MAX_FOXES_PER_CELL + 1);
                int goats = rnd.nextInt(MAX_GOATS_PER_CELL + 1);
                int boas = rnd.nextInt(MAX_BOAS_PER_CELL + 1);
                int sheep = rnd.nextInt(MAX_SHEEP_PER_CELL + 1);
                int bears = rnd.nextInt(MAX_BEARS_PER_CELL + 1);
                int eagles = rnd.nextInt(MAX_EAGLES_PER_CELL + 1);
                int ducks = rnd.nextInt(MAX_DUCKS_PER_CELL + 1);
                int deer = rnd.nextInt(MAX_DEER_PER_CELL + 1);
                int horses = rnd.nextInt(MAX_HORSES_PER_CELL + 1);
                int buffalo = rnd.nextInt(MAX_BUFFALO_PER_CELL + 1);
                int boars = rnd.nextInt(MAX_BOARS_PER_CELL + 1);
                int caterpillars = rnd.nextInt(MAX_CATERPILLARS_PER_CELL + 1);



                for (int i = 0; i < rabbits; i++) {
                    island.getLocation(x, y).addAnimal(new Rabbit());
                }
                for (int i = 0; i < mice; i++) {
                    island.getLocation(x, y).addAnimal(new Mouse());
                }
                for (int i = 0; i < goats; i++) {
                    island.getLocation(x, y).addAnimal(new Goat());
                }
                for (int i = 0; i < sheep; i++) {
                    island.getLocation(x, y).addAnimal(new Sheep());
                }
                for (int i = 0; i < ducks; i++) {
                    island.getLocation(x, y).addAnimal(new Duck());
                }

                for (int i = 0; i < deer; i++) {
                    island.getLocation(x, y).addAnimal(new Deer());
                }
                for (int i = 0; i < wolves; i++) {
                    island.getLocation(x, y).addAnimal(new Wolf());
                }
                for (int i = 0; i < foxes; i++) {
                    island.getLocation(x, y).addAnimal(new Fox());
                }
                for (int i = 0; i < plants; i++) {
                    island.getLocation(x, y).addPlant(new Plant(1.0));
                }
                for (int i = 0; i < boas; i++) {
                    island.getLocation(x, y).addAnimal(new Boa());
                }
                for (int i = 0; i < bears; i++) {
                    island.getLocation(x, y).addAnimal(new Bear());
                }
                for (int i = 0; i < eagles; i++) {
                    island.getLocation(x, y).addAnimal(new Eagle());
                }
                for (int i = 0; i < horses; i++) {
                    island.getLocation(x, y).addAnimal(new Horse());
                }

                for (int i = 0; i < buffalo; i++) {
                    island.getLocation(x, y).addAnimal(new Buffalo());
                }
                for (int i = 0; i < boars; i++) {
                    island.getLocation(x, y).addAnimal(new Boar());
                }

                for (int i = 0; i < caterpillars; i++) {
                    island.getLocation(x, y).addAnimal(new Caterpillar());
                }

            }
        }
    }
}

