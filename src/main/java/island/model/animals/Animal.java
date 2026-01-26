package island.model.animals;

public abstract class Animal {

    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;

    public abstract void eat();
    public abstract void move();
    public abstract void reproduce();
}

