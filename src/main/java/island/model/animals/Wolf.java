package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;

public class Wolf extends Predator {

    public Wolf() {
        this.weight = 50;
        this.speed = 3;
        this.foodNeeded = 8;
        this.maxPerCell = 30;
    }

    @Override
    public void eat(Location location) { }

    @Override
    public void move(Island island, int x, int y) { }

    @Override
    public void reproduce(Location location) { }

}


