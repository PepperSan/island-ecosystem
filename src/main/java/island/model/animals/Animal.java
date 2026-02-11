package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;

public abstract class Animal {

    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;


    public abstract void eat(Location location);
    public abstract void reproduce(Location location);
    public abstract void move(Island island, int x, int y);

    public int getSpeed() {
        return speed;
    }
}

