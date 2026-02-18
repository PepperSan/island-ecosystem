package island.model.animals;

public final class EatTable {
    private static final int[][] CHANCE = new int[Species.values().length][Species.values().length];

    static {

        set(Species.WOLF, Species.RABBIT, 60);
        set(Species.WOLF, Species.MOUSE, 80);

    }

    private static void set(Species predator, Species prey, int chance) {
        CHANCE[predator.ordinal()][prey.ordinal()] = chance;
    }

    public static int chance(Species predator, Species prey) {
        return CHANCE[predator.ordinal()][prey.ordinal()];
    }

    private EatTable() {}
}

