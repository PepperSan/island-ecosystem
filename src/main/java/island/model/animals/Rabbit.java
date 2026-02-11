package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;

public class Rabbit extends Herbivore {

    public Rabbit() {
        this.weight = 2;
        this.speed = 2;
        this.foodNeeded = 0.45;
        this.maxPerCell = 150;
    }


    @Override
    public void eat(Location location) { }

    @Override
    public void move(Island island, int x, int y) { }

    @Override
    public void reproduce(Location location) { }

}

