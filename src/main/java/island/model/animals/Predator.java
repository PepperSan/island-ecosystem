package island.model.animals;

public abstract class Predator extends Animal {
    @Override
    public void eat() {
        System.out.println(getClass().getSimpleName() + " hunts animals");
    }
}

