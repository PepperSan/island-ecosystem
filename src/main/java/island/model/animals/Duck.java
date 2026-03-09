package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Duck extends Herbivore {

    public Duck() {
        super(Species.DUCK);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int ducksCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Duck) {
                ducksCount++;
            }
        }

        long newbornDucks = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Duck)
                .count();

        if (ducksCount >= 2
                && newbornDucks == 0
                && location.canAddAnimal(Species.DUCK)) {
            delta.addAnimalBorn(new Duck());
        }
    }
}
