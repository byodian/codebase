package com.byodian.class_objects;

public class TestClass {

    public void accessMountainBike() {
        MountainBike mountainBike = new MountainBike();
        // can have access to package-private and protected fields and methods.
        mountainBike.protectedMethod();
        mountainBike.packagePrivateMethod();
    }

    public static void main(String[] args) {
        MountainBike mountainBike = new MountainBike();
        mountainBike.speedUp(1);
        mountainBike.setHeight(1);
        mountainBike.packagePrivateMethod();
        mountainBike.protectedMethod();
        mountainBike.protectedMethodWithinSuper();
        mountainBike.packagePrivateMethodWithinSuper();
        System.out.println(Bicycle.getClassName());
        System.out.println(Bicycle.getNumberOfBicycle());
    }
}
