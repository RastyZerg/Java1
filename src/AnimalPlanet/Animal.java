package AnimalPlanet;

import java.util.Random;

public abstract class Animal {

    private static final Random RANDOM = new Random();
    protected String name;
    protected double maxRuning;
    protected double maxJumping;
    protected double maxSwiming;

    protected Animal(String name, double maxRuning, double maxJumping, double maxSwiming){
        this.name = name;
        this.maxRuning = maxRuning * RANDOM.nextDouble(0.8, 1.2);
        this.maxJumping = maxJumping * RANDOM.nextDouble(0.8, 1.2);
        this.maxSwiming = maxSwiming * RANDOM.nextDouble(0.8, 1.2);
    }

    public void run(double distance){
        if (distance <= maxRuning){
            System.out.println(this.name + " пробежал!");
        }
        else {
            System.out.println(this.name + " не пробежал");
        }
    }

    public void jump(double height){
        if (height <= maxJumping){
            System.out.println(this.name + " перепрыгнул!!");
        }
        else {
            System.out.println(this.name + " не перепрыгнул");
        }
    }

    public void swim(double distance){
        if (distance <= maxSwiming){
            System.out.println(this.name + " проплыл!");
        }
        else {
            System.out.println(this.name + " утонул :(");
        }
    }
    public void info(){
        System.out.println(String.format("%s %f %f %f", this.name, this.maxRuning, this.maxJumping, this.maxSwiming));
    }
}
