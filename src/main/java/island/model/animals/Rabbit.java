package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(Species.RABBIT);
    }


    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        super.eat(location, delta, rnd);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int rabbitsCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Rabbit) {
                rabbitsCount++;
            }
        }

        long newbornRabbits = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Rabbit)
                .count();

        if (rabbitsCount >= 2
                && newbornRabbits == 0
                && location.canAddAnimal(Species.RABBIT)) {
            delta.addAnimalBorn(new Rabbit());
        }
    }
}

