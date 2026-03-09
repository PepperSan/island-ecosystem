package island.model.animals;

public final class EatTable {
    private static final int[][] CHANCE = new int[Species.values().length][Species.values().length];

    static {

        set(Species.WOLF, Species.RABBIT, 60);
        set(Species.WOLF, Species.MOUSE, 80);
        set(Species.WOLF, Species.GOAT, 60);
        set(Species.WOLF, Species.SHEEP, 70);
        set(Species.WOLF, Species.DUCK, 40);
        set(Species.WOLF, Species.DEER, 15);
        set(Species.WOLF, Species.HORSE, 10);
        set(Species.WOLF, Species.BOAR, 15);
        set(Species.WOLF, Species.BUFFALO, 10);
        set(Species.WOLF, Species.CATERPILLAR, 0);

        set(Species.FOX, Species.RABBIT, 70);
        set(Species.FOX, Species.MOUSE, 90);
        set(Species.FOX, Species.DUCK, 60);
        set(Species.FOX, Species.CATERPILLAR, 40);

        set(Species.BOA, Species.FOX, 15);
        set(Species.BOA, Species.RABBIT, 20);
        set(Species.BOA, Species.MOUSE, 40);
        set(Species.BOA, Species.DUCK, 10);

        set(Species.BEAR, Species.BOA, 80);
        set(Species.BEAR, Species.HORSE, 40);
        set(Species.BEAR, Species.DEER, 80);
        set(Species.BEAR, Species.RABBIT, 80);
        set(Species.BEAR, Species.MOUSE, 90);
        set(Species.BEAR, Species.GOAT, 70);
        set(Species.BEAR, Species.SHEEP, 70);
        set(Species.BEAR, Species.BOAR, 50);
        set(Species.BEAR, Species.DUCK, 10);

        set(Species.EAGLE, Species.FOX, 10);
        set(Species.EAGLE, Species.RABBIT, 90);
        set(Species.EAGLE, Species.MOUSE, 90);
        set(Species.EAGLE, Species.DUCK, 80);

    }

    private static void set(Species predator, Species prey, int chance) {
        CHANCE[predator.ordinal()][prey.ordinal()] = chance;
    }

    public static int chance(Species predator, Species victim) {
        return CHANCE[predator.ordinal()][victim.ordinal()];
    }

    private EatTable() {}
}

