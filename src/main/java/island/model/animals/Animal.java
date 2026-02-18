package island.model.animals;

import island.model.island.Island;
import island.model.location.Location;

public abstract class Animal {

    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected double hunger = 0;
    protected int starvationLimit = 3;
    private final Species species;

    protected Animal(Species species) {
        this.species = species;
    }


    public void resetHunger() {
        hunger = 0;
    }
    public void increaseHunger() {
        hunger++;
    }
    public boolean isStarving() {
        return hunger >= starvationLimit;
    }
    public abstract void eat(Location location);
    public abstract void reproduce(Location location);
    public abstract void move(Island island, int x, int y);

    public int getSpeed() {
        return speed;
    }
    public Species getSpecies() {
        return species;
    }
}

