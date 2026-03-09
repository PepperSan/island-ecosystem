package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {

    public Fox() {
        super(Species.FOX);
    }

    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        for (Animal animal : location.getAnimals()) {
            if (delta.getAnimalsToRemove().contains(animal)) {
                continue;
            }

            int chance = EatTable.chance(getSpecies(), animal.getSpecies());

            if (chance > 0 && rnd.nextInt(100) < chance) {
                delta.addAnimalToRemove(animal);
                resetHunger();
                return;
            }
        }

        increaseHunger();
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int foxesCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Fox) {
                foxesCount++;
            }
        }

        long newbornFoxes = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Fox)
                .count();

        if (foxesCount >= 2
                && newbornFoxes == 0
                && location.canAddAnimal(Species.FOX)
                && ThreadLocalRandom.current().nextInt(100) < 25) {
            delta.addAnimalBorn(new Fox());
        }
    }
}