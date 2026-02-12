package island.simulation;

import island.model.island.Island;
import island.simulation.engine.SimulationEngine;
import island.simulation.init.IslandPopulator;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(3, 3);
        new IslandPopulator().populate(island);

        SimulationEngine engine = new SimulationEngine(island);
        engine.start();
    }
}
