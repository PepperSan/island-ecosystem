package island.simulation;

import island.model.animals.Animal;
import island.model.animals.Rabbit;
import island.model.animals.Wolf;

public class Main {
    public static void main(String[] args) {
        System.out.println("Simulation started");
        Animal a1 = new Rabbit();
        Animal a2 = new Wolf();

        a1.eat();
        a2.eat();

    }
}

