package island.simulation.engine;

import island.model.animals.Animal;

public record MoveRequest(Animal animal, int fromX, int fromY, int toX, int toY) {}

