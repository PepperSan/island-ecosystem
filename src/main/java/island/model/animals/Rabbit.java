package island.model.animals;

public class Rabbit extends Herbivore {

    public Rabbit() {
        weight = 2;
        speed = 2;
        foodNeeded = 0.45;
    }

    @Override
    public void move() {
        System.out.println("Rabbit hops");
    }

    @Override
    public void reproduce() {
        System.out.println("Rabbit reproduces");
    }
}

