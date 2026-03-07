package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator {

    public Boa() {
        super(Species.BOA);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        for (Animal animal : location.getAnimals()) {
            if ((animal instanceof Rabbit || animal instanceof Mouse)
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
        int boasCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Boa) {
                boasCount++;
            }
        }

        long newbornBoas = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Boa)
                .count();

        if (boasCount >= 2
                && newbornBoas == 0
                && location.canAddAnimal(Species.BOA)
                && ThreadLocalRandom.current().nextInt(100) < 20) {
            delta.addAnimalBorn(new Boa());
        }
    }
}
