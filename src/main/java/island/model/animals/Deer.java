package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Deer extends Herbivore {

    public Deer() {
        super(Species.DEER);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int deerCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Deer) {
                deerCount++;
            }
        }

        long newbornDeer = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Deer)
                .count();

        if (deerCount >= 2
                && newbornDeer == 0
                && location.canAddAnimal(Species.DEER)) {
            delta.addAnimalBorn(new Deer());
        }
    }
}
