package island.simulation.engine;

import island.model.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class LocationDelta {

    private final int x;
    private final int y;

    private final List<Animal> animalsToRemove = new ArrayList<>();
    private final List<Animal> animalsBorn = new ArrayList<>();
    private final List<MoveRequest> moves = new ArrayList<>();

    private int plantsToRemoveCount = 0;
    private int plantsToAddCount = 0;

    public LocationDelta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // ===== getters =====

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Animal> getAnimalsToRemove() {
        return animalsToRemove;
    }

    public List<Animal> getAnimalsBorn() {
        return animalsBorn;
    }

    public List<MoveRequest> getMoves() {
        return moves;
    }

    public int getPlantsToRemoveCount() {
        return plantsToRemoveCount;
    }

    public int getPlantsToAddCount() {
        return plantsToAddCount;
    }

    // ===== mutators =====

    public void addAnimalToRemove(Animal animal) {
        animalsToRemove.add(animal);
    }

    public void addAnimalBorn(Animal animal) {
        animalsBorn.add(animal);
    }

    public void addMove(MoveRequest move) {
        moves.add(move);
    }

    public void addPlantToRemove() {
        plantsToRemoveCount++;
    }

    public void addPlantToAdd() {
        plantsToAddCount++;
    }
}