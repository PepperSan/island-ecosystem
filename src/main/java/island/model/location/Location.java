package island.model.location;

import island.model.animals.Animal;
import island.model.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private static final int MAX_PLANTS = 10;


    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addPlant(Plant plant) {
        if (plants.size() < MAX_PLANTS) {
            plants.add(plant);
        }
    }


    public void growPlant() {
        if (plants.size() < MAX_PLANTS) {
            plants.add(new Plant(1.0));
        }
    }



    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public void removeFirstPlants(int n) {
        int count = Math.min(n, plants.size());
        for (int i = 0; i < count; i++) {
            plants.remove(0);
        }
    }


}

