package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Mouse extends Herbivore {

    public Mouse() {
        super(Species.MOUSE);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int miceCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Mouse) {
                miceCount++;
            }
        }

        long newbornMice = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Mouse)
                .count();

        if (miceCount >= 2
                && newbornMice == 0
                && location.canAddAnimal(Species.MOUSE)) {
            delta.addAnimalBorn(new Mouse());
        }
    }
}