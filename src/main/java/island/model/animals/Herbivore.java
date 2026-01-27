package island.model.animals;

public abstract class Herbivore extends Animal {

    @Override
    public void eat() {
        System.out.println(getClass().getSimpleName() + " eats plants");
    }
}


