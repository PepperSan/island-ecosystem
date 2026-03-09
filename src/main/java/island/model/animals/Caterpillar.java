package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(Species.CATERPILLAR);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int caterpillarsCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Caterpillar) {
                caterpillarsCount++;
            }
        }

        long newbornCaterpillars = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Caterpillar)
                .count();

        if (caterpillarsCount >= 2
                && newbornCaterpillars == 0
                && location.canAddAnimal(Species.CATERPILLAR)) {
            delta.addAnimalBorn(new Caterpillar());
        }
    }
}
