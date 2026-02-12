package island.simulation.service;

import island.model.animals.Animal;
import island.model.island.Island;
import island.model.location.Location;
import island.model.util.Point;

import java.util.ArrayList;
import java.util.List;

public class MovementService {

    public void move(Island island) {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {

                Location from = island.getLocation(x, y);
                List<Animal> snapshot = new ArrayList<>(from.getAnimals());

                for (Animal animal : snapshot) {
                    int speed = animal.getSpeed();
                    Point toPoint = island.randomNeighbor(x, y, speed);

                    if (toPoint.x() == x && toPoint.y() == y) continue;

                    from.removeAnimal(animal);
                    island.getLocation(toPoint.x(), toPoint.y()).addAnimal(animal);
                }
            }
        }
    }
}
