package island.simulation.engine;

import island.model.animals.Animal;

import java.util.List;

public record LocationDelta(
        int x, int y,
        List<Animal> animalsToRemove,
        int plantsToRemoveCount,
        List<Animal> animalsBorn,
        List<MoveRequest> moves
) {}

