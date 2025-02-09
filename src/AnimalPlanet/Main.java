package AnimalPlanet;

import AnimalPlanet.zoo.*;

public class Main {

    public static void main(String[] args){
        Cat cat1 = new Cat("Semka");
        Dog dog1 = new Dog("Rolly");
        dog1.swim(10);
        dog1.run(500);
        cat1.swim(2);
        dog1.info();
        cat1.info();
    }
}
