package island.model.animals;

import island.model.location.Location;
import island.simulation.engine.LocationDelta;

public abstract class Predator extends Animal {

    protected Predator(Species species) {
        super(species);
    }

    @Override
    public void eat(Location loc, LocationDelta delta, java.util.Random rnd) {

        System.out.println("EAT called: " + getClass().getSimpleName()
                + " species=" + this.getSpecies()
                + " locAnimals=" + loc.getAnimals().size());

        // 1) найдём жертву по шансу
        var animals = loc.getAnimals();

        Animal victim = null;

        for (Animal candidate : animals) {
            if (candidate == this) continue;

            int chance = EatTable.chance(this.getSpecies(), candidate.getSpecies());
            if (chance > 0) {
                System.out.println("chance " + this.getSpecies() + " -> " + candidate.getSpecies() + " = " + chance);
            }
            if (chance <= 0) continue;

            if (rnd.nextInt(100) < chance) {
                victim = candidate;
                break;
            }
        }

        // 2) если съел — помечаем жертву на удаление, сбрасываем голод
        if (victim != null) {
            delta.addAnimalToRemove(victim);   // <-- подгони под своё имя метода
            this.resetHunger();
            return;
        }

        // 3) если не съел — голод растёт
        this.increaseHunger();

        // 4) умер от голода — помечаем себя на удаление
        if (this.isStarving()) {
            delta.addAnimalToRemove(this);
        }
    }

}

