package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Goat extends Herbivore {

    public Goat() {
        super(Species.GOAT);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int goatsCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Goat) {
                goatsCount++;
            }
        }

        long newbornGoats = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Goat)
                .count();

        if (goatsCount >= 2
                && newbornGoats == 0
                && location.canAddAnimal(Species.GOAT)) {
            delta.addAnimalBorn(new Goat());
        }
    }
}
