package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {

    public Bear() {
        super(Species.BEAR);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        for (Animal animal : location.getAnimals()) {
            if ((animal instanceof Rabbit
                    || animal instanceof Mouse
                    || animal instanceof Goat
                    || animal instanceof Sheep)
                    && !delta.getAnimalsToRemove().contains(animal)) {
                delta.addAnimalToRemove(animal);
                resetHunger();
                return;
            }
        }

        increaseHunger();
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int bearsCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Bear) {
                bearsCount++;
            }
        }

        long newbornBears = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Bear)
                .count();

        if (bearsCount >= 2
                && newbornBears == 0
                && location.canAddAnimal(Species.BEAR)
                && ThreadLocalRandom.current().nextInt(100) < 15) {
            delta.addAnimalBorn(new Bear());
        }
    }
}
