package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(Species.BUFFALO);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int buffaloCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Buffalo) {
                buffaloCount++;
            }
        }

        long newbornBuffalo = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Buffalo)
                .count();

        if (buffaloCount >= 2
                && newbornBuffalo == 0
                && location.canAddAnimal(Species.BUFFALO)) {
            delta.addAnimalBorn(new Buffalo());
        }
    }
}