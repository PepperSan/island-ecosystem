package island.model.animals;

public class Wolf extends Predator {

    public Wolf() {
        weight = 50;
        speed = 3;
        foodNeeded = 8;
    }

    @Override
    public void move() {
        System.out.println("Wolf runs");
    }

    @Override
    public void reproduce() {
        System.out.println("Wolf reproduces");
    }
}


