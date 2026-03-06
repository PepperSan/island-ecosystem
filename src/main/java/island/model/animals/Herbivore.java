package island.model.animals;

import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;

public abstract class Herbivore extends Animal {

    protected Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {

        if (location.getPlants().size() > delta.getPlantsToRemoveCount()) {
            delta.addPlantToRemove();
            resetHunger();
        } else {
            increaseHunger();
        }
    }

}


