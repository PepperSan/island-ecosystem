package island.simulation.service;

import island.model.animals.Animal;
import island.model.animals.Rabbit;
import island.model.animals.Wolf;
import island.model.island.Island;
import island.model.location.Location;
import island.model.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class FeedingService {

    public void feed(Island island) {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                feedInLocation(location);
            }
        }
    }

    private void feedInLocation(Location location) {
        // копия списка, чтобы можно было удалять из локации
        List<Animal> animalsSnapshot = new ArrayList<>(location.getAnimals());

        // 1) Волки едят кроликов
        for (Animal animal : animalsSnapshot) {
            if (animal instanceof Wolf) {
                Rabbit victim = findRabbit(location);
                if (victim != null) {
                    location.removeAnimal(victim);
                }
            }
        }

        // 2) Кролики едят растения
        animalsSnapshot = new ArrayList<>(location.getAnimals());
        for (Animal animal : animalsSnapshot) {
            if (animal instanceof Rabbit) {
                Plant plant = findPlant(location);
                if (plant != null) {
                    location.removePlant(plant);
                }
            }
        }
    }

    private Rabbit findRabbit(Location location) {
        for (Animal a : location.getAnimals()) {
            if (a instanceof Rabbit r) return r;
        }
        return null;
    }

    private Plant findPlant(Location location) {
        if (location.getPlants().isEmpty()) return null;
        return location.getPlants().get(0);
    }
}

