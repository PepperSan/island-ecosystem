package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;
import island.simulation.engine.LocationDelta;

import java.util.Random;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(Species.RABBIT);
    }


    @Override
    public void eat(Location location, LocationDelta delta, Random rnd) {
        super.eat(location, delta, rnd);
    }

    @Override
    public void move(Island island, int x, int y) { }

    @Override
    public void reproduce(Location location, LocationDelta delta) { }

}

