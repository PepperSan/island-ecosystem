package island.model.animals;

import island.model.location.Location;

public abstract class Predator extends Animal {

    protected Predator(Species species) {
        super(species);
    }

    @Override
    public void eat(Location location) {
        System.out.println(getClass().getSimpleName() +
                " tries to hunt");
    }

}

