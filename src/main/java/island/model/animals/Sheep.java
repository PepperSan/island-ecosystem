package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Sheep extends Herbivore {

    public Sheep() {
        super(Species.SHEEP);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int sheepCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Sheep) {
                sheepCount++;
            }
        }

        long newbornSheep = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Sheep)
                .count();

        if (sheepCount >= 2
                && newbornSheep == 0
                && location.canAddAnimal(Species.SHEEP)) {
            delta.addAnimalBorn(new Sheep());
        }
    }
}
