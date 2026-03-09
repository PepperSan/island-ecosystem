package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {

    public Eagle() {
        super(Species.EAGLE);
    }

    @Override
    public void move(Island island, int x, int y) {
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
    public void reproduce(Location location, LocationDelta delta) {
        int eaglesCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Eagle) {
                eaglesCount++;
            }
        }

        long newbornEagles = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Eagle)
                .count();

        if (eaglesCount >= 2
                && newbornEagles == 0
                && location.canAddAnimal(Species.EAGLE)
                && ThreadLocalRandom.current().nextInt(100) < 15) {
            delta.addAnimalBorn(new Eagle());
        }
    }
}
