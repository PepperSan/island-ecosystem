package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {

    public Wolf() {
        super(Species.WOLF);
    }

    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Rabbit && !delta.getAnimalsToRemove().contains(animal)) {
                delta.addAnimalToRemove(animal);
                resetHunger();
                return;
            }
        }

        increaseHunger();
    }
    @Override
    public void move(Island island, int x, int y) { }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int wolvesCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Wolf) {
                wolvesCount++;
            }
        }

        long newbornWolves = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Wolf)
                .count();

        if (wolvesCount >= 2
                && newbornWolves == 0
                && location.canAddAnimal(Species.WOLF)
                && ThreadLocalRandom.current().nextInt(100) < 25) {
            delta.addAnimalBorn(new Wolf());
        }
    }
}


