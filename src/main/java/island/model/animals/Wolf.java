package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public class Wolf extends Predator {

    public Wolf() {
        super(Species.WOLF);
    }

    @Override
    public void eat(Location loc, LocationDelta delta, java.util.Random rnd) { }

    @Override
    public void move(Island island, int x, int y) { }

    @Override
    public void reproduce(Location location) { }

}


