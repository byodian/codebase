package com.byodian.class_objects;

public class Bicycle {
    private static int numberOfBicycles;
    private static String className;

    private int cadence;
    private int gear;
    private int speed;
    private int id;

    static {
        className = "Bicycle";
        initializeClassVariables();
    }

    private static void initializeClassVariables() {
        numberOfBicycles = 0;
        className = "Bicycle1";
    }

    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
        id = ++numberOfBicycles;
    }

    public int getId() {
        return id;
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    public static int getNumberOfBicycle() {
        return numberOfBicycles;
    }

    public static String getClassName() {
        return className;
    }

    // no modifier  - package-private
    void packagePrivateMethodWithinSuper() {
        System.out.printf("Package-Private method within the super class. gear: %s, speed: %s%n",
            this.gear, this.cadence);
    }

    protected void protectedMethodWithinSuper() {
        System.out.println("Protected method within the super class.");
    }
}
