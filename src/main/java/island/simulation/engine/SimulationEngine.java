package island.simulation.engine;

import island.model.animals.Animal;
import island.model.animals.Species;
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
        int rabbits = 0;
        int mice = 0;
        int goats = 0;
        int wolves = 0;
        int foxes = 0;
        int boas = 0;
        int plants = 0;
        int sheep = 0;
        int bears = 0;
        int ducks = 0;
        int deer = 0;
        int eagles = 0;
        int horses = 0;
        int buffalo = 0;
        int boars = 0;
        int caterpillars = 0;

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);

                for (Animal animal : location.getAnimals()) {
                    if (animal.getSpecies() == Species.RABBIT) {
                        rabbits++;
                    } else if (animal.getSpecies() == Species.MOUSE) {
                        mice++;
                    } else if (animal.getSpecies() == Species.GOAT) {
                        goats++;
                    } else if (animal.getSpecies() == Species.WOLF) {
                        wolves++;
                    } else if (animal.getSpecies() == Species.FOX) {
                        foxes++;
                    } else if (animal.getSpecies() == Species.BOA) {
                        boas++;
                    }
                    else if (animal.getSpecies() == Species.SHEEP) {
                        sheep++;
                    } else if (animal.getSpecies() == Species.BEAR) {
                        bears++;
                    }else if (animal.getSpecies() == Species.DUCK) {
                        ducks++;
                    } else if (animal.getSpecies() == Species.DEER) {
                        deer++;
                    } else if (animal.getSpecies() == Species.EAGLE) {
                        eagles++;
                    }else if (animal.getSpecies() == Species.HORSE) {
                        horses++;
                    } else if (animal.getSpecies() == Species.BUFFALO) {
                        buffalo++;
                    }else if (animal.getSpecies() == Species.BOAR) {
                        boars++;
                    } else if (animal.getSpecies() == Species.CATERPILLAR) {
                        caterpillars++;
                    }

                }

                plants += location.getPlants().size();
            }
        }

        System.out.println("Tick: rabbits=" + rabbits
                + ", mice=" + mice
                + ", goats=" + goats
                + ", sheep=" + sheep
                + ", ducks=" + ducks
                + ", deer=" + deer
                + ", horses=" + horses
                + ", buffalo=" + buffalo
                + ", boars=" + boars
                + ", caterpillars=" + caterpillars
                + ", wolves=" + wolves
                + ", foxes=" + foxes
                + ", boas=" + boas
                + ", bears=" + bears
                + ", eagles=" + eagles
                + ", plants=" + plants);
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

