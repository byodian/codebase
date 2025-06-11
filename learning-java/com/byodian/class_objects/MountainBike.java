package com.byodian.class_objects;

public class MountainBike extends Bicycle {
    private int seatHeight;
    public MountainBike(int startHeight, int startCadence,
                        int startSpeed, int startGear) {
        super(startCadence, startSpeed, startGear);
        seatHeight = startHeight;
    }

    public MountainBike() {
        super(0, 0, 0);
        seatHeight = 0;
    }

    public int getSeatHeight() {
        return seatHeight;
    }
 
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }

    // This is visible within the class and package
    // no modifier - package-private
    // Subclasses of the class declared outside this package have no access to the member.
    void packagePrivateMethod() {
        System.out.printf("Package-private method within the subclass. seatHeight: %s, speed: %s%n",
            this.seatHeight, this.getSpeed());
    }

    protected void protectedMethod() {
        System.out.printf("Protected method within the subclass, such as seatHeight: %s, speed: %s%n",
            this.seatHeight, this.getSpeed());
    }

    public static void changeMountainBycle(Bicycle bicycle) {
        // java 中方法参数是值传递
        MountainBike mountainBike = new MountainBike(1, 1, 0, 2);
        // 只是改变了 bicycle 指向的内存地址，并不会改变传入参数的值
        bicycle = mountainBike;
        bicycle.applyBrake(1);
        System.out.printf("Changing the reference of bicycle. bicycle seatHeight: %s, cadence: %s, speed: %s%n",
            mountainBike.getSeatHeight(), mountainBike.getCadence(), mountainBike.getSpeed());
    }

    public static void main(String[] args) {
        MountainBike mountainBike = new MountainBike(0, 0, 0, 0);
        System.out.println(mountainBike.getCadence());
        System.out.printf("%s: %d, %s%n", "version", 9, "test");
        System.out.println("end");
        changeMountainBycle(mountainBike);
        System.out.printf("MountainBike is not changed. seatHeight: %d, cadence: %d, speed: %d%n",
            mountainBike.getSeatHeight(), mountainBike.getCadence(), mountainBike.getSeatHeight());
    }
}
