package island.simulation.engine;

import island.model.island.Island;
import island.model.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine {

    private final Island island;
    private final ExecutorService workers;
    private final ScheduledExecutorService scheduler;

    public SimulationEngine(Island island) {
        this.island = island;
        this.workers = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::tick, 0, 1, TimeUnit.SECONDS);
    }

    private void tick() {
        try {
            List<Future<LocationDelta>> futures = new ArrayList<>();

            for (int y = 0; y < island.getHeight(); y++) {
                for (int x = 0; x < island.getWidth(); x++) {
                    futures.add(workers.submit(new LocationTask(island, x, y)));
                }
            }

            List<LocationDelta> deltas = new ArrayList<>(futures.size());
            for (Future<LocationDelta> f : futures) {
                deltas.add(f.get()); // ждём завершения всех задач
            }

            applyDeltas(deltas);
            printStats();

        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void applyDeltas(List<LocationDelta> deltas) {
        // 1) применяем локальные изменения (еда/рождение)
        for (LocationDelta d : deltas) {
            Location loc = island.getLocation(d.x(), d.y());
            for (var a : d.animalsToRemove()) loc.removeAnimal(a);
            loc.removeFirstPlants(d.plantsToRemoveCount());
            for (var b : d.animalsBorn()) loc.addAnimal(b);
        }

        // 2) применяем перемещения
        for (LocationDelta d : deltas) {
            for (MoveRequest m : d.moves()) {
                Location from = island.getLocation(m.fromX(), m.fromY());
                Location to = island.getLocation(m.toX(), m.toY());

                if (from.getAnimals().remove(m.animal())) { // если ещё там
                    to.addAnimal(m.animal());
                }
            }
        }
    }

    private void printStats() {
        int animals = 0;
        int plants = 0;
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                var loc = island.getLocation(x, y);
                animals += loc.getAnimals().size();
                plants += loc.getPlants().size();
            }
        }
        System.out.println("Tick: animals=" + animals + ", plants=" + plants);
    }

    public void stop() {
        scheduler.shutdownNow();
        workers.shutdownNow();
    }
}

