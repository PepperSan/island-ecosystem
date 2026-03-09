package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Boar extends Herbivore {

    public Boar() {
        super(Species.BOAR);
    }

    @Override
    public void move(Island island, int x, int y) {
    }

    @Override
    public void reproduce(Location location, LocationDelta delta) {
        int boarsCount = 0;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Boar) {
                boarsCount++;
            }
        }

        long newbornBoars = delta.getAnimalsBorn().stream()
                .filter(animal -> animal instanceof Boar)
                .count();

        if (boarsCount >= 2
                && newbornBoars == 0
                && location.canAddAnimal(Species.BOAR)) {
            delta.addAnimalBorn(new Boar());
        }
    }
}
