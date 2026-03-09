package island.simulation.engine;

import island.model.animals.Animal;
import island.model.animals.Species;
import island.model.island.Island;
import island.model.location.Location;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

        if (countAnimals() == 0) {
            System.out.println("STOP: all animals are dead");
            stop();
        }

    }

    private void applyDeltas(List<LocationDelta> deltas) {
        applyLocalChanges(deltas);
        applyMoves(deltas);
    }

    private void applyLocalChanges(List<LocationDelta> deltas) {
        for (LocationDelta d : deltas) {
            Location loc = island.getLocation(d.getX(), d.getY());

            for (var a : d.getAnimalsToRemove()) {
                loc.removeAnimal(a);
            }

            loc.removeFirstPlants(d.getPlantsToRemoveCount());

            for (int i = 0; i < d.getPlantsToAddCount(); i++) {
                loc.growPlant();
            }

            for (var b : d.getAnimalsBorn()) {
                loc.addAnimal(b);
            }
        }
    }

    private void applyMoves(List<LocationDelta> deltas) {
        for (LocationDelta d : deltas) {
            for (MoveRequest m : d.getMoves()) {
                Location from = island.getLocation(m.fromX(), m.fromY());
                Location to = island.getLocation(m.toX(), m.toY());

                moveAnimal(from, to, m.animal());
            }
        }
    }

    private void moveAnimal(Location from, Location to, Animal animal) {
        if (to.canAddAnimal(animal.getSpecies()) && from.removeAnimal(animal)) {
            to.addAnimal(animal);
        }
    }

    private void printStats() {
        Map<Species, Integer> stats = new EnumMap<>(Species.class);

        for (Species species : Species.values()) {
            stats.put(species, 0);
        }

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);

                for (Animal animal : location.getAnimals()) {
                    Species species = animal.getSpecies();
                    stats.put(species, stats.get(species) + 1);
                }

                stats.put(Species.PLANT, stats.get(Species.PLANT) + location.getPlants().size());
            }
        }

        System.out.println(
                "Tick: rabbits=" + stats.get(Species.RABBIT)
                        + ", mice=" + stats.get(Species.MOUSE)
                        + ", goats=" + stats.get(Species.GOAT)
                        + ", sheep=" + stats.get(Species.SHEEP)
                        + ", ducks=" + stats.get(Species.DUCK)
                        + ", deer=" + stats.get(Species.DEER)
                        + ", horses=" + stats.get(Species.HORSE)
                        + ", buffalo=" + stats.get(Species.BUFFALO)
                        + ", boars=" + stats.get(Species.BOAR)
                        + ", caterpillars=" + stats.get(Species.CATERPILLAR)
                        + ", wolves=" + stats.get(Species.WOLF)
                        + ", foxes=" + stats.get(Species.FOX)
                        + ", boas=" + stats.get(Species.BOA)
                        + ", bears=" + stats.get(Species.BEAR)
                        + ", eagles=" + stats.get(Species.EAGLE)
                        + ", plants=" + stats.get(Species.PLANT)
        );
    }

    private int countAnimals() {
        int animals = 0;
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                animals += island.getLocation(x, y).getAnimals().size();
            }
        }
        return animals;
    }

    public void stop() {
        scheduler.shutdownNow();
        workers.shutdownNow();
    }
}

