package island.model.animals;

import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public abstract class Herbivore extends Animal {

    protected Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Location loc, LocationDelta delta, java.util.Random rnd) {
        System.out.println(getClass().getSimpleName() +
                " tries to eat plants");
    }

}


