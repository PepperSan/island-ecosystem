package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Horse extends Herbivore {

    public Horse() {
        super(Species.HORSE);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int horsesCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Horse) {
                horsesCount++;
            }
        }

        long newbornHorses = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Horse)
                .count();

        if (horsesCount >= 2
                && newbornHorses == 0
                && location.canAddAnimal(Species.HORSE)) {
            delta.addAnimalBorn(new Horse());
        }
    }
}
