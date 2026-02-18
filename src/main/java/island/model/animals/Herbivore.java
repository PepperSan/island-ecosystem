package island.model.animals;

import island.model.location.Location;

public abstract class Herbivore extends Animal {

    protected Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Location location) {
        System.out.println(getClass().getSimpleName() +
                " tries to eat plants");
    }

}


